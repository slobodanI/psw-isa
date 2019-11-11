package rs.ac.uns.ftn.informatika.jpa.model;

//videti kako za enumeraciju
public class Cenovnik {
	
	private Long id;
	private TipPregleda tipPregleda;
	private Double cena;
	public Cenovnik(Long id, TipPregleda tipPregleda, Double cena) {
		super();
		this.id = id;
		this.tipPregleda = tipPregleda;
		this.cena = cena;
	}
	
	public Cenovnik() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	

}
