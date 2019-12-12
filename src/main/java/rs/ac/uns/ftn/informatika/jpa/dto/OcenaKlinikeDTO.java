package rs.ac.uns.ftn.informatika.jpa.dto;

public class OcenaKlinikeDTO {
	
	private Long idKlinike;
	private int ocena;
	
	public OcenaKlinikeDTO() {
		// TODO Auto-generated constructor stub
	}

	public OcenaKlinikeDTO(Long idKlinike, int ocena) {
		super();
		this.idKlinike = idKlinike;
		this.ocena = ocena;
	}

	public Long getIdKlinike() {
		return idKlinike;
	}

	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	
	
}
