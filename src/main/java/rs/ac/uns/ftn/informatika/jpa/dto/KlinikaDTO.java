package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;

public class KlinikaDTO {
	
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private String slobodniTerminiPregleda;
	private float latitude;
	private float longitude;
	
	public KlinikaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public KlinikaDTO(Klinika klinika) {
		this(klinika.getId(),klinika.getNaziv(),klinika.getAdresa(),klinika.getOpis(),klinika.getSlobodniTerminiPregleda(),klinika.getLatitude(),klinika.getLongitude());
	}
	
	public KlinikaDTO(Long id, String naziv, String adresa, String opis, String slobodniTerminiPregleda, float latitude, float longitude) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTerminiPregleda = slobodniTerminiPregleda;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	
	
	
}
