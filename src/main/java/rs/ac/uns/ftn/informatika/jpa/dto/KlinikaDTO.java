package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;

public class KlinikaDTO {
	
	private Long id;
	private String naziv;
	private String opis;
	private String slobodniTerminiPregleda;
	
	public KlinikaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public KlinikaDTO(Klinika klinika) {
		this(klinika.getId(),klinika.getNaziv(),klinika.getOpis(),klinika.getSlobodniTerminiPregleda());
	}
	
	public KlinikaDTO(Long id, String naziv, String opis, String slobodniTerminiPregleda) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.slobodniTerminiPregleda = slobodniTerminiPregleda;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlobodniTerminiPregleda() {
		return slobodniTerminiPregleda;
	}

	public void setSlobodniTerminiPregleda(String slobodniTerminiPregleda) {
		this.slobodniTerminiPregleda = slobodniTerminiPregleda;
	}
	
	
}
