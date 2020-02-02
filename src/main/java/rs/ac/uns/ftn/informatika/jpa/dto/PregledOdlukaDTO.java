package rs.ac.uns.ftn.informatika.jpa.dto;

public class PregledOdlukaDTO {
		
	private Long pregledID;
	private String odluka;
	
	public PregledOdlukaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PregledOdlukaDTO(long pregledID, String odluka) {
		this.pregledID = pregledID;
		this.odluka = odluka;
	}

	public Long getPregledID() {
		return pregledID;
	}

	public void setPregledID(Long pregledID) {
		this.pregledID = pregledID;
	}

	public String getOdluka() {
		return odluka;
	}

	public void setOdluka(String odluka) {
		this.odluka = odluka;
	}
	
	
	
}
