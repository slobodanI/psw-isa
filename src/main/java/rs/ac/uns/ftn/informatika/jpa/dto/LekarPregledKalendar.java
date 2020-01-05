package rs.ac.uns.ftn.informatika.jpa.dto;

public class LekarPregledKalendar 
{
	private Long id;
	
	private String pocetak;
	
	private String kraj;
	
	private String tip;
	
	private Long lekarId;
	
	public LekarPregledKalendar()
	{
		
	}

	public LekarPregledKalendar(Long id, String pocetak, String kraj, String tip, Long lekarId) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tip = tip;
		this.lekarId = lekarId;
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

	@Override
	public String toString() {
		return "LekarPregledKalendar [id=" + id + ", pocetak=" + pocetak + ", kraj=" + kraj + ", tip=" + tip
				+ ", lekarId=" + lekarId + "]";
	}
	
	
}
