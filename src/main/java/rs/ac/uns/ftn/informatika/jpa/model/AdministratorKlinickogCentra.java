package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdministratorKlinickogCentra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String ime;
	
	@Column(nullable =  false)
	private String prezime;
	
	@Column(unique = true,nullable =  false)
	private String username;
	
	@Column(nullable =  false)
	private String password;
	
	@Column(nullable =  false)
	private String email;
	
	@Column(nullable =  false)
	private Boolean promenjenaLozinka;
	
	
	public AdministratorKlinickogCentra(Long id, String ime, String prezime, String username, String password,
			String email, Boolean promenjenaLozinka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.promenjenaLozinka = promenjenaLozinka;
	}
	
	public AdministratorKlinickogCentra() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorKlinickogCentra other = (AdministratorKlinickogCentra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdministratorKlinickogCentra [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username="
				+ username + ", password=" + password + ", email=" + email + ", promenjenaLozinka=" + promenjenaLozinka
				+ "]";
	}
	
	
	
}
