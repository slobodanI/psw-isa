package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;

public class AdministratorKlinickogCentraDTO 
{
	
	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	private Boolean promenjenaLozinka;
	private String uloga = "AdministratorKlinickogCentra";
	
	public AdministratorKlinickogCentraDTO()
	{
		
	}

	public AdministratorKlinickogCentraDTO(Long id, String ime, String prezime, String username, String password,
			String email, Boolean promenjenaLozinka, String uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.promenjenaLozinka = promenjenaLozinka;
		this.uloga = uloga;
	}
	
	public AdministratorKlinickogCentraDTO(AdministratorKlinickogCentra admin)
	{
		this(admin.getId(), admin.getIme(), admin.getPrezime(), admin.getUsername(), admin.getPassword(), admin.getEmail(), admin.getPromenjenaLozinka(), admin.getUloga());
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

	@Override
	public String toString() {
		return "AdministratorKlinickogCentraDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username="
				+ username + ", password=" + password + ", email=" + email + ", promenjenaLozinka=" + promenjenaLozinka
				+ ", uloga=" + uloga + "]";
	}
	
}
