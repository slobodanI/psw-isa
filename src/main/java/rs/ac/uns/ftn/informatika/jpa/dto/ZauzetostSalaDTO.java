package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostSala;

public class ZauzetostSalaDTO {
	
	private Long id;
	private String pocetak;
	private String kraj;
	private Long salaId;
	
	public ZauzetostSalaDTO() {
		
	}
	
	public ZauzetostSalaDTO(Long id,String pocetak,String kraj,Long salaId) {
		super();
		this.id=id;
		this.pocetak=pocetak;
		this.kraj=kraj;
		this.salaId=salaId;
	}
	public ZauzetostSalaDTO(ZauzetostSala z) {
		this(z.getId(),z.getPocetak().toString(),z.getKraj().toString(),z.getSala().getId());
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

	public Long getSalaId() {
		return salaId;
	}

	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}
	

}
