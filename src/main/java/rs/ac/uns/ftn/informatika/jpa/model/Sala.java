package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false, unique = true)
	private String naziv;
	
	@OneToMany(mappedBy = "sala",fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	private Set<ZauzetostSala> listaZauzetostiSala = new HashSet<ZauzetostSala>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Klinika klinika;
	
	public Sala() {
		// TODO Auto-generated constructor stub
	}

	public Sala(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
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


	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Set<ZauzetostSala> getListaZauzetostiSala() {
		return listaZauzetostiSala;
	}

	public void setListaZauzetostiSala(Set<ZauzetostSala> listaZauzetostiSala) {
		this.listaZauzetostiSala = listaZauzetostiSala;
	}
	
	
	
}
