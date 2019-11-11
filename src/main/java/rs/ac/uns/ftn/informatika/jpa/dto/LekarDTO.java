package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class LekarDTO {
	
	private Long id;
	private String ime;
	private String prezime;
	private Double ocenaLekara;
	private KlinikaDTO klinika;
//	private PacijentDTO pacijent;
//	private PregledDTO pregled;
	
	public LekarDTO() {
		// TODO Auto-generated constructor stub
	}
	public LekarDTO(Lekar lekar) {
		id=lekar.getId();
		ime=lekar.getIme();
		prezime=lekar.getPrezime();
		ocenaLekara=lekar.getukupnaOcena();
		klinika= new KlinikaDTO(lekar.getKlinika());
//		pacijent=new PacijentDTO(lekar.getPacijent());
//		pregled=new PregledDTO(lekar.getPregled());
		
	}

	public LekarDTO(Long id, String ime, String prezime, Double ocenaLekara, KlinikaDTO klinika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.ocenaLekara = ocenaLekara;
		this.klinika = klinika;
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
	public Double getOcenaLekara() {
		return ocenaLekara;
	}
	public void setOcenaLekara(Double ocenaLekara) {
		this.ocenaLekara = ocenaLekara;
	}
	public KlinikaDTO getKlinika() {
		return klinika;
	}
	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}
	
	

}
