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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.FormSubmissionDto;

@Controller
@RequestMapping("/urednik")
public class UrednikController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private TaskService taskService;

	@PostMapping(path = "/proveraRada/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> proveraRada(@PathVariable String taskId, @RequestBody boolean odgovor) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();

		runtimeService.setVariable(processInstanceId, "radPrihvacen", odgovor);
		taskService.complete(taskId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/proveraFormataRada/{processInstanceId}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> proveraFormataRada(@PathVariable String processInstanceId,
			@RequestBody boolean odgovor) {

		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);

		runtimeService.setVariable(processInstanceId, "ispravan", odgovor);
		taskService.complete(task.getId());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/oceni/{taskId}", produces = "application/json")
	public ResponseEntity<?> oceni(@PathVariable String taskId, @RequestBody List<FormSubmissionDto> dto) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String odluka;
		String finalnaOdluka;
		if (map.get("krajnjaOdluka") != null) {
			odluka = (String) map.get("krajnjaOdluka");
			runtimeService.setVariable(task.getProcessInstanceId(), "odluka", odluka);
		} else {
			finalnaOdluka = (String) map.get("finalnaOdluka");
			runtimeService.setVariable(task.getProcessInstanceId(), "finalnaOdluka", finalnaOdluka);
		}
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
