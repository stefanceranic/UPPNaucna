package root.naucnaCentrala.controllers;

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
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.services.CasopisService;

@Controller
@RequestMapping("/casopis")
public class CasopisController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private CasopisService casopisService;

	@Autowired
	private CasopisRepository casopisRepository;

	@GetMapping(path = "/sviCasopisi", produces = "application/json")
	public @ResponseBody List<Casopis> findAllCasopis() {
		List<Casopis> c = casopisService.pronadjiSveCasopise();
		return c;
	}

	@PostMapping(path = "/odaberi/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> odaberiCasopis(@PathVariable String taskId, @RequestBody String naziv) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("casopis", naziv);
		Casopis casopis = casopisRepository.findByNaziv(naziv);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "casopis", naziv);

		if (casopis.isOpenAccess() == false)
			runtimeService.setVariable(processInstanceId, "openAccess", false);
		else
			runtimeService.setVariable(processInstanceId, "openAccess", true);

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
