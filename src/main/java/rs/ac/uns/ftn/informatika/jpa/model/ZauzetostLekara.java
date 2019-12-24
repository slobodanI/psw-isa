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
public class ZauzetostLekara {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime pocetak;
	
	@Column(nullable = false)
	private LocalDateTime kraj;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Lekar lekar;
	
	
	public ZauzetostLekara() {
		
	}
	
	public ZauzetostLekara(Long id,LocalDateTime pocetak,LocalDateTime kraj,Lekar lekar) {
		super();
		this.id=id;
		this.pocetak=pocetak;
		this.kraj=kraj;
		this.lekar=lekar;
		
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

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	

}
