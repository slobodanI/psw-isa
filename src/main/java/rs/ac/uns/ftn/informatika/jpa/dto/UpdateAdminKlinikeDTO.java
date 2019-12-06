package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;

public class UpdateAdminKlinikeDTO {
	
	
	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	
	public UpdateAdminKlinikeDTO() {
		
	}
	
	public UpdateAdminKlinikeDTO(AdministratorKlinike admin) {
		id=admin.getId();
		ime=admin.getIme();
		prezime=admin.getPrezime();
		username=admin.getUsername();
		password=admin.getPassword();
		email=admin.getEmail();
	}
	
	public UpdateAdminKlinikeDTO(String ime,String prezime,String username,String password,String email,Long id) {
		super();
		this.id=id;
		this.ime=ime;
		this.prezime=prezime;
		this.username=username;
		this.password=password;
		this.email=email;
		
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
	

}
