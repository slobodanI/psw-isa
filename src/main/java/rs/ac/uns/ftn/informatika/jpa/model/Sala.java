package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false, unique = true)
	private String naziv;
	
	@Column(nullable =  false)
	private String zauzetost;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Klinika klinika;
	
	public Sala() {
		// TODO Auto-generated constructor stub
	}

	public Sala(Long id, String naziv, String zauzetost) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.zauzetost = zauzetost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZauzetost() {
		return zauzetost;
	}

	public void setZauzetost(String zauzetost) {
		this.zauzetost = zauzetost;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	
	
}
