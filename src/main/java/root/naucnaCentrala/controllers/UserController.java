package root.naucnaCentrala.controllers;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
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

import root.naucnaCentrala.model.FormFieldsDto;
import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.Korisnik;
import root.naucnaCentrala.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private UserService userService;

	@GetMapping(path = "/getRegister", produces = "application/json")
	public @ResponseBody FormFieldsDto getRegisterFF() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracija");
		System.out.println("Poceo registracioni proces");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@PostMapping(path = "/post/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> submitRegister(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		List<Task> a = taskService.createTaskQuery().active().list();
		String tI = a.get(0).getId();
		System.out.println(tI);

		Task task = taskService.createTaskQuery().taskId(tI).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "registration", dto);

		formService.submitTaskForm(tI, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/postLogin", produces = "application/json")
	public @ResponseBody ResponseEntity<?> tryLogin(@RequestBody Korisnik korisnik) {

		Korisnik k = userService.checkLogin(korisnik);
		if (k != null)
			return ResponseEntity.ok(k);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
}
