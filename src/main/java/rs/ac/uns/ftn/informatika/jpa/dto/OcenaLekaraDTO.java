package rs.ac.uns.ftn.informatika.jpa.dto;

public class OcenaLekaraDTO {
	
	private Long idLekara;
	private int ocena;
	
	public OcenaLekaraDTO() {
		// TODO Auto-generated constructor stub
	}

	public OcenaLekaraDTO(Long idLekara, int ocena) {
		super();
		this.idLekara = idLekara;
		this.ocena = ocena;
	}

	public Long getIdLekara() {
		return idLekara;
	}

	public void setIdLekara(Long idLekara) {
		this.idLekara = idLekara;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	
	
}
