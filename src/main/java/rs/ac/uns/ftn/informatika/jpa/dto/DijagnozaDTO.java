package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;

public class DijagnozaDTO 
{
	private Long id;
	private String sifra;
	private String naziv;
	
	public DijagnozaDTO()
	{
		
	}

	public DijagnozaDTO(Long id, String sifra, String naziv) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
	}
	
	public DijagnozaDTO(Dijagnoza dijag)
	{
		this(dijag.getId(), dijag.getSifra(), dijag.getNaziv());
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
		return "DijagnozaDTO [id=" + id + ", sifra=" + sifra + ", naziv=" + naziv + "]";
	}
	
	
}
