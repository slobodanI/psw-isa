package rs.ac.uns.ftn.informatika.jpa.model;

public class PretragaSale {
	
	private String naziv;
	private Double id;
	private String zauzetost;
	
	public PretragaSale() {
		
	}
	
	public PretragaSale(String naziv, Double id, String zauzetost) {
		super();
		this.naziv = naziv;
		this.id = id;
		this.zauzetost = zauzetost;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getZauzetost() {
		return zauzetost;
	}

	public void setZauzetost(String zauzetost) {
		this.zauzetost = zauzetost;
	}

	
	
}
