package rs.ac.uns.ftn.informatika.jpa.dto;

public class ZavrsiPregledDTO 
{
	private Long id_pregleda;
	private String info;
	private Long id_dijagnoze;
	
	public ZavrsiPregledDTO() 
	{
		
	}

	public ZavrsiPregledDTO(Long id_pregleda, String info, Long id_dijagnoze) {
		super();
		this.id_pregleda = id_pregleda;
		this.info = info;
		this.id_dijagnoze = id_dijagnoze;
	}

	public Long getId_pregleda() {
		return id_pregleda;
	}

	public void setId_pregleda(Long id_pregleda) {
		this.id_pregleda = id_pregleda;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Long getId_dijagnoze() {
		return id_dijagnoze;
	}

	public void setId_dijagnoze(Long id_dijagnoze) {
		this.id_dijagnoze = id_dijagnoze;
	}

	@Override
	public String toString() {
		return "ZavrsiPregledDTO [id_pregleda=" + id_pregleda + ", info=" + info + ", id_dijagnoze=" + id_dijagnoze
				+ "]";
	}
	
	
}
