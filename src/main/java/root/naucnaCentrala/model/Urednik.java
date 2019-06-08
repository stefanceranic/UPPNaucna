package root.naucnaCentrala.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Urednik extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605997952086305940L;

	@Column
	private String titula;

	@Column
	private NaucnaOblast naucnaOblast;

	@Column
	private TipUrednika tipUrednika;

	@OneToMany
	@JoinColumn(name = "id")
	public List<Rad> radovi;

	@ManyToOne
	public Casopis casopis;

	public Urednik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Urednik(String ime, String prezime, String grad, String drzava, String email, String username,
			String password, TipKorisnika tipKorisnika, String titula) {
		super(ime, prezime, grad, drzava, email, username, password, tipKorisnika);
		this.titula = titula;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public NaucnaOblast getNaucnaOblast() {
		return naucnaOblast;
	}

	public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public TipUrednika getTipUrednika() {
		return tipUrednika;
	}

	public void setTipUrednika(TipUrednika tipUrednika) {
		this.tipUrednika = tipUrednika;
	}

	public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}
}
