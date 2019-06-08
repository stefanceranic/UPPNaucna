package root.naucnaCentrala.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.model.Recenzent;
import root.naucnaCentrala.model.Recenzija;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.RadRepository;
import root.naucnaCentrala.repository.RecenzentRepository;
import root.naucnaCentrala.repository.RecenzijaRepository;

@Controller
@RequestMapping("/recenzent")
public class RecenzentController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private CasopisRepository casopisRepository;

	@Autowired
	private RecenzijaRepository recenzijaRepository;

	@Autowired
	private RadRepository radRepository;

	@Autowired
	private RecenzentRepository recenzentRepository;

	@Autowired
	private FormService formService;

	@GetMapping(path = "/sviRecenzenti/{taskId}", produces = "application/json")
	public @ResponseBody List<Recenzent> findAllRecenzenti(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String casopisNaziv = (String) runtimeService.getVariable(processInstanceId, "casopis");
		Casopis casopis = casopisRepository.findByNaziv(casopisNaziv);
		List<Recenzent> recenzenti = casopis.getRecenzenti();
		// List<Recenzent> recenzenti =
		// recenzentService.findAllRecenzentiByCasopis(casopis);
		return recenzenti;
	}

	@PostMapping(path = "/odabraniRecenzenti/{taskId}", produces = "application/json")
	public ResponseEntity<?> recenzenti(@PathVariable String taskId, @RequestBody List<String> rec) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recenzent1", rec.get(0));
		map.put("recenzent2", rec.get(1));
		List<String> recenzenti = new ArrayList<String>();
		recenzenti.add(rec.get(0));
		recenzenti.add(rec.get(1));
		runtimeService.setVariable(processInstanceId, "recenzenti", recenzenti);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/komentarisi/{taskId}", produces = "application/json")
	public ResponseEntity<?> zakomentarisi(@PathVariable String taskId, @RequestBody List<FormSubmissionDto> dto) {
		Recenzija recenzija = new Recenzija();

		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String recenzentEmail = task.getAssignee();
		Recenzent recenzent = recenzentRepository.findByEmail(recenzentEmail);
		Rad rad = radRepository.findByNaziv((String) runtimeService.getVariable(task.getProcessInstanceId(), "naslov"));
		String glavniOdgovorni = (String) runtimeService.getVariable(task.getProcessInstanceId(), "glavniOdgovorni");
		recenzija.setGlavniOdgovorni(glavniOdgovorni);
		recenzija.setRad(rad);
		recenzija.setKomentar((String) map.get("komentar"));
		recenzija.setOcena((String) map.get("preporukaOcene"));
		recenzija.setRecenzent(recenzent);
		recenzijaRepository.save(recenzija);
		runtimeService.setVariable(task.getProcessInstanceId(), "recenzija", recenzija.getId());
		formService.submitTaskForm(taskId, map);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
}
