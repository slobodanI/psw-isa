package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ZdravstveniKarton {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String krvnaGrupa;
	
	@Column(nullable =  false)
	private String dioptrija;
	
	@Column(nullable =  false)
	private Double visina;
	
	@Column(nullable =  false)
	private Double tezina;
	
	@Column(nullable =  false)
	private String alergije;
	
	@OneToMany(mappedBy = "zdravstveniKarton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> listaPregleda = new HashSet<Pregled>();
	
	@OneToMany(mappedBy = "zdravstveniKarton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Operacija> listaOperacija = new HashSet<Operacija>();
	
	@Column(nullable =  false)
	private String listaBolesti;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	public ZdravstveniKarton() {
		// TODO Auto-generated constructor stub
	}
		
	
	public ZdravstveniKarton(Long id, String krvnaGrupa, String dioptrija, Double visina, Double tezina,
			String alergije, Set<Pregled> listaPregleda, String listaBolesti) {
		super();
		this.id = id;
		this.krvnaGrupa = krvnaGrupa;
		this.dioptrija = dioptrija;
		this.visina = visina;
		this.tezina = tezina;
		this.alergije = alergije;
		this.listaPregleda = listaPregleda;
		this.listaBolesti = listaBolesti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public String getDioptrija() {
		return dioptrija;
	}

	public void setDioptrija(String dioptrija) {
		this.dioptrija = dioptrija;
	}

	public Double getVisina() {
		return visina;
	}

	public void setVisina(Double visina) {
		this.visina = visina;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public String getAlergije() {
		return alergije;
	}

	public void setAlergije(String alergije) {
		this.alergije = alergije;
	}

	public Set<Pregled> getListaPregleda() {
		return listaPregleda;
	}

	public void setListaPregleda(Set<Pregled> listaPregleda) {
		this.listaPregleda = listaPregleda;
	}

	public String getListaBolesti() {
		return listaBolesti;
	}

	public void setListaBolesti(String listaBolesti) {
		this.listaBolesti = listaBolesti;
	}


	public Set<Operacija> getListaOperacija() {
		return listaOperacija;
	}


	public void setListaOperacija(Set<Operacija> listaOperacija) {
		this.listaOperacija = listaOperacija;
	}


	public Pacijent getPacijent() {
		return pacijent;
	}


	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
	
	
	
	
	
}
