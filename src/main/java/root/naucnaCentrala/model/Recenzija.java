package root.naucnaCentrala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Recenzija {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String komentar;
	@ManyToOne
	private Rad rad;
	@ManyToOne
	private Recenzent recenzent;
	@Column
	private String ocena;
	@Column
	private String glavniOdgovorni;

	public Recenzija() {
	}

	public Recenzija(Long id, String komentar, Rad rad, Recenzent recenzent, String ocena, String glavniOdgovorni) {
		super();
		this.id = id;
		this.komentar = komentar;
		this.rad = rad;
		this.recenzent = recenzent;
		this.ocena = ocena;
		this.glavniOdgovorni = glavniOdgovorni;
	}

	public Recenzent getRecenzent() {
		return recenzent;
	}

	public void setRecenzent(Recenzent recenzent) {
		this.recenzent = recenzent;
	}

	public String getGlavniOdgovorni() {
		return glavniOdgovorni;
	}

	public void setGlavniOdgovorni(String glavniOdgovorni) {
		this.glavniOdgovorni = glavniOdgovorni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Rad getRad() {
		return rad;
	}

	public void setRad(Rad rad) {
		this.rad = rad;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}
}
