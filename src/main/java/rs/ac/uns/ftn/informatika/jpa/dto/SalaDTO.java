package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Sala;

public class SalaDTO {
	
	private Long id;
	private String naziv;
	private KlinikaDTO klinika;
	
	//private Long idKlinike;
	
	
	public SalaDTO() {
		
	}
	
	public SalaDTO(Sala sala) {
		id=sala.getId();
		naziv=sala.getNaziv();
		klinika=new KlinikaDTO(sala.getKlinika());
		//idKlinike=sala.getKlinika().getId();
		
	}
	
	
	public SalaDTO(Long id, String naziv, KlinikaDTO klinika) {
		super();
		this.id = id;
		this.naziv = naziv;
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
