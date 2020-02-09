package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Cenovnik;

public class CenovnikDTO {
	
	private Long id;
	private Long admin;
	private Long tipPregleda;
	private Long cena;
	
	public CenovnikDTO() {
		
	}

	public CenovnikDTO(Long id,Long klinika,Long tipPregleda,Long cena) {
		super();
		this.id=id;
		this.admin=klinika;
		this.tipPregleda=tipPregleda;
		this.cena=cena;
	}
	
	public CenovnikDTO(Cenovnik c) {
		this(c.getId(),c.getKlinika().getId(),c.getTipPregleda().getId(),c.getCena());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Long getAdmin() {
		return admin;
	}

	public void setAdmin(Long admin) {
		this.admin = admin;
	}

	public Long getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(Long tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Long getCena() {
		return cena;
	}

	public void setCena(Long cena) {
		this.cena = cena;
	}
	
	
}
