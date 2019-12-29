package rs.ac.uns.ftn.informatika.jpa.dto;

import java.text.DecimalFormat;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class KlinikaDTOzaStudent1PRETRAGA {

	private Long id;
	private String naziv;
	private String ocena;
	private String adresa;
	private double cena;
	
	//da ocena ima samo dve vrednosti posle zareza
	private DecimalFormat df2 = new DecimalFormat("#.##");
	
	public KlinikaDTOzaStudent1PRETRAGA() {
		// TODO Auto-generated constructor stub
	}
	
	public KlinikaDTOzaStudent1PRETRAGA(Klinika klinika) {
		id = klinika.getId();
		naziv = klinika.getNaziv();	
		ocena = df2.format(klinika.getUkupnaOcena() / klinika.getBrojOcena());
		adresa = klinika.getAdresa();
		cena = 1000; // TREBA PROMENITI KADA SE ODRADI CENOVNIK
		
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

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}	
		
}
