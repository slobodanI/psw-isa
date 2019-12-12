package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Collection;
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

import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;

@Entity
public class Pregled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String informacije;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Dijagnoza dijagnoza;
	
	@OneToMany(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti = new HashSet<Recept>();
	
	@Column(nullable =  false)
	private String datumPregleda;
	
	@Column(nullable =  false)
	private String satnica;
	
//	@Column(nullable =  false)
//	private Sala sala;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Lekar lekar;
	
	@Column(nullable =  false)
	private int popust;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private TipPregleda tipPregleda;
	
	@Column(nullable =  false)
	private int cena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ZdravstveniKarton zdravstveniKarton;
	
	@Column(nullable = false)
	private boolean obavljen;
	
	public Pregled() {
		// TODO Auto-generated constructor stub
	}
	
	public Pregled(Long id, String informacije, Dijagnoza dijagnoza, Set<Recept> recepti, String datumPregleda,
			String satnica, Sala sala, Lekar lekar, int popust, TipPregleda tipPregleda, int cena, boolean obavljen) {
		super();
		this.id = id;
		this.informacije = informacije;
		this.dijagnoza = dijagnoza;
		this.recepti = recepti;
		this.datumPregleda = datumPregleda;
		this.satnica = satnica;
//		this.sala = sala;
		this.lekar = lekar;
		this.popust = popust;
		this.tipPregleda = tipPregleda;
		this.cena = cena;
		this.obavljen = obavljen;
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

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	public String getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(String datumPregleda) {
		this.datumPregleda = datumPregleda;
	}

	public String getSatnica() {
		return satnica;
	}

	public void setSatnica(String satnica) {
		this.satnica = satnica;
	}

//	public Sala getSala() {
//		return sala;
//	}
//
//	public void setSala(Sala sala) {
//		this.sala = sala;
//	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isObavljen() {
		return obavljen;
	}

	public void setObavljen(boolean obavljen) {
		this.obavljen = obavljen;
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
	
	
	
	
	
}
