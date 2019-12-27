package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;

public class PromenaPregledaDTO 
{
	private Long id;
	private String informacije;
	private Long dijagnozaId;
	private String dijagnozaSifra;
	private String dijagnozaNaziv;
	private Long tipId;
	private String tipNaziv;
	
	public PromenaPregledaDTO()
	{
		
	}
	
	
	
	public PromenaPregledaDTO(Long id, String informacije, Long dijagnozaId, String dijagnozaSifra,
			String dijagnozaNaziv, Long tipId, String tipNaziv) {
		super();
		this.id = id;
		this.informacije = informacije;
		this.dijagnozaId = dijagnozaId;
		this.dijagnozaSifra = dijagnozaSifra;
		this.dijagnozaNaziv = dijagnozaNaziv;
		this.tipId = tipId;
		this.tipNaziv = tipNaziv;
	}



	public PromenaPregledaDTO(Pregled p)
	{
		this(p.getId(), p.getInformacije(), p.getDijagnoza().getId(), p.getDijagnoza().getSifra(), p.getDijagnoza().getNaziv(), p.getTipPregleda().getId(), p.getTipPregleda().getNaziv());
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getInformacije() {
		return informacije;
	}



	public void setInformacije(String informacije) {
		this.informacije = informacije;
	}



	public Long getDijagnozaId() {
		return dijagnozaId;
	}



	public void setDijagnozaId(Long dijagnozaId) {
		this.dijagnozaId = dijagnozaId;
	}



	public String getDijagnozaSifra() {
		return dijagnozaSifra;
	}



	public void setDijagnozaSifra(String dijagnozaSifra) {
		this.dijagnozaSifra = dijagnozaSifra;
	}



	public String getDijagnozaNaziv() {
		return dijagnozaNaziv;
	}



	public void setDijagnozaNaziv(String dijagnozaNaziv) {
		this.dijagnozaNaziv = dijagnozaNaziv;
	}


	public Long getTipId() {
		return tipId;
	}



	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}



	public String getTipNaziv() {
		return tipNaziv;
	}



	public void setTipNaziv(String tipNaziv) {
		this.tipNaziv = tipNaziv;
	}



	@Override
	public String toString() {
		return "PromenaPregledaDTO [id=" + id + ", informacije=" + informacije + ", dijagnozaId=" + dijagnozaId
				+ ", dijagnozaSifra=" + dijagnozaSifra + ", dijagnozaNaziv=" + dijagnozaNaziv + ", tipId=" + tipId
				+ ", tipNaziv=" + tipNaziv + "]";
	}



	
	
	
	
}
