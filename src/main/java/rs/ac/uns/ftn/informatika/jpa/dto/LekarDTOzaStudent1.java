package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class LekarDTOzaStudent1 {
	
	private Long id;
	private String ime;
	private String prezime;
	private Double ocena;
	
	public LekarDTOzaStudent1() {
		// TODO Auto-generated constructor stub
	}
	
	public LekarDTOzaStudent1(Lekar lekar) {
		id = lekar.getId();
		ime = lekar.getIme();
		prezime = lekar.getPrezime();
		ocena = lekar.getUkupnaOcena() / lekar.getBrojOcena();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
	
	
}
