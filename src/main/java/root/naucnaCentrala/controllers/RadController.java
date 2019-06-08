package root.naucnaCentrala.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.model.TaskDTO;
import root.naucnaCentrala.services.RadService;

@Controller
@RequestMapping("/rad")
public class RadController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private RadService radService;

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/sviRadovi", produces = "application/json")
	public @ResponseBody List<Rad> findAllRadovi() {
		List<Rad> rad = radService.findAllRadovi();
		return rad;
	}

	@GetMapping(path = "/vratiRad/{naziv}", produces = "application/json")
	public @ResponseBody Rad findRad(@PathVariable String naziv) {
		Rad rad = radService.findByNaziv(naziv);
		return rad;
	}

	@PostMapping(path = "/prijavi/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> submitPrijava(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String nazivCasopisa = (String) runtimeService.getVariable(task.getProcessInstanceId(), "casopis");
		Rad r = radService.makeRad(dto, nazivCasopisa);

		String processInstanceId = task.getProcessInstanceId();
		List<String> mejlovi = new ArrayList<String>();
		mejlovi.add(r.getAutor().getEmail());
		mejlovi.add(r.getCasopis().getGlavniUrednik().getEmail());
		runtimeService.setVariable(processInstanceId, "mejlovi", mejlovi);
		runtimeService.setVariable(processInstanceId, "urednik", r.getCasopis().getGlavniUrednik().getEmail());
		runtimeService.setVariable(processInstanceId, "autor", r.getAutor().getEmail());
		map.remove("autor");
		formService.submitTaskForm(taskId, map);
		
		System.out.println("Prijavljen rad");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}

	@PostMapping(path = "/download", produces = "application/json")
	public @ResponseBody ResponseEntity<byte[]> download(@RequestBody Long id) throws IOException {
		String putanja = radService.findById(id).getPutanja();
		Path path = Paths.get(putanja);
		File downloadFile = new File(putanja);

		byte[] content = Files.readAllBytes(path);

		HttpHeaders headers = new HttpHeaders();

		// set header fields
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength((int) downloadFile.length());

		// disposition = attachment - for download
		headers.setContentDispositionFormData("attachment; filename=\"%s\"",
				"C:\\Users\\Stefan\\Downloads\\KA1_Prijavni_formular_Vuk_Boskovic.pdf");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(content, headers, HttpStatus.OK);
	}

	@PostMapping(path = "/downloadNaziv", produces = "application/json")
	public @ResponseBody ResponseEntity<byte[]> download(@RequestBody String naziv) throws IOException {
		String putanja = radService.findByNaziv(naziv).getPutanja();
		Path path = Paths.get(putanja);
		File downloadFile = new File(putanja);

		byte[] content = Files.readAllBytes(path);

		HttpHeaders headers = new HttpHeaders();

		// set header fields
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength((int) downloadFile.length());

		// disposition = attachment - for download
		headers.setContentDispositionFormData("attachment; filename=\"%s\"",
				"C:\\Users\\Stefan\\Downloads\\KA1_Prijavni_formular_Vuk_Boskovic.pdf");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(content, headers, HttpStatus.OK);
	}

	@PostMapping(path = "/ispraviRad/{putanja}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> ispraviRad(@PathVariable String putanja, @RequestBody TaskDTO taskDTO) {
		taskService.createTaskQuery().active().list();
		Rad rad = radService.findByNaziv(taskDTO.getRad());
		radService.izmeniRad(rad, putanja);
		String taskId = taskDTO.getTaskId();
		taskService.complete(taskId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
