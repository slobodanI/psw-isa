package rs.ac.uns.ftn.informatika.jpa.dto;

import java.time.LocalDateTime;

import rs.ac.uns.ftn.informatika.jpa.model.Operacija;

public class OperacijaDTOStudent2 {
	
	private Long id;
	private LocalDateTime datumOperacije;
	private Long pacijent;

	
	public OperacijaDTOStudent2() {
		
	}
	
	public OperacijaDTOStudent2(Operacija op) {
		this.id=op.getId();
		this.datumOperacije=op.getDatumOperacijeOd();
		this.pacijent=op.getPacijent().getId();
	}
	
	public OperacijaDTOStudent2(LocalDateTime datum,Long pacijent) {
		this.datumOperacije=datum;
		this.pacijent=pacijent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumOperacije() {
		return datumOperacije;
	}

	public void setDatumOperacije(LocalDateTime datumOperacije) {
		this.datumOperacije = datumOperacije;
	}

	public Long getPacijent() {
		return pacijent;
	}

	public void setPacijent(Long pacijent) {
		this.pacijent = pacijent;
	}



	
	
}
