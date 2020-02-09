package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;

public class PredefPregledDTO {
	
	private Long id;
	private String informacije;
	private Dijagnoza dijagnoza;
	private String pocetak;
	private String kraj;
	private String pocetakVreme;
	private String krajVreme;
	private LocalDateTime datumPregledaOd;
	private LocalDateTime datumPregledaDo;
	private Long lekar;
	private int popust;
	private Long tipPregleda;
	private int cena;
	private Long sala;
	
	public PredefPregledDTO() {
		
	}
	
	
	public PredefPregledDTO(Pregled pregled) {
		id=pregled.getId();
		informacije="";
		dijagnoza=null;
		datumPregledaOd=pregled.getDatumPregledaOd();
		datumPregledaDo=pregled.getDatumPregledaDo();
		lekar=pregled.getLekar().getId();
		popust=pregled.getPopust();
		cena=pregled.getCena();
		sala=pregled.getSala().getId();
	}
	public PredefPregledDTO(LocalDateTime datumPregledaOd,LocalDateTime datumPregledaDo
			,Long tipPregleda,Long lekar,Long sala,int cena,int popust) {
		this.datumPregledaOd=datumPregledaOd;
		this.datumPregledaDo =datumPregledaDo;
		this.tipPregleda=tipPregleda;
		this.lekar=lekar;
		this.sala=sala;
		this.cena=cena;
		this.informacije="";
		this.popust=popust;
		this.dijagnoza=null;
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacije() {
		return informacije;
	}

	public void setInformacije(String informacije) {
		this.informacije = informacije;
	}

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public LocalDateTime getDatumPregledaOd() {
		return datumPregledaOd;
	}

	public void setDatumPregledaOd(LocalDateTime datumPregledaOd) {
		this.datumPregledaOd = datumPregledaOd;
	}

	public LocalDateTime getDatumPregledaDo() {
		return datumPregledaDo;
	}

	public void setDatumPregledaDo(LocalDateTime datumPregledaDo) {
		this.datumPregledaDo = datumPregledaDo;
	}



	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}
	

	public Long getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(Long tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}


	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getPocetakVreme() {
		return pocetakVreme;
	}

	public void setPocetakVreme(String pocetakVreme) {
		this.pocetakVreme = pocetakVreme;
	}

	public String getKrajVreme() {
		return krajVreme;
	}

	public void setKrajVreme(String krajVreme) {
		this.krajVreme = krajVreme;
	}

	public Long getLekar() {
		return lekar;
	}

	public void setLekar(Long lekar) {
		this.lekar = lekar;
	}

	public Long getSala() {
		return sala;
	}

	public void setSala(Long sala) {
		this.sala = sala;
	}
	

	

}
