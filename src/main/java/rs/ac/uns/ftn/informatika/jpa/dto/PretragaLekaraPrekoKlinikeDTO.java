package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

public class PretragaLekaraPrekoKlinikeDTO {
	
	private LocalDateTime datum;
	private Long tipPregleda;
	private String imeLekara;
	private String prezimeLekara;
	private Double ocenaVecaOd;
	private Long klinikaID;
	
	public PretragaLekaraPrekoKlinikeDTO() {
		// TODO Auto-generated constructor stub
	}

	public PretragaLekaraPrekoKlinikeDTO(LocalDateTime datum, Long tipPregleda, String imeLekara, String prezimeLekara,
			Double ocenaVecaOd, Long klinikaID) {
		super();
		this.datum = datum;
		this.tipPregleda = tipPregleda;
		this.imeLekara = imeLekara;
		this.prezimeLekara = prezimeLekara;
		this.ocenaVecaOd = ocenaVecaOd;
		this.klinikaID = klinikaID;
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

	public String getImeLekara() {
		return imeLekara;
	}

	public void setImeLekara(String imeLekara) {
		this.imeLekara = imeLekara;
	}

	public String getPrezimeLekara() {
		return prezimeLekara;
	}

	public void setPrezimeLekara(String prezimeLekara) {
		this.prezimeLekara = prezimeLekara;
	}

	public Double getOcenaVecaOd() {
		return ocenaVecaOd;
	}

	public void setOcenaVecaOd(Double ocenaVecaOd) {
		this.ocenaVecaOd = ocenaVecaOd;
	}

	public Long getKlinikaID() {
		return klinikaID;
	}

	public void setKlinikaID(Long klinikaID) {
		this.klinikaID = klinikaID;
	}
	
	
}
