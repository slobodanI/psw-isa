package rs.ac.uns.ftn.informatika.jpa.dto;

public class ReceptIdDTO {
	private Long id_recepta;
	
	public ReceptIdDTO()
	{
		
	}

	public ReceptIdDTO(Long id_recepta) {
		super();
		this.id_recepta = id_recepta;
	}

	public Long getId_recepta() {
		return id_recepta;
	}

	public void setId_recepta(Long id_recepta) {
		this.id_recepta = id_recepta;
	}
	
	
}
