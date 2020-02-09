package rs.ac.uns.ftn.informatika.jpa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Odsustvo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime pocetak;
	
	@Column(nullable = false)
	private LocalDateTime kraj;
	
	@Column(nullable = false)
	private TipOdsustva tip;
	
	@Column(nullable = true)
	Boolean odobreno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private MedicinskaSestra medSestra;
	

	
	public Odsustvo()
	{
		
	}

	public Odsustvo(Long id, LocalDateTime pocetak, LocalDateTime kraj, TipOdsustva tip, Boolean odobreno, MedicinskaSestra medSestra) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tip = tip;
		this.odobreno = odobreno;
		this.medSestra = medSestra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	public TipOdsustva getTip() {
		return tip;
	}

	public void setTip(TipOdsustva tip) {
		this.tip = tip;
	}

	public MedicinskaSestra getMedSestra() {
		return medSestra;
	}

	public void setMedSestra(MedicinskaSestra medSestra) {
		this.medSestra = medSestra;
	}

	public Boolean getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(Boolean odobreno) {
		this.odobreno = odobreno;
	}

	@Override
	public String toString() {
		return "Odsustvo [id=" + id + ", pocetak=" + pocetak + ", kraj=" + kraj + ", tip=" + tip + ", odobreno="
				+ odobreno + ", medSestra=" + medSestra + "]";
	}

	
	
	
}
