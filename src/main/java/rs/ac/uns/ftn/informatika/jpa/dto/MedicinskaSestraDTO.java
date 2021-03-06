package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;

//medicinska sestra DTO klasa
public class MedicinskaSestraDTO {

	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	private Boolean promenjenaLozinka;
	private String uloga = "MedicinskaSestra";
	private KlinikaDTO klinika;
	
	public MedicinskaSestraDTO()
	{
		
	}

	public MedicinskaSestraDTO(Long id, String ime, String prezime, String username, String password, String email,
			Boolean promenjenaLozinka, String uloga, KlinikaDTO klinika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.promenjenaLozinka = promenjenaLozinka;
		this.uloga = uloga;
		this.klinika = klinika;
	}
	
	public MedicinskaSestraDTO(MedicinskaSestra sestra)
	{
		this(sestra.getId(), sestra.getIme(), sestra.getPrezime(), sestra.getUsername(), sestra.getPassword(), sestra.getEmail(), sestra.getPromenjenaLozinka(), sestra.getUloga(), new KlinikaDTO(sestra.getKlinika()));
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

	public Boolean getPromenjenaLozinka() {
		return promenjenaLozinka;
	}

	public void setPromenjenaLozinka(Boolean promenjenaLozinka) {
		this.promenjenaLozinka = promenjenaLozinka;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public KlinikaDTO getKlinika() {
		return klinika;
	}

	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}

	@Override
	public String toString() {
		return "MedicinskaSestraDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", promenjenaLozinka=" + promenjenaLozinka
				+ ", uloga=" + uloga + ", klinika=" + klinika + "]";
	}

	
	
	
}
