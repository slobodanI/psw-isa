package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;

public class PacijentDTO {
	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	private String adresa;
	private String grad;
	private String drzava;
	private String brojTel;
	private Long lbo;
	private Boolean aktiviranNalog;
	private String uloga = "Pacijent";
	
	public PacijentDTO(Pacijent pac) {
		id = pac.getId();
		ime = pac.getIme();
		prezime = pac.getPrezime();
		username = pac.getUsername();
		password = pac.getPassword();
		email = pac.getEmail();
		adresa = pac.getAdresa();
		grad = pac.getGrad();
		drzava = pac.getDrzava();
		brojTel = pac.getBrojTel();
		lbo = pac.getLbo();
		aktiviranNalog = pac.getAktiviranNalog();
	}
	
	public PacijentDTO(Long id, String ime, String prezime, String username, String password, String email,
			String adresa, String grad, String drzava, String brojTel, Long lbo, Boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTel = brojTel;
		this.lbo = lbo;
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

	public Boolean getAktiviranNalog() {
		return aktiviranNalog;
	}

	public void setAktiviranNalog(Boolean aktiviranNalog) {
		this.aktiviranNalog = aktiviranNalog;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
	
}
