package root.naucnaCentrala.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.Korisnik;
import root.naucnaCentrala.model.TipKorisnika;
import root.naucnaCentrala.repository.KorisnikRepository;

@Service
public class MakeUserService implements JavaDelegate {

	@Autowired
	IdentityService identityService;

	@Autowired
	KorisnikRepository korisnikRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		List<FormSubmissionDto> registration = (List<FormSubmissionDto>) execution.getVariable("registration");
		User user = identityService.newUser("242");
		Korisnik a = new Korisnik();
		for (FormSubmissionDto formField : registration) {

			if (formField.getFieldId().equals("username")) {
                a.setUsername(formField.getFieldValue());
				user.setId(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("password")) {
				user.setPassword(formField.getFieldValue());
			    a.setPassword(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("email")) {
				user.setEmail(formField.getFieldValue());
				a.setEmail(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("first")) {
				user.setFirstName(formField.getFieldValue());
			    a.setIme(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("last")) {
				user.setLastName(formField.getFieldValue());
				a.setPrezime(formField.getFieldValue());
			}
		}
		a.setTipKorisnika(TipKorisnika.autor);
		korisnikRepository.save(a);
		identityService.saveUser(user);
	}
}
