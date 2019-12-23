package rs.ac.uns.ftn.informatika.jpa.dto;

import java.text.DecimalFormat;
import java.util.Date;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class DodavanjeLekaraDTO {

	private Long id;
	private String ime;
	private String prezime;
	private Double ukupnaOcena;
	private int brojOcena;
//	private KlinikaDTO klinika;
//	private Klinika kl;
	private String radnoVreme;
	private String username;
	private String password;
	private String uloga="Lekar";
	private Long idKlinike;
	private String radniKalendar;
//	private PacijentDTO pacijent;
//	private PregledDTO pregled;

	public DodavanjeLekaraDTO() {
		// TODO Auto-generated constructor stub
	}

	public DodavanjeLekaraDTO(Lekar lekar) {
		id = lekar.getId();
		ime = lekar.getIme();
		prezime = lekar.getPrezime();
		ukupnaOcena = lekar.getUkupnaOcena();
//		klinika = new KlinikaDTO(lekar.getKlinika());
		username = lekar.getUsername();
		password = lekar.getPassword();
		brojOcena = lekar.getBrojOcena();
		uloga = "Lekar";
//		idKlinike = lekar.getKlinika().getId();
		radniKalendar = lekar.getRadniKalendar();
		radnoVreme = lekar.getRadnoVreme();
//		kl=lekar.getKlinika();
//		pacijent=new PacijentDTO(lekar.getPacijent());
//		pregled=new PregledDTO(lekar.getPregled());

	}

	public DodavanjeLekaraDTO(String ime, String prezime, Double ukupnaOcena, int brojOcena,
			String radnoVreme, String username, String password, Long idKlinike, String radniKalendar,String uloga) {
		super();
	//	this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.ukupnaOcena = ukupnaOcena;
//		this.klinika = klinika;
		this.radnoVreme = radnoVreme;
		this.username = username;
		this.password = password;
		this.brojOcena = brojOcena;
		this.uloga =uloga;
		this.radniKalendar = radniKalendar;
		this.idKlinike = idKlinike;
		// this.kl=kl;
	}
	public DodavanjeLekaraDTO(String ime,String prezime,String password) {
		super();
		this.ime=ime;
		this.prezime=prezime;
		this.password=password;
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

//	public KlinikaDTO getKlinika() {
//		return klinika;
//	}
//
//	public void setKlinika(KlinikaDTO klinika) {
//		this.klinika = klinika;
//	}

	public String getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
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

	public Double getUkupnaOcena() {
		return ukupnaOcena;
	}

	public void setUkupnaOcena(Double ukupnaOcena) {
		this.ukupnaOcena = ukupnaOcena;
	}

	public int getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga() {
		this.uloga = "Lekar";
	}

//	public Klinika getKl() {
//		return kl;
//	}
//
//	public void setKl(Klinika kl) {
//		this.kl = kl;
//	}

	public String getRadniKalendar() {
		return radniKalendar;
	}

	public void setRadniKalendar(String radniKalendar) {
		this.radniKalendar = radniKalendar;
	}

	public Long getIdKlinike() {
		return idKlinike;
	}

	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}

}
