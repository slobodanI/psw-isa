package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

public class PretragaKlinikaDTO {
	
	private LocalDateTime datum;
	private Long tipPregleda;
	
	public PretragaKlinikaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PretragaKlinikaDTO(LocalDateTime datum, Long tipPregleda) {
		super();
		this.datum = datum;
		this.tipPregleda = tipPregleda;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Long getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(Long tipPregleda) {
		this.tipPregleda = tipPregleda;
	}
	
	
}
