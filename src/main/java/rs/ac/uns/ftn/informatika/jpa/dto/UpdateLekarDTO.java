package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class UpdateLekarDTO {
	
	private Long id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	
	public UpdateLekarDTO() {
		// TODO Auto-generated constructor stub
	}

	public UpdateLekarDTO(Lekar lekar) {
		
		id=lekar.getId();
		ime = lekar.getIme();
		prezime = lekar.getPrezime();
		username = lekar.getUsername();
		password = lekar.getPassword();

	}
	
	public UpdateLekarDTO(String ime, String prezime,
			 String username, String password,Long id) {
		super();
		this.id=id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
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
	
	
}
