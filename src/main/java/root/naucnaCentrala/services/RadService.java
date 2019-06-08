package root.naucnaCentrala.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.NaucnaOblast;
import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.model.StatusRada;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.KorisnikRepository;
import root.naucnaCentrala.repository.RadRepository;

@Service
public class RadService {

	@Autowired
	private RadRepository radRepository;
	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
	private CasopisRepository casopisRepository;

	public void saveRad(Rad r) {
		radRepository.save(r);
	}

	public Rad findById(Long id) {
		return radRepository.findById(id).get();

	}

	public Rad makeRad(List<FormSubmissionDto> dto, String nazivCasopisa) {
		Rad r = new Rad();
		Casopis casopis = casopisRepository.findByNaziv(nazivCasopisa);
		for (FormSubmissionDto temp : dto) {
			if (temp.getFieldId().equals("naslov")) {
				r.setNaziv(temp.getFieldValue());
			}
			if (temp.getFieldId().equals("kljucneReci")) {
				r.setKljucneReci(temp.getFieldValue());
			} else if (temp.getFieldId().equals("apstrakt")) {
				r.setApstrakt(temp.getFieldValue());
			} else if (temp.getFieldId().equals("naucnaOblast")) {
				r.setNaucnaOblast(NaucnaOblast.Matematika);
			} else if (temp.getFieldId().equals("autor")) {
				r.setAutor(korisnikRepository.findKorisnikByUsername(temp.getFieldValue()));
			} else if (temp.getFieldId().equals("putanjaFajla")) {
				r.setPutanja("C:\\Users\\uvrnu\\Desktop\\" + temp.getFieldValue());
			}

		}
		r.setStatusRada(StatusRada.prijavljen);
		r.setCasopis(casopis);
		radRepository.save(r);
		return r;
	}

	public List<Rad> findAllRadovi() {
		List<Rad> r = radRepository.findAll();
		return r;
	}

	public Rad findByNaziv(String naziv) {
		return radRepository.findByNaziv(naziv);
	}

	public void izmeniRad(Rad rad, String naziv) {
		rad.setPutanja("C:\\Users\\uvrnu\\Desktop\\" + naziv);
		radRepository.save(rad);
	}
}
