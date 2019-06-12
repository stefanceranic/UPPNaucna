package root.naucnaCentrala.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.Korisnik;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.KorisnikRepository;

@Service
public class ProveraClanarine implements JavaDelegate {

	@Autowired
	CasopisRepository casopisRepository;

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PROVERA CLANARINE");
		String nazivCasopisa = (String) execution.getVariable("casopis");
		Casopis casopis = casopisRepository.findByNaziv(nazivCasopisa);
		String autor = (String) execution.getVariable("autor");
		Korisnik korisnik = korisnikRepository.findKorisnikByEmail(autor);
		boolean aktivna = casopis.getClanovi().contains(korisnik);
		execution.setVariable("aktivna", aktivna);
	}

}
