package root.naucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import root.naucnaCentrala.model.Recenzent;
import root.naucnaCentrala.model.Recenzija;


public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {

	List<Recenzija> findAllByRadNaziv(String nazivRada);

	

}
