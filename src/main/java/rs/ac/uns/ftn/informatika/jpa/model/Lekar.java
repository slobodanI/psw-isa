package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Collection;
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

@Entity
public class Lekar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String ime;
	
	@Column(nullable =  false)
	private String prezime;
	
	@Column(nullable =  false)
	private int brojOcena;
	
	@Column(nullable =  false)
	private Double ukupnaOcena;
	
	@Column(nullable =  false, unique = true)
	private String username;
	
	@Column(nullable =  false)
	private String password;
	
	@Column(nullable =  false)
	private String radniKalendar;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	
//	@Column(nullable =  false)
//	private Collection<Pacijent> listaPacijenata;
	
	@OneToMany(mappedBy = "lekar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> listaZakazanihPregleda = new HashSet<Pregled>();
	
	@Column(nullable = false)
	private String uloga = "Lekar";
	
	public Lekar() {
		// TODO Auto-generated constructor stub
	}

	public Lekar(Long id, String ime, String prezime, Double ukupnaOcena, Collection<Pacijent> listaPacijenata,
			Collection<Pregled> listaPregleda, String radniKalendar,Klinika klinika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.ukupnaOcena = ukupnaOcena;
//		this.listaPacijenata = listaPacijenata;
//		this.listaPregleda = listaPregleda;
		this.radniKalendar = radniKalendar;
		this.klinika=klinika;
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

	public Double getukupnaOcena() {
		return ukupnaOcena;
	}

	public void setukupnaOcena(Double ukupnaOcena) {
		this.ukupnaOcena = ukupnaOcena;
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
	
	
	
	
}
