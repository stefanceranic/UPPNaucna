package root.naucnaCentrala.services;

import java.util.List;

import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.Recenzent;
import root.naucnaCentrala.repository.RecenzentRepository;

@Service
public class RecenzentService {

	private RecenzentRepository recenzentRepository;

	public List<Recenzent> findAllRecenzentiByCasopis(Casopis casopis) {
		// TODO Auto-generated method stub
		return recenzentRepository.findAllByCasopis(casopis);
	}
	
	
}
