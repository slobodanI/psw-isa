package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lekar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String ime;
	
	@Column(nullable =  false)
	private String prezime;
	
	@Column(nullable =  true)
	private int brojOcena;
	
	@Column(nullable =  true)
	private Double ukupnaOcena;
	
	@Column(nullable =  false, unique = true)
	private String username;
	
	@Column(nullable =  false)
	private String password;
	
	@Column(nullable =  false)
	private String email;
	
	@Column(nullable =  false)
	private String radniKalendar;
	
//	@Column(nullable = true)
//	private String radnoVreme;
	
	@Column(nullable = false)
	private int radnoVremeOd;

	@Column(nullable = false)
	private int radnoVremeDo;	
	
	@OneToMany(mappedBy = "lekar",fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	private Set<LekarOdsustvo> listaOdsustava = new HashSet<LekarOdsustvo>();
	
	@OneToMany(mappedBy = "lekar",fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	private Set<ZauzetostLekara> listaZauzetostiLekara = new HashSet<ZauzetostLekara>(); // ovo se ne koristi koliko ja znam STUDENT 1

	@ManyToOne(fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private TipPregleda tipPregleda;
	
//	@Column(nullable =  false)
//	private Collection<Pacijent> listaPacijenata;
	
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<Pregled> listaZakazanihPregleda = new HashSet<Pregled>();
	
	@Column(nullable = false)
	private String uloga = "Lekar";
	
	@ManyToMany(mappedBy = "lekari")
	private Set<Operacija> operacije =  new HashSet<Operacija>();
	
	public Lekar() {
		// TODO Auto-generated constructor stub
	}

	public Lekar(Long id, String ime, String prezime,int brojOcena, Double ukupnaOcena, Collection<Pacijent> listaPacijenata,
			Collection<Pregled> listaPregleda, String radniKalendar,Klinika klinika,int radnoVremeOd,int radnoVremeDo,String username,String password) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username=username;
		this.password=password;
		this.brojOcena=brojOcena;
		this.ukupnaOcena = ukupnaOcena;
//		this.listaPacijenata = listaPacijenata;
//		this.listaPregleda = listaPregleda;
		this.radniKalendar = radniKalendar;
//		this.klinika=klinika;
//		this.radnoVreme=radnoVreme;
		this.radnoVremeOd=radnoVremeOd;
		this.radnoVremeDo=radnoVremeDo;
		
		this.uloga="Lekar";
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

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
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

//	public Collection<Pacijent> getListaPacijenata() {
//		return listaPacijenata;
//	}
//
//	public void setListaPacijenata(Collection<Pacijent> listaPacijenata) {
//		this.listaPacijenata = listaPacijenata;
//	}
//
//	public Collection<Pregled> getListaPregleda() {
//		return listaPregleda;
//	}
//
//	public void setListaPregleda(Collection<Pregled> listaPregleda) {
//		this.listaPregleda = listaPregleda;
//	}

	public String getRadniKalendar() {
		return radniKalendar;
	}

	public void setRadniKalendar(String radniKalendar) {
		this.radniKalendar = radniKalendar;
	}

	public int getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public Double getUkupnaOcena() {
		return ukupnaOcena;
	}

	public void setUkupnaOcena(Double ukupnaOcena) {
		this.ukupnaOcena = ukupnaOcena;
	}

	public Set<Pregled> getListaZakazanihPregleda() {
		return listaZakazanihPregleda;
	}

	public void setListaZakazanihPregleda(Set<Pregled> listaZakazanihPregleda) {
		this.listaZakazanihPregleda = listaZakazanihPregleda;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

//	public String getRadnoVreme() {
//		return radnoVreme;
//	}
//
//	public void setRadnoVreme(String radnoVreme) {
//		this.radnoVreme = radnoVreme;
//	}

	public int getRadnoVremeOd() {
		return radnoVremeOd;
	}

	public void setRadnoVremeOd(int radnoVremeOd) {
		this.radnoVremeOd = radnoVremeOd;
	}

	public int getRadnoVremeDo() {
		return radnoVremeDo;
	}

	public void setRadnoVremeDo(int radnoVremeDo) {
		this.radnoVremeDo = radnoVremeDo;
	}

	public Set<LekarOdsustvo> getListaOdsustava() {
		return listaOdsustava;
	}

	public void setListaOdsustava(Set<LekarOdsustvo> listaOdsustava) {
		this.listaOdsustava = listaOdsustava;
	}

	public Set<ZauzetostLekara> getListaZauzetostiLekara() {
		return listaZauzetostiLekara;
	}

	public void setListaZauzetostiLekara(Set<ZauzetostLekara> listaZauzetostiLekara) {
		this.listaZauzetostiLekara = listaZauzetostiLekara;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Set<Operacija> getOperacije() {
		return operacije;
	}

	public void setOperacije(Set<Operacija> operacije) {
		this.operacije = operacije;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
