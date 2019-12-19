package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;

//za prikazivanje predefinisanig pregleda u klinici
public class PregledDTOStudent1 {
	
	private Long id;
	private String datumPregleda; // za sad ima i datum i satnicu
	private String satnica;	
	private String sala;
	private String lekar; // ime i prezime lekara
	private int cena;
	private int popust;
	private String tipPregleda; // naziv tipa pregleda
	
	public PregledDTOStudent1() {
		// TODO Auto-generated constructor stub
	}
	
	public PregledDTOStudent1(Long id, String datumPregleda, String satnica, Sala sala, String lekar,
								int cena, int popust, TipPregleda tipPregleda) {
		this.id = id;
		this.datumPregleda = datumPregleda;
		this.satnica = satnica;
		this.sala = sala.getNaziv();
		this.lekar = lekar;
		this.cena = cena;
		this.popust = popust;
		this.tipPregleda = tipPregleda.getNaziv();
	}
	
	public PregledDTOStudent1(Pregled pregled) {
		this.id = pregled.getId();
		this.datumPregleda = pregled.getDatumPregleda();
		this.satnica = pregled.getSatnica();
		this.sala = pregled.getSala().getNaziv();
		this.lekar = pregled.getLekar().getIme() + " " + pregled.getLekar().getPrezime();
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

	public String getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(String datumPregleda) {
		this.datumPregleda = datumPregleda;
	}

	public String getSatnica() {
		return satnica;
	}

	public void setSatnica(String satnica) {
		this.satnica = satnica;
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
	
	
	
}
