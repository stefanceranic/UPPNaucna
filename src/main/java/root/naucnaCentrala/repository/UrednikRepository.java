package root.naucnaCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import root.naucnaCentrala.model.NaucnaOblast;
import root.naucnaCentrala.model.Urednik;

public interface UrednikRepository extends JpaRepository<Urednik, Long> {

    public List<Urednik> findByNaucnaOblast(NaucnaOblast n);


}
