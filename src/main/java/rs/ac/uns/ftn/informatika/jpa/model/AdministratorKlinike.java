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
public class AdministratorKlinike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String ime;
	
	@Column(nullable =  false)
	private String prezime;
	
	@Column(nullable =  false, unique = true)
	private String username;
	
	@Column(nullable =  false)
	private String password;
	
	@Column(nullable =  false)
	private String email;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Klinika klinika;
	
	@Column(nullable =  false)
	private Boolean promenjenaLozinka;

	@Column(nullable = false)
	private String uloga = "AdministratorKlinike";
	
	public AdministratorKlinike() {
		// TODO Auto-generated constructor stub
	}

	public AdministratorKlinike(Long id, String ime, String prezime, String username, String password, String email,
			Klinika klinika, Boolean promenjenaLozinka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.klinika = klinika;
		this.promenjenaLozinka = promenjenaLozinka;
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

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
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
		return "AdministratorKlinike [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", klinika=" + klinika + ", promenjenaLozinka="
				+ promenjenaLozinka + ", uloga=" + uloga + "]";
	}
	
}
