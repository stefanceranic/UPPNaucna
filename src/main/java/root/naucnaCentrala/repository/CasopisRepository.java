package root.naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.naucnaCentrala.model.Casopis;

@Repository
public interface CasopisRepository extends JpaRepository<Casopis, Long> {

	public Casopis findByNaziv(String nazivCasopisa);

}
