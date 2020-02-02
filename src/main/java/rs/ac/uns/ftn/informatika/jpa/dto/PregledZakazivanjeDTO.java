package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

public class PregledZakazivanjeDTO {
	
	private long lekarID;
	private LocalDateTime termin;
	private long pacijentID;
	
	public PregledZakazivanjeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PregledZakazivanjeDTO(long lekarID, LocalDateTime termin, long pacijentID) {
		this.lekarID = lekarID;
		this.termin = termin;
		this.pacijentID = pacijentID;
	}

	public long getLekarID() {
		return lekarID;
	}

	public void setLekarID(long lekarID) {
		this.lekarID = lekarID;
	}

	public LocalDateTime getTermin() {
		return termin;
	}

	public void setTermin(LocalDateTime termin) {
		this.termin = termin;
	}

	public long getPacijentID() {
		return pacijentID;
	}

	public void setPacijentID(long pacijentID) {
		this.pacijentID = pacijentID;
	}
	
	
	
}
