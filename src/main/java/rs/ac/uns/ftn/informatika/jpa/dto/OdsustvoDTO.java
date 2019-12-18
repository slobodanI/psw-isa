package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Odsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.TipOdsustva;

public class OdsustvoDTO 
{

	private Long id;
	
	private String pocetak;
	
	private String kraj;
	
	private String tip;
	
	private Long medSestraId;
	
	public OdsustvoDTO()
	{
		
	}

	public OdsustvoDTO(Long id, String pocetak, String kraj, String tip, Long medSestraId) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tip = tip;
		this.medSestraId = medSestraId;
	}
	
	public OdsustvoDTO(Odsustvo o)
	{
		this(o.getId(),o.getPocetak().toString(),o.getKraj().toString(),o.getTip().toString(),o.getMedSestra().getId());
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

	public Long getMedSestraId() {
		return medSestraId;
	}

	public void setMedSestraId(Long medSestraId) {
		this.medSestraId = medSestraId;
	}

	@Override
	public String toString() {
		return "OdsustvoDTO [id=" + id + ", pocetak=" + pocetak + ", kraj=" + kraj + ", tip=" + tip + ", medSestraId="
				+ medSestraId + "]";
	}

	
	
	
	
}
