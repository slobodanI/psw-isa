package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;

public class TipPregledaDTO {
	
	private Long id;
	private String naziv;
	
	public TipPregledaDTO() {
		
	}
	public TipPregledaDTO(Long id,String naziv) {
		
		super();
		this.id=id;
		this.naziv=naziv;
		
	}
	public TipPregledaDTO(TipPregleda tipPregleda) {
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
	
	

}
