package root.naucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.Recenzent;

public interface RecenzentRepository extends JpaRepository<Recenzent,Long> {

	List<Recenzent> findAllByCasopis(Casopis casopis);

	Recenzent findByEmail(String recenzentEmail);
	
}
