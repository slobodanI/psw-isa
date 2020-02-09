package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;

public class upisiTipPregledaDTO {
	
	private Long id;
	private String naziv;
	private Long admin;
	private Long cena;
	
	public upisiTipPregledaDTO() {
		
	}
	public upisiTipPregledaDTO(Long id,String naziv,Long admin,Long cena) {
		
		super();
		this.id=id;
		this.naziv=naziv;
		this.admin=admin;
		this.cena=cena;
		
	}
	public upisiTipPregledaDTO(TipPregleda tipPregleda) {
		id=tipPregleda.getId();
		naziv=tipPregleda.getNaziv();
		
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

	public Long getCena() {
		return cena;
	}
	public void setCena(Long cena) {
		this.cena = cena;
	}
	public Long getAdmin() {
		return admin;
	}
	public void setAdmin(Long admin) {
		this.admin = admin;
	}
	
	

}