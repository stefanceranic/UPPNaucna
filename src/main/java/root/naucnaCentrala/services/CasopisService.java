package root.naucnaCentrala.services;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.RadRepository;
import root.naucnaCentrala.repository.RecenzijaRepository;
import root.naucnaCentrala.repository.UrednikRepository;

@Service
public class CasopisService {

	@Autowired
	CasopisRepository casopisRepository;

	@Autowired
	RadRepository radRepository;

	@Autowired
	UrednikRepository urednikRepository;

	@Autowired
	RecenzijaRepository recenzijaRepository;

	@Autowired
	TaskService taskService;

	@Autowired
	RuntimeService runtimeService;

	public Casopis pronadjiCasopis(Long idd) {
		Casopis c = casopisRepository.findById(idd).get();
		return c;
	}

	public List<Casopis> pronadjiSveCasopise() {
		List<Casopis> c = casopisRepository.findAll();
		return c;
	}

}
