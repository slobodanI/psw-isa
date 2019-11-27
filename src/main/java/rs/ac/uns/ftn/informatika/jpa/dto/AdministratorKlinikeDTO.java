package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;

public class AdministratorKlinikeDTO 
{

	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	private KlinikaDTO klinika;
	private Boolean promenjenaLozinka;
	private String uloga = "AdministratorKlinike";
	
	public AdministratorKlinikeDTO()
	{
		
	}
	
	public AdministratorKlinikeDTO(Long id, String ime, String prezime, String username, String password, String email,
			KlinikaDTO klinika, Boolean promenjenaLozinka, String uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.klinika = klinika;
		this.promenjenaLozinka = promenjenaLozinka;
		this.uloga = uloga;
	}
	
	public AdministratorKlinikeDTO(AdministratorKlinike admin)
	{
		this(admin.getId(), admin.getIme(), admin.getPrezime(), admin.getUsername(), admin.getPassword(), admin.getEmail(), new KlinikaDTO(admin.getKlinika()) ,admin.getPromenjenaLozinka(), admin.getUloga());
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

	public KlinikaDTO getKlinika() {
		return klinika;
	}

	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
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

	@Override
	public String toString() {
		return "AdministratorKlinikeDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", klinika=" + klinika + ", promenjenaLozinka="
				+ promenjenaLozinka + ", uloga=" + uloga + "]";
	}

	
}
