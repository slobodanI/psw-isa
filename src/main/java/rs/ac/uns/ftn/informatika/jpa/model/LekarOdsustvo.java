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
public class LekarOdsustvo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime pocetak;
	
	@Column(nullable = false)
	private LocalDateTime kraj;
	
	@Column(nullable = false)
	private TipOdsustva tip;
	
	@Column()
	Boolean odobreno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Lekar lekar;
	
	public LekarOdsustvo() {
		
	}
	
	public LekarOdsustvo(Long id, LocalDateTime pocetak, LocalDateTime kraj, TipOdsustva tip, Boolean odobreno, Lekar lekar) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tip = tip;
		this.odobreno = odobreno;
		this.lekar = lekar;
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

	public Boolean getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(Boolean odobreno) {
		this.odobreno = odobreno;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	

}
