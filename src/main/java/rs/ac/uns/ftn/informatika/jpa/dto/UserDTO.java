package rs.ac.uns.ftn.informatika.jpa.dto;

public class UserDTO {

	private Long id;
	private String uloga;

	public UserDTO() {
	}

	public UserDTO(Long id, String uloga) {
		super();
		this.id = id;
		this.uloga = uloga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}


}
