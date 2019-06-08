package root.naucnaCentrala.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.RadRepository;
import root.naucnaCentrala.repository.RecenzijaRepository;
import root.naucnaCentrala.repository.UrednikRepository;

@Service
public class DodelaUlogeGlavnomUredniku implements JavaDelegate {

	@Autowired
	CasopisRepository casopisRepository;

	@Autowired
	RadRepository radRepository;

	@Autowired
	UrednikRepository urednikRepository;

	@Autowired
	RecenzijaRepository recenzijaRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String nazivCasopisa = (String) execution.getVariable("casopis");
		Casopis casopis = casopisRepository.findByNaziv(nazivCasopisa);
		execution.setVariable("glavniOdgovorni", casopis.getGlavniUrednik().getEmail());
	}

}
