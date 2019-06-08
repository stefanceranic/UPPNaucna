package root.naucnaCentrala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String naziv;
	@Column
	private String apstrakt;
	@Column
	private NaucnaOblast naucnaOblast;
	@Column
	private StatusRada statusRada;
	@Column
	private String putanja;
	@Column
	private String kljucneReci;

	@ManyToOne
	public Casopis casopis;

	@ManyToOne
	public Korisnik autor;

	public Rad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rad(Long id, String naziv, String apstrakt, NaucnaOblast naucnaOblast, StatusRada statusRada, String putanja,
			Casopis casopis, Korisnik autor) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.apstrakt = apstrakt;
		this.naucnaOblast = naucnaOblast;
		this.statusRada = statusRada;
		this.putanja = putanja;
		this.casopis = casopis;
		this.autor = autor;
	}

	public String getKljucneReci() {
		return kljucneReci;
	}

	public void setKljucneReci(String kljucneReci) {
		this.kljucneReci = kljucneReci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getApstrakt() {
		return apstrakt;
	}

	public void setApstrakt(String apstrakt) {
		this.apstrakt = apstrakt;
	}

	public NaucnaOblast getNaucnaOblast() {
		return naucnaOblast;
	}

	public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
	}

	public StatusRada getStatusRada() {
		return statusRada;
	}

	public void setStatusRada(StatusRada statusRada) {
		this.statusRada = statusRada;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public Korisnik getAutor() {
		return autor;
	}

	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}

	public String getPutanja() {
		return putanja;
	}

	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}

}
