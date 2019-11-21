package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Sala;

public class SalaDTO {
	
	private Long id;
	private String naziv;
	private String zauzetost;
	private KlinikaDTO klinika;
	//private Long idKlinike;
	
	
	public SalaDTO() {
		
	}
	
	public SalaDTO(Sala sala) {
		id=sala.getId();
		naziv=sala.getNaziv();
		zauzetost=sala.getZauzetost();
		klinika=new KlinikaDTO(sala.getKlinika());
		//idKlinike=sala.getKlinika().getId();
		
	}
	
	
	public SalaDTO(Long id, String naziv, String zauzetost, KlinikaDTO klinika) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.zauzetost = zauzetost;
		this.klinika = klinika;
	//	this.idKlinike=idKlinike;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZauzetost() {
		return zauzetost;
	}

	public void setZauzetost(String zauzetost) {
		this.zauzetost = zauzetost;
	}

	public KlinikaDTO getKlinika() {
		return klinika;
	}

	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}

//	public Long getIdKlinike() {
//		return idKlinike;
//	}
//
//	public void setIdKlinike(Long idKlinike) {
//		this.idKlinike = idKlinike;
//	}
//	
//	
//	
	
	
}
