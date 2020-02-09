package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Recept;

public class ReceptDTO 
{
	private Long id;
	private String sifraLeka;
	private String nazivLeka;
	private Long lbo;
	private String imePacijenta;
	private String prezimePacijenta;
	
	public ReceptDTO()
	{
		
	}
	
	public ReceptDTO(Recept rec)
	{
		this(rec.getId(), rec.getSifra_Leka(), rec.getNazivLeka(), rec.getLbo(), rec.getImePacijenta(), rec.getPrezimePacijenta());
	}

	public ReceptDTO(Long id, String sifraLeka, String nazivLeka, Long lbo, String imePacijenta,
			String prezimePacijenta) {
		super();
		this.id = id;
		this.sifraLeka = sifraLeka;
		this.nazivLeka = nazivLeka;
		this.lbo = lbo;
		this.imePacijenta = imePacijenta;
		this.prezimePacijenta = prezimePacijenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSifraLeka() {
		return sifraLeka;
	}

	public void setSifraLeka(String sifraLeka) {
		this.sifraLeka = sifraLeka;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}

	public Long getLbo() {
		return lbo;
	}

	public void setLbo(Long lbo) {
		this.lbo = lbo;
	}

	public String getImePacijenta() {
		return imePacijenta;
	}

	public void setImePacijenta(String imePacijenta) {
		this.imePacijenta = imePacijenta;
	}

	public String getPrezimePacijenta() {
		return prezimePacijenta;
	}

	public void setPrezimePacijenta(String prezimePacijenta) {
		this.prezimePacijenta = prezimePacijenta;
	}

	@Override
	public String toString() {
		return "ReceptDTO [id=" + id + ", sifraLeka=" + sifraLeka + ", nazivLeka=" + nazivLeka + ", lbo=" + lbo
				+ ", imePacijenta=" + imePacijenta + ", prezimePacijenta=" + prezimePacijenta + "]";
	}

	
	
	
}
