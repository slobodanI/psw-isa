package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Sala;

public class DodajSaluDTO {
	
	private Long id;
	private String naziv;
	private String zauzetost;
	private Long idKlinike;
//	private KlinikaDTO klinika;
	//private Long idKlinike;
	
	
	public DodajSaluDTO() {
		
	}
	
	public DodajSaluDTO(Sala sala) {
		id=sala.getId();
		naziv=sala.getNaziv();
		zauzetost=sala.getZauzetost();
//		klinika=new KlinikaDTO(sala.getKlinika());
		//idKlinike=sala.getKlinika().getId();
		
	}
	
	
	public DodajSaluDTO(String naziv, String zauzetost, Long idKlinike) {
		super();
		this.naziv = naziv;
		this.zauzetost = zauzetost;
		this.idKlinike=idKlinike;
//		this.klinika = klinika;
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

	public Long getIdKlinike() {
		return idKlinike;
	}

	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}
	

//	public KlinikaDTO getKlinika() {
//		return klinika;
//	}
//
//	public void setKlinika(KlinikaDTO klinika) {
//		this.klinika = klinika;
//	}

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