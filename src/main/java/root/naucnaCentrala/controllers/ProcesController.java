package root.naucnaCentrala.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.FormFieldsDto;
import root.naucnaCentrala.model.TaskDTO;

@Controller
@RequestMapping("/process")
public class ProcesController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@GetMapping(path = "/getRFF", produces = "application/json")
	public @ResponseBody FormFieldsDto getFieldsF() {

		Task task = taskService.createTaskQuery().active().list().get(0);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("Glavni").active().list()
				.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@GetMapping(path = "/start", produces = "application/json")
	public @ResponseBody FormFieldsDto startProcess() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Glavni");
		System.out.println("Poceo glavni proces");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@GetMapping(path = "/getFF/{processId}", produces = "application/json")
	public @ResponseBody FormFieldsDto getFormFields(@PathVariable String processId) {

		Task task = taskService.createTaskQuery().processInstanceId(processId).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), processId, properties);
	}

	@GetMapping(value = "/aproveTasks/{email}")
	public ResponseEntity<?> getTasksAprove(@PathVariable String email) {
		List<Task> tasks = taskService.createTaskQuery().active().taskAssignee(email).list();
		List<Task> tasksForSend = new ArrayList<>();
		for (Task task : tasks) {
			if (task.getTaskDefinitionKey().equals("UserTask_0zmqcd2"))
				tasksForSend.add(task);
		}

		List<TaskDTO> tasksDto = new ArrayList<>();
		for (Task task1 : tasksForSend) {
			String filename = (String) runtimeService.getVariable(task1.getProcessInstanceId(), "naslov");
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setTaskId(task1.getId());
			taskDTO.setName(task1.getName());
			taskDTO.setRad(filename);
			taskDTO.setProcessId(task1.getProcessInstanceId());
			tasksDto.add(taskDTO);
		}
		return new ResponseEntity<>(tasksDto, HttpStatus.OK);
	}

	@GetMapping(value = "/odabirRecenzenata/{email}")
	public ResponseEntity<?> getTasksOdabirRecenzenata(@PathVariable String email) {
		List<Task> tasks = taskService.createTaskQuery().active().taskAssignee(email).list();
		List<Task> tasksForSend = new ArrayList<>();
		for (Task task : tasks) {
			if (task.getTaskDefinitionKey().equals("Task_0tljzv5"))
				tasksForSend.add(task);
		}

		List<TaskDTO> tasksDto = new ArrayList<>();
		for (Task task1 : tasksForSend) {
			String filename = (String) runtimeService.getVariable(task1.getProcessInstanceId(), "naslov");
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setTaskId(task1.getId());
			taskDTO.setName(task1.getName());
			taskDTO.setRad(filename);
			taskDTO.setProcessId(task1.getProcessInstanceId());
			tasksDto.add(taskDTO);
		}
		return new ResponseEntity<>(tasksDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pregledRecenzija/{email}")
	public ResponseEntity<?> pregledRecenzija(@PathVariable String email) {
		List<Task> tasks = taskService.createTaskQuery().active().taskAssignee(email).list();
		List<Task> tasksForSend = new ArrayList<>();
		for (Task task : tasks) {
			if (task.getTaskDefinitionKey().equals("Task_00ze36f") || task.getTaskDefinitionKey().equals("Task_1r5jyli"))
				tasksForSend.add(task);
		}

		List<TaskDTO> tasksDto = new ArrayList<>();
		for (Task task1 : tasksForSend) {
			String filename = (String) runtimeService.getVariable(task1.getProcessInstanceId(), "naslov");
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setTaskId(task1.getId());
			taskDTO.setName(task1.getName());
			taskDTO.setRad(filename);
			taskDTO.setProcessId(task1.getProcessInstanceId());
			tasksDto.add(taskDTO);
		}
		return new ResponseEntity<>(tasksDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/taskoviZaIspravak/{email}")
	public ResponseEntity<?> taskoviZaIspravak(@PathVariable String email) {
		List<Task> tasks = taskService.createTaskQuery().active().taskAssignee(email).list();
		List<Task> tasksForSend = new ArrayList<>();
		for (Task task : tasks) {
			if (task.getTaskDefinitionKey().equals("Task_0s1ey3x")
					|| task.getTaskDefinitionKey().equals("Task_0n6a54i"))
				tasksForSend.add(task);
		}

		List<TaskDTO> tasksDto = new ArrayList<>();
		for (Task task1 : tasksForSend) {
			String filename = (String) runtimeService.getVariable(task1.getProcessInstanceId(), "naslov");
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setTaskId(task1.getId());
			taskDTO.setName(task1.getName());
			taskDTO.setRad(filename);
			taskDTO.setProcessId(task1.getProcessInstanceId());
			tasksDto.add(taskDTO);
		}
		return new ResponseEntity<>(tasksDto, HttpStatus.OK);
	}

	@GetMapping(value = "/recTasks/{email}")
	public ResponseEntity<?> getTasksRec(@PathVariable String email) {
		List<Task> tasks = taskService.createTaskQuery().active().taskAssignee(email).list();
		List<Task> tasksForSend = new ArrayList<>();
		for (Task task : tasks) {
			if (task.getTaskDefinitionKey().equals("Task_0ryw7y1"))
				tasksForSend.add(task);
		}

		List<TaskDTO> tasksDto = new ArrayList<>();
		for (Task task1 : tasksForSend) {
			String filename = (String) runtimeService.getVariable(task1.getProcessInstanceId(), "naslov");
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setTaskId(task1.getId());
			taskDTO.setName(task1.getName());
			taskDTO.setRad(filename);
			taskDTO.setProcessId(task1.getProcessInstanceId());
			tasksDto.add(taskDTO);
		}
		return new ResponseEntity<>(tasksDto, HttpStatus.OK);
	}
}
