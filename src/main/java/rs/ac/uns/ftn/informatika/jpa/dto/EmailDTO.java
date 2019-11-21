package rs.ac.uns.ftn.informatika.jpa.dto;

//DTO za slanje eMail-ova
public class EmailDTO 
{
	private int id_pacijenta;
	private String naslov;
	private String telo;
	private String mail;
	
	public EmailDTO()
	{
		
	}
	
	public EmailDTO(int id_pacijenta, String naslov, String telo, String mail) {
		super();
		this.id_pacijenta = id_pacijenta;
		this.naslov = naslov;
		this.telo = telo;
		this.mail = mail;
	}

	public int getId_pacijenta() {
		return id_pacijenta;
	}

	public void setId_pacijenta(int id_pacijenta) {
		this.id_pacijenta = id_pacijenta;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTelo() {
		return telo;
	}

	public void setTelo(String telo) {
		this.telo = telo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "EmailDTO [id_pacijenta=" + id_pacijenta + ", naslov=" + naslov + ", telo=" + telo + ", mail=" + mail
				+ "]";
	}
	
}
