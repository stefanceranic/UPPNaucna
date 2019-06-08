package root.naucnaCentrala.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Casopis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4793616280342947769L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String naziv;

	@Column
	private TipCasopisa tipCasopisa;

	@ManyToOne
	public Urednik glavniUrednik;

	@OneToMany
	public List<Urednik> urednici;

	@ManyToMany(targetEntity = Recenzent.class)
	@JoinTable(name = "recenzenti_casopisa", joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recenzent_id", referencedColumnName = "id"))
	@JsonIgnore
	public List<Recenzent> recenzenti;

	@Column
	private Long issn;

	@Column
	private boolean openAccess;

	public Casopis() {
	}

	public Casopis(Long id, String naziv, TipCasopisa tipCasopisa, Urednik glavniUrednik, List<Urednik> urednici,
			List<Recenzent> recenzenti, Long issn, boolean openAccess) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tipCasopisa = tipCasopisa;
		this.glavniUrednik = glavniUrednik;
		this.urednici = urednici;
		this.recenzenti = recenzenti;
		this.issn = issn;
		this.openAccess = openAccess;
	}

	public Urednik getGlavniUrednik() {
		return glavniUrednik;
	}

	public void setGlavniUrednik(Urednik glavniUrednik) {
		this.glavniUrednik = glavniUrednik;
	}

	public boolean isOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public TipCasopisa getTipCasopisa() {
		return tipCasopisa;
	}

	public void setTipCasopisa(TipCasopisa tipCasopisa) {
		this.tipCasopisa = tipCasopisa;
	}

	public List<Urednik> getUrednici() {
		return urednici;
	}

	public void setUrednici(List<Urednik> urednici) {
		this.urednici = urednici;
	}

	public List<Recenzent> getRecenzenti() {
		return recenzenti;
	}

	public void setRecenzenti(List<Recenzent> recenzenti) {
		this.recenzenti = recenzenti;
	}

	public Long getIssn() {
		return issn;
	}

	public void setIssn(Long issn) {
		this.issn = issn;
	}
}
