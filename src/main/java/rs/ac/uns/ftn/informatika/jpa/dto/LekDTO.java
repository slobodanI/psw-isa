package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Lek;

public class LekDTO {
	
	private Long id;
	private String sifra;
	private String naziv;
	
	
	public LekDTO()
	{
		
	}


	public LekDTO(Long id, String sifra, String naziv) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
	}
	
	public LekDTO(Lek lek)
	{
		this(lek.getId(), lek.getSifra(), lek.getNaziv());
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSifra() {
		return sifra;
	}


	public void setSifra(String sifra) {
		this.sifra = sifra;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	@Override
	public String toString() {
		return "LekDTO [id=" + id + ", sifra=" + sifra + ", naziv=" + naziv + "]";
	}
	
	
}
