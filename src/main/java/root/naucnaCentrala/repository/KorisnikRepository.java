package root.naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.naucnaCentrala.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    public Korisnik findKorisnikByUsernameAndPassword(String username, String password);
    public Korisnik findKorisnikByUsername(String username);
    public Korisnik findKorisnikByEmail(String email);
}
