package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;

public class PregledKalendarDTO 
{
	private Long id;
	private Long pac_id;
	private Long lbo;
	private String ime;
	private String prezime;
	
	public PregledKalendarDTO()
	{
		
	}

	public PregledKalendarDTO(Long id, Long pac_id, Long lbo, String ime, String prezime) {
		super();
		this.id = id;
		this.pac_id = pac_id;
		this.lbo = lbo;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public PregledKalendarDTO(Pregled p)
	{
		this(p.getId(),p.getPacijent().getId(),p.getPacijent().getLbo(),p.getPacijent().getIme(),p.getPacijent().getPrezime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPac_id() {
		return pac_id;
	}

	public void setPac_id(Long pac_id) {
		this.pac_id = pac_id;
	}

	public Long getLbo() {
		return lbo;
	}

	public void setLbo(Long lbo) {
		this.lbo = lbo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return "PregledKalendarDTO [id=" + id + ", pac_id=" + pac_id + ", lbo=" + lbo + ", ime=" + ime + ", prezime="
				+ prezime + "]";
	}
	
	
}
