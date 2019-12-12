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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Operacija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String informacije;
	
	@Column(nullable =  false)
	private String datumVreme;
	
	@Column(nullable =  false)
	private int cena;
	
	@ManyToMany
	@JoinTable(name = "lekarOperacija", joinColumns = @JoinColumn(name = "operacija_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "lekar_id", referencedColumnName = "id"))
	private Set<Lekar> lekari = new HashSet<Lekar>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ZdravstveniKarton zdravstveniKarton;
	
	@Column(nullable = false)
	private boolean obavljen;
	
	public Operacija() {
		// TODO Auto-generated constructor stub
	}

	public Operacija(Long id, String informacije, String datumIVreme, int cena, Set<Lekar> lekari, Pacijent pacijent,
			ZdravstveniKarton zdravstveniKarton) {
		super();
		this.id = id;
		this.informacije = informacije;
		this.datumVreme = datumIVreme;
		this.cena = cena;
		this.lekari = lekari;
		this.pacijent = pacijent;
		this.zdravstveniKarton = zdravstveniKarton;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacije() {
		return informacije;
	}

	public void setInformacije(String informacije) {
		this.informacije = informacije;
	}

	public String getDatumIVreme() {
		return datumVreme;
	}

	public void setDatumIVreme(String datumIVreme) {
		this.datumVreme = datumIVreme;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(Set<Lekar> lekari) {
		this.lekari = lekari;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public ZdravstveniKarton getZdravstveniKarton() {
		return zdravstveniKarton;
	}

	public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
		this.zdravstveniKarton = zdravstveniKarton;
	}

	public boolean isObavljen() {
		return obavljen;
	}

	public void setObavljen(boolean obavljen) {
		this.obavljen = obavljen;
	}
	
	
	
}
