package rs.ac.uns.ftn.informatika.jpa.dto;

public class OperacijaKalendarDTO 
{
	private Long id;
	private String pocetak;
	private String kraj;
	
	public OperacijaKalendarDTO()
	{
		
	}
	
	public OperacijaKalendarDTO(Long id, String pocetak, String kraj) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
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

	@Override
	public String toString() {
		return "OperacijaKalendarDTO [id=" + id + ", pocetak=" + pocetak + ", kraj=" + kraj + "]";
	}
	
	
}
