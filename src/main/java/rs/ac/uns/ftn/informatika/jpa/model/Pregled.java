package rs.ac.uns.ftn.informatika.jpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class Pregled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =  false)
	private String informacije;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dijagnoza dijagnoza;
	
	@OneToMany(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti = new HashSet<Recept>();
	
	@Column(nullable =  false)
	private LocalDateTime datumPregledaOd;
	
	@Column(nullable =  false)
	private LocalDateTime datumPregledaDo;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Sala sala;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Lekar lekar;
	
	@Column(nullable =  false)
	private int popust;
	
	@ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/) // ne treba cascade..
	private TipPregleda tipPregleda;
	
	@Column(nullable =  false)
	private int cena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ZdravstveniKarton zdravstveniKarton;
	
	@Column(nullable = false)
	private boolean obavljen;
	
	@Column(nullable = false)
	private boolean prihvacen;
	
	@Column(nullable = false)
	private boolean obrisan;
	
	public Pregled() {
		// TODO Auto-generated constructor stub
	}
	
	public Pregled(Long id, String informacije, Dijagnoza dijagnoza, Set<Recept> recepti, LocalDateTime datumPregledaOd, LocalDateTime datumPregledaDo,
			String satnica, Sala sala, Lekar lekar, int popust, TipPregleda tipPregleda, int cena, boolean obavljen, boolean prihvacen, boolean obrisan) {
		super();
		this.id = id;
		this.informacije = informacije;
		this.dijagnoza = dijagnoza;
		this.recepti = recepti;
		this.datumPregledaOd = datumPregledaOd;
		this.datumPregledaDo = datumPregledaDo;
//		this.sala = sala;
		this.lekar = lekar;
		this.popust = popust;
		this.tipPregleda = tipPregleda;
		this.cena = cena;
		this.obavljen = obavljen;
		this.prihvacen = prihvacen;
		this.obrisan = obrisan;
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

	public LocalDateTime getDatumPregledaOd() {
		return datumPregledaOd;
	}

	public void setDatumPregledaOd(LocalDateTime datumPregleda) {
		this.datumPregledaOd = datumPregleda;
	}
	
	public LocalDateTime getDatumPregledaDo() {
		return datumPregledaDo;
	}

	public void setDatumPregledaDo(LocalDateTime datumPregleda) {
		this.datumPregledaDo = datumPregleda;
	}	


	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	

	public Lekar getLekar() {
		return lekar;
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
		
	public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public List<Recept> receptiToList()
	{
		List<Recept> rez = new ArrayList<Recept>();
		
		for (Recept r : this.recepti) {
			rez.add(r);
		}
		
		return rez;
	}

	
}
