package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;

public class StariPregledDTO 
{
	private Long id;
	
	private String pacijentIme;
	
	private String pacijentPrezime;
	
	private String dijagnozaNaziv;
	
	private String tipPregledaNaziv;
	
	public StariPregledDTO()
	{
		
	}
	
	public StariPregledDTO(Pregled p)
	{
		this(p.getId(), p.getPacijent().getIme(), p.getPacijent().getPrezime(), p.getDijagnoza().getNaziv(), p.getTipPregleda().getNaziv());
	}

	public StariPregledDTO(Long id, String pacijentIme, String pacijentPrezime, String dijagnozaNaziv,
			String tipPregledaNaziv) {
		super();
		this.id = id;
		this.pacijentIme = pacijentIme;
		this.pacijentPrezime = pacijentPrezime;
		this.dijagnozaNaziv = dijagnozaNaziv;
		this.tipPregledaNaziv = tipPregledaNaziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPacijentIme() {
		return pacijentIme;
	}

	public void setPacijentIme(String pacijentIme) {
		this.pacijentIme = pacijentIme;
	}

	public String getPacijentPrezime() {
		return pacijentPrezime;
	}

	public void setPacijentPrezime(String pacijentPrezime) {
		this.pacijentPrezime = pacijentPrezime;
	}

	public String getDijagnozaNaziv() {
		return dijagnozaNaziv;
	}

	public void setDijagnozaNaziv(String dijagnozaNaziv) {
		this.dijagnozaNaziv = dijagnozaNaziv;
	}

	public String getTipPregledaNaziv() {
		return tipPregledaNaziv;
	}

	public void setTipPregledaNaziv(String tipPregledaNaziv) {
		this.tipPregledaNaziv = tipPregledaNaziv;
	}

	@Override
	public String toString() {
		return "StariPregledDTO [id=" + id + ", pacijentIme=" + pacijentIme + ", pacijentPrezime=" + pacijentPrezime
				+ ", dijagnozaNaziv=" + dijagnozaNaziv + ", tipPregledaNaziv=" + tipPregledaNaziv + "]";
	}
	
	
}
