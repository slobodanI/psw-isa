package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Klinika {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column(nullable = false)
	private int brojOcena;
	
	@Column(nullable = false)
	private Double ukupnaOcena;
	
	
	@Column(nullable = false)
	private Double prihod;

	
	@Column(nullable = false)
	private String adresa;
	
	@Column(nullable = false)
	private String opis;
	
	@Column(nullable = false)
	private String slobodniTerminiPregleda;
	
	@Column(nullable = false)
	private float latitude;
	
	@Column(nullable = false)
	private float longitude;
	
	@ManyToMany(mappedBy = "klinike")
	private Set<Pacijent> pacijenti =  new HashSet<Pacijent>();
	
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorKlinike> administratoriKlinike = new HashSet<AdministratorKlinike>();
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Lekar> lekari = new HashSet<Lekar>();
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> sale = new HashSet<Sala>();
	
//	private HashMap<TipPregleda,Double> cenovnik;

	
	public Klinika() {
		// TODO Auto-generated constructor stub
	}

	public Klinika(Long id, String naziv, String adresa, String opis, String slobodniTerminiPregleda, float latitude, float longitude,
			Set<Lekar> lekari/*, HashMap<TipPregleda, Double> cenovnik,
			IzvestajOPoslovanju izvestaj*/
			, Set<Sala> sale) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodniTerminiPregleda = slobodniTerminiPregleda;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lekari = lekari;
		this.sale = sale;
//		this.cenovnik = cenovnik;
//		this.izvestaj = izvestaj;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlobodniTerminiPregleda() {
		return slobodniTerminiPregleda;
	}

	public void setSlobodniTerminiPregleda(String slobodniTerminiPregleda) {
		this.slobodniTerminiPregleda = slobodniTerminiPregleda;
	}

	public Set<AdministratorKlinike> getAdministratorKlinike() {
		return administratoriKlinike;
	}

	public void setAdministratorKlinike(Set<AdministratorKlinike> administratorKlinike) {
		this.administratoriKlinike = administratorKlinike;
	}

	public int getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(int brojOcena) {
		this.brojOcena = brojOcena;
	}

	public Double getUkupnaOcena() {
		return ukupnaOcena;
	}

	public void setUkupnaOcena(Double ukupnaOcena) {
		this.ukupnaOcena = ukupnaOcena;
	}

	public Double getPrihod() {
		return prihod;
	}

	public void setPrihod(Double prihod) {
		this.prihod = prihod;
	}

	public Set<AdministratorKlinike> getAdministratoriKlinike() {
		return administratoriKlinike;
	}

	public void setAdministratoriKlinike(Set<AdministratorKlinike> administratoriKlinike) {
		this.administratoriKlinike = administratoriKlinike;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(Set<Lekar> lekari) {
		this.lekari = lekari;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Klinika [id=" + id + ", naziv=" + naziv + ", brojOcena=" + brojOcena + ", ukupnaOcena=" + ukupnaOcena
				+ ", prihod=" + prihod + ", adresa=" + adresa + ", opis=" + opis + ", slobodniTerminiPregleda="
				+ slobodniTerminiPregleda + ", latitude=" + latitude + ", longitude=" + longitude + ", pacijenti="
				+ pacijenti + ", administratoriKlinike=" + administratoriKlinike + ", lekari=" + lekari + ", sale="
				+ sale + "]";
	}

	

//	public Collection<Lekar> getLekari() {
//		return lekari;
//	}
//
//	public void setLekari(Collection<Lekar> lekari) {
//		this.lekari = lekari;
//	}
//
//	public Collection<Sala> getSale() {
//		return sale;
//	}
//
//	public void setSale(Collection<Sala> sale) {
//		this.sale = sale;
//	}
//
//	public HashMap<TipPregleda, Double> getCenovnik() {
//		return cenovnik;
//	}
//
//	public void setCenovnik(HashMap<TipPregleda, Double> cenovnik) {
//		this.cenovnik = cenovnik;
//	}

//	public IzvestajOPoslovanju getIzvestaj() {
//		return izvestaj;
//	}
//
//	public void setIzvestaj(IzvestajOPoslovanju izvestaj) {
//		this.izvestaj = izvestaj;
//	}
	
	
	
	

}
