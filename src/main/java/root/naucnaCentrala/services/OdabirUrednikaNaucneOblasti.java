package root.naucnaCentrala.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.RadRepository;

@Service
public class OdabirUrednikaNaucneOblasti implements JavaDelegate {

	@Autowired
	private CasopisRepository casopisRepository;

	@Autowired
	private RadRepository radRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String nazivCasopisa = (String) execution.getVariable("casopis");
		String nazivRada = (String) execution.getVariable("naslov");
		Rad rad = radRepository.findByNaziv(nazivRada);
		Casopis casopis = casopisRepository.findByNaziv(nazivCasopisa);

		for (int j = 0; j < casopis.getUrednici().size(); j++) {
			if (casopis.getUrednici().get(j).getNaucnaOblast().equals(rad.getNaucnaOblast())) {
				execution.setVariable("glavniOdgovorni", casopis.getUrednici().get(j).getEmail());
				execution.setVariable("dalje", true);
			} else {
				execution.setVariable("dalje", false);
			}
		}
	}

}
