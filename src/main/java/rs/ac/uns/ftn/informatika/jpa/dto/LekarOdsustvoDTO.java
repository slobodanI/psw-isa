package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;

public class LekarOdsustvoDTO {
	
	private Long id;
	
	private String pocetak;
	
	private String kraj;
	
	private String tip;
	
	private Long lekarId;
	
	public LekarOdsustvoDTO()
	{
		
	}

	public LekarOdsustvoDTO(Long id, String pocetak, String kraj, String tip, Long lekarId) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tip = tip;
		this.lekarId = lekarId;
	}
	
	public LekarOdsustvoDTO(LekarOdsustvo o)
	{
		this(o.getId(),o.getPocetak().toString(),o.getKraj().toString(),o.getTip().toString(),o.getLekar().getId());
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Long getLekarId() {
		return lekarId;
	}

	public void setLekarId(Long lekarId) {
		this.lekarId = lekarId;
	}
	

}
