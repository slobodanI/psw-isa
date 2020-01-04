package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;


public class PregledDTO {
	
	private Long id;
	private String informacije;
	private Dijagnoza dijagnoza;
	//private Set<Recept> recepti = new HashSet<Recept>();
	private String datumPregleda;
	private String satnica;	
//	@Column(nullable =  false)
//	private Sala sala;
	private LekarDTOzaStudent1 lekar;
	private int popust;
	private TipPregleda tipPregleda;
	private int cena;
	
	public PregledDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PregledDTO(Pregled pregled) {
		id = pregled.getId();
		informacije = pregled.getInformacije();
		dijagnoza = pregled.getDijagnoza();		
//		datumPregleda = pregled.getDatumPregleda();
//		satnica = pregled.getSatnica();			
		lekar = new LekarDTOzaStudent1(pregled.getLekar());				
		popust = pregled.getPopust();
		tipPregleda = pregled.getTipPregleda();
		cena = pregled.getCena();
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

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public String getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(String datumPregleda) {
		this.datumPregleda = datumPregleda;
	}

	public String getSatnica() {
		return satnica;
	}

	public void setSatnica(String satnica) {
		this.satnica = satnica;
	}

	public LekarDTOzaStudent1 getLekar() {
		return lekar;
	}

	public void setLekar(LekarDTOzaStudent1 lekar) {
		this.lekar = lekar;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
	
	
	
}
