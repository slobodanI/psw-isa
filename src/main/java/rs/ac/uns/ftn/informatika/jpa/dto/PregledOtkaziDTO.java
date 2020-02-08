package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;

//za prikazivanje predefinisanig pregleda u klinici
public class PregledOtkaziDTO {
	
	private Long id;
	private LocalDateTime datumPregledaOD; 
	private LocalDateTime datumPregledaDO;	
	private String sala;
	private String lekar; // ime i prezime lekara
	private String pacijent;
	private int cena;
	private int popust;
	private String tipPregleda; // naziv tipa pregleda
	
	public PregledOtkaziDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PregledOtkaziDTO(Long id, LocalDateTime datumPregledaOD, LocalDateTime datumPregledaDO, Sala sala, String lekar,
								int cena, int popust, TipPregleda tipPregleda,String pacijent) {
		this.id = id;
		this.datumPregledaOD = datumPregledaOD;
		this.datumPregledaDO = datumPregledaDO;
		this.sala = sala.getNaziv();
		this.lekar = lekar;
		this.cena = cena;
		this.popust = popust;
		this.pacijent=pacijent;
		this.tipPregleda = tipPregleda.getNaziv();
	}
	
	public PregledOtkaziDTO(Pregled pregled) {
		this.id = pregled.getId();
		this.datumPregledaOD = pregled.getDatumPregledaOd();
		this.datumPregledaDO = pregled.getDatumPregledaDo();
		if(pregled.getSala() == null) {
			this.sala = "nije dodeljena sala";
		} else {
			this.sala = pregled.getSala().getNaziv();
		}
		
		this.lekar = pregled.getLekar().getIme() + " " + pregled.getLekar().getPrezime();
		this.pacijent = pregled.getPacijent().getIme() + " "+ pregled.getPacijent().getPrezime(); 
		this.cena = pregled.getCena();
		this.popust = pregled.getPopust();
		this.tipPregleda = pregled.getTipPregleda().getNaziv();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public LocalDateTime getDatumPregledaOD() {
		return datumPregledaOD;
	}

	public void setDatumPregledaOD(LocalDateTime datumPregledaOD) {
		this.datumPregledaOD = datumPregledaOD;
	}

	public LocalDateTime getDatumPregledaDO() {
		return datumPregledaDO;
	}

	public void setDatumPregledaDO(LocalDateTime datumPregledaDO) {
		this.datumPregledaDO = datumPregledaDO;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public String getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}
	
	
	
}
