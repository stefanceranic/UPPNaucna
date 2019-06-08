package root.naucnaCentrala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.naucnaCentrala.model.Korisnik;
import root.naucnaCentrala.repository.KorisnikRepository;
import root.naucnaCentrala.repository.UrednikRepository;

@Service
public class UserService {

    @Autowired
    UrednikRepository urednikRepository;

    @Autowired
    KorisnikRepository korisnikRepository;

    public Korisnik checkLogin(Korisnik korisnik){
        return korisnikRepository.findKorisnikByUsernameAndPassword(korisnik.getUsername(), korisnik.getPassword());
    }
    public Korisnik getKorisnikByEmail(String email){
        return korisnikRepository.findKorisnikByEmail(email);
    }
}
