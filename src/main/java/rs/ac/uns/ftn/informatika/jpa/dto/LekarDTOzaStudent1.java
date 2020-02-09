package rs.ac.uns.ftn.informatika.jpa.dto;

import java.text.DecimalFormat;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class LekarDTOzaStudent1 {
	
	private Long id;
	private String ime;
	private String prezime;
	private String ocena;
	
	//da ocena ima samo dve vrednosti posle zareza
	private DecimalFormat df2 = new DecimalFormat("#.##");
	
	public LekarDTOzaStudent1() {
		// TODO Auto-generated constructor stub
	}
	
	public LekarDTOzaStudent1(Lekar lekar) {
		id = lekar.getId();
		ime = lekar.getIme();
		prezime = lekar.getPrezime();
		ocena = df2.format(lekar.getUkupnaOcena() / lekar.getBrojOcena());
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

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}
	
	
}
