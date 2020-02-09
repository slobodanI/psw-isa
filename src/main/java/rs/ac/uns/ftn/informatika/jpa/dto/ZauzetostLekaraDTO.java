package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;

public class ZauzetostLekaraDTO {

	private Long id;
	private String pocetak;
	private String kraj;
	private Long lekarId;
	
	public ZauzetostLekaraDTO() {
		
	}
	
	public ZauzetostLekaraDTO(Long id,String pocetak,String kraj,Long lekarId) {
		super();
		this.id=id;
		this.pocetak=pocetak;
		this.kraj=kraj;
		this.lekarId=lekarId;
	}
	
	public ZauzetostLekaraDTO(ZauzetostLekara z) {
		this(z.getId(),z.getPocetak().toString(),z.getKraj().toString(),z.getLekar().getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Long getLekarId() {
		return lekarId;
	}

	public void setLekarId(Long lekarId) {
		this.lekarId = lekarId;
	}
	
	
	
}
