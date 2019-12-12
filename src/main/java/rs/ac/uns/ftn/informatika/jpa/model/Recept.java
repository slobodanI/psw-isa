package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Recept {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false, unique = true)
	private String sifraLeka;
	
	@Column(nullable =  false, unique = true)
	private String nazivLeka;
	
	@Column(nullable =  false)
	private Long lbo;
	
	@Column(nullable =  false)
	private String imePacijenta;
	
	@Column(nullable =  false)
	private String prezimePacijenta;
	
	@Column(nullable =  false)
	private Boolean overen;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private MedicinskaSestra medicinskaSestra;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pregled pregled;
	
	public Recept() {
		// TODO Auto-generated constructor stub
	}

	public Recept(Long id, String sifra_Leka, String nazivLeka, Long lbo, String imePacijenta, String prezimePacijenta,
			Boolean overen, MedicinskaSestra medicinskasestra) {
		super();
		this.id = id;
		this.sifraLeka = sifra_Leka;
		this.nazivLeka = nazivLeka;
		this.lbo = lbo;
		this.imePacijenta = imePacijenta;
		this.prezimePacijenta = prezimePacijenta;
		this.overen = overen;
		this.medicinskaSestra = medicinskasestra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSifra_Leka() {
		return sifraLeka;
	}

	public void setSifra_Leka(String sifra_Leka) {
		this.sifraLeka = sifra_Leka;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}

	public Long getLbo() {
		return lbo;
	}

	public void setLbo(Long lbo) {
		this.lbo = lbo;
	}

	public String getImePacijenta() {
		return imePacijenta;
	}

	public void setImePacijenta(String imePacijenta) {
		this.imePacijenta = imePacijenta;
	}

	public String getPrezimePacijenta() {
		return prezimePacijenta;
	}

	public void setPrezimePacijenta(String prezimePacijenta) {
		this.prezimePacijenta = prezimePacijenta;
	}

	public Boolean getOveren() {
		return overen;
	}

	public void setOveren(Boolean overen) {
		this.overen = overen;
	}

	public MedicinskaSestra getMedicinskaSestra() {
		return medicinskaSestra;
	}

	public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
		this.medicinskaSestra = medicinskaSestra;
	}

	public Pregled getPregled() {
		return pregled;
	}

	public void setPregled(Pregled pregled) {
		this.pregled = pregled;
	}

	
	
	
	
}
