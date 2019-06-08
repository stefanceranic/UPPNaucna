package root.naucnaCentrala.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.Recenzija;
import root.naucnaCentrala.repository.RecenzijaRepository;

@Controller
@RequestMapping("/recenzija")
public class RecenzijaController {

	@Autowired
	private RecenzijaRepository recenzijaRepository;

	@GetMapping(path = "/vratiRecenzije/{nazivRada}", produces = "application/json")
	public @ResponseBody List<Recenzija> findAllRecenzijeByRad(@PathVariable String nazivRada) {
		List<Recenzija> recenzija = recenzijaRepository.findAllByRadNaziv(nazivRada);
		return recenzija;
	}
}
