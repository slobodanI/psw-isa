package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Poruka;

public class PorukaDTO {

	private Long id;
	private Long pacijent_id;
	private String naslov;
	private String telo;
	private String email_posiljaoca;
	private String email_primaoca;
	private Boolean odgovoreno;
	
	public PorukaDTO()
	{
		
	}

	public PorukaDTO(Long id, Long pacijent_id, String naslov, String telo, String email_posiljaoca, String email_primaoca, Boolean odgovoreno) {
		super();
		this.id = id;
		this.pacijent_id = pacijent_id;
		this.naslov = naslov;
		this.telo = telo;
		this.email_posiljaoca = email_posiljaoca;
		this.email_primaoca = email_primaoca;
		this.odgovoreno = odgovoreno;
	}
	
	public PorukaDTO(Poruka poruka)
	{
		this(poruka.getId(), poruka.getPacijent_id(), poruka.getNaslov(), poruka.getTelo(), poruka.getEmail_posiljaoca(), poruka.getEmail_primaoca(), poruka.getOdgovoreno());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacijent_id() {
		return pacijent_id;
	}

	public void setPacijent_id(Long pacijent_id) {
		this.pacijent_id = pacijent_id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTelo() {
		return telo;
	}

	public void setTelo(String telo) {
		this.telo = telo;
	}

	public String getEmail_posiljaoca() {
		return email_posiljaoca;
	}

	public void setEmail_posiljaoca(String email_posiljaoca) {
		this.email_posiljaoca = email_posiljaoca;
	}

	public String getEmail_primaoca() {
		return email_primaoca;
	}

	public void setEmail_primaoca(String email_primaoca) {
		this.email_primaoca = email_primaoca;
	}

	public Boolean getOdgovoreno() {
		return odgovoreno;
	}

	public void setOdgovoreno(Boolean odgovoreno) {
		this.odgovoreno = odgovoreno;
	}

	@Override
	public String toString() {
		return "PorukaDTO [id=" + id + ", pacijent_id=" + pacijent_id + ", naslov=" + naslov + ", telo=" + telo
				+ ", email_posiljaoca=" + email_posiljaoca + ", email_primaoca=" + email_primaoca + ", odgovoreno="
				+ odgovoreno + "]";
	}

	
	
}
