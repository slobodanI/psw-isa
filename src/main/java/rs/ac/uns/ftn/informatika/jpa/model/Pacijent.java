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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pacijent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String ime;
	
	@Column(nullable =  false)
	private String prezime;
	
	@OneToOne(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ZdravstveniKarton zdravstveniKarton;
		
	@Column(nullable =  false, unique = true)
	private String username;
	
	@Column(nullable =  false)
	private String password;
	
	@Column(nullable =  false)
	private String email;
	
	@Column(nullable =  false)
	private String adresa;
	
	@Column(nullable =  false)
	private String grad;
	
	@Column(nullable =  false)
	private String drzava;
	
	@Column(nullable =  false)
	private String brojTel;
	
	@Column(nullable =  false, unique = true)
	private Long lbo;
	
	//U KOJOJ SVE KLINICI JE BIO PACIJENT, TREBA STUDENTU 2
	@ManyToMany
	@JoinTable(name = "obavljenPregled", joinColumns = @JoinColumn(name = "klinika_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pacijent_id", referencedColumnName = "id"))
	private Set<Klinika> klinike =  new HashSet<Klinika>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> zakazaniPregledi = new HashSet<Pregled>();
	
//	@ManyToMany
//	@JoinTable(name = "obavljeneOperacije", joinColumns = @JoinColumn(name = "klinika_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pacijent_id", referencedColumnName = "id"))
//	private Set<Operacija> obavljeneOperacije =  new HashSet<Operacija>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Operacija> zakazaneOperacije = new HashSet<Operacija>();
	
	@Column(nullable =  false)
	private Boolean aktiviranNalog;
	
	@Column(nullable = false)
	private String uloga = "Pacijent";
	
	public Pacijent() {
		// TODO Auto-generated constructor stub
	}

	public Pacijent(Long id, String ime, String prezime, /*ZdravstveniKarton zdravstveniKarton,*/ String username,
			String password, String email, String adresa, String grad, String drzava, String brojTel, Long lbo,
			/*Collection<Pregled> listaPregleda,*/ Boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
//		this.zdravstveniKarton = zdravstveniKarton;
		this.username = username;
		this.password = password;
		this.email = email;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTel = brojTel;
		this.lbo = lbo;
//		this.listaPregleda = listaPregleda;
		this.aktiviranNalog = aktiviranNalog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public ZdravstveniKarton getZdravstveniKarton() {
		return zdravstveniKarton;
	}

	public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
		this.zdravstveniKarton = zdravstveniKarton;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getBrojTel() {
		return brojTel;
	}

	public void setBrojTel(String brojTel) {
		this.brojTel = brojTel;
	}

	public Long getLbo() {
		return lbo;
	}

	public void setLbo(Long lbo) {
		this.lbo = lbo;
	}

//	public Collection<Pregled> getListaPregleda() {
//		return listaPregleda;
//	}
//
//	public void setListaPregleda(Collection<Pregled> listaPregleda) {
//		this.listaPregleda = listaPregleda;
//	}

	public Boolean getAktiviranNalog() {
		return aktiviranNalog;
	}

	public void setAktiviranNalog(Boolean aktiviranNalog) {
		this.aktiviranNalog = aktiviranNalog;
	}

	public Set<Klinika> getKlinike() {
		return klinike;
	}

	public void setKlinike(Set<Klinika> klinike) {
		this.klinike = klinike;
	}

	public Set<Pregled> getZakazaniPregledi() {
		return zakazaniPregledi;
	}

	public void setZakazaniPregledi(Set<Pregled> zakazaniPregledi) {
		this.zakazaniPregledi = zakazaniPregledi;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
}
