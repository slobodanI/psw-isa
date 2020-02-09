package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Model klase Poruka
//(poruka koja stize administratoru kad se kreira zahtev za kreiranje naloga pacijenta)
@Entity
public class Poruka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private Long pacijent_id;
	
	@Column(nullable =  false)
	private String naslov;
	
	@Column(nullable =  false)
	private String telo;
	
	@Column(nullable =  false)
	private String email_posiljaoca;
	
	@Column(nullable =  false)
	private String email_primaoca;
	
	@Column(nullable =  false)
	private Boolean odgovoreno;
	
	public Poruka()
	{
		
	}

	public Poruka(Long pacijent_id, String naslov, String telo, String email_posiljaoca, String email_primaoca, Boolean odgovoreno) {
		super();
		this.pacijent_id = pacijent_id;
		this.naslov = naslov;
		this.telo = telo;
		this.email_posiljaoca = email_posiljaoca;
		this.email_primaoca = email_primaoca;
		this.odgovoreno = odgovoreno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacijent_id() {
		return pacijent_id;
	}

	public void setPacijent_id(Long pacijent_id) {
		this.pacijent_id = pacijent_id;
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

	public String getEmail_posiljaoca() {
		return email_posiljaoca;
	}

	public void setEmail_posiljaoca(String email_posiljaoca) {
		this.email_posiljaoca = email_posiljaoca;
	}

	public String getEmail_primaoca() {
		return email_primaoca;
	}

	public void setEmail_primaoca(String email_primaoca) {
		this.email_primaoca = email_primaoca;
	}

	public Boolean getOdgovoreno() {
		return odgovoreno;
	}

	public void setOdgovoreno(Boolean odgovoreno) {
		this.odgovoreno = odgovoreno;
	}

	@Override
	public String toString() {
		return "Poruka [id=" + id + ", pacijent_id=" + pacijent_id + ", naslov=" + naslov + ", telo=" + telo
				+ ", email_posiljaoca=" + email_posiljaoca + ", email_primaoca=" + email_primaoca + ", odgovoreno="
				+ odgovoreno + "]";
	}

	
	
}
