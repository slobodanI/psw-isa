package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;

public class PregledDTOStudent2 {
	
	private Long id;
	private LocalDateTime datumPregledaOd;
	private LocalDateTime datumPregledaDo;
	private Long lekar;
	private Long pacijent;
//	private Long tipPregleda;
	
	public PregledDTOStudent2() {
		
	}
	
	public PregledDTOStudent2(Pregled pregled) {
		id=pregled.getId();
		datumPregledaOd=pregled.getDatumPregledaOd();
		datumPregledaDo=pregled.getDatumPregledaDo();
		lekar=pregled.getLekar().getId();
		pacijent=pregled.getPacijent().getId();
		
	}
	
	public PregledDTOStudent2(LocalDateTime datumOd,LocalDateTime datumDo,Long lekar,Long pacijent) {
		
		this.datumPregledaOd=datumOd;
		this.datumPregledaDo=datumDo;
		this.lekar=lekar;
		this.pacijent=pacijent;
			
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumPregledaOd() {
		return datumPregledaOd;
	}

	public void setDatumPregledaOd(LocalDateTime datumPregledOd) {
		this.datumPregledaOd = datumPregledOd;
	}

	public LocalDateTime getDatumPregledaDo() {
		return datumPregledaDo;
	}

	public void setDatumPregledaDo(LocalDateTime datumPregledaDo) {
		this.datumPregledaDo = datumPregledaDo;
	}

	public Long getLekar() {
		return lekar;
	}

	public void setLekar(Long lekar) {
		this.lekar = lekar;
	}

	public Long getPacijent() {
		return pacijent;
	}

	public void setPacijent(Long pacijent) {
		this.pacijent = pacijent;
	}
	
	
	
	
}
