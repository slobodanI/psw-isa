package rs.ac.uns.ftn.informatika.jpa.model;

import java.util.HashMap;

//BICE DTO
public class IzvestajOPoslovanju {
	

	private Long id;
	
	
	private Double ocenaKlinike;
	
	private HashMap<Lekar,Double> prosecnaOcenaLekara;
	private Double prihodKlinike;
	
	public IzvestajOPoslovanju() {
		// TODO Auto-generated constructor stub
	}

	public IzvestajOPoslovanju(Long id, Double ocenaKlinike, HashMap<Lekar, Double> prosecnaOcenaLekara,
			Double prihodKlinike) {
		super();
		this.id = id;
		this.ocenaKlinike = ocenaKlinike;
		this.prosecnaOcenaLekara = prosecnaOcenaLekara;
		this.prihodKlinike = prihodKlinike;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOcenaKlinike() {
		return ocenaKlinike;
	}

	public void setOcenaKlinike(Double ocenaKlinike) {
		this.ocenaKlinike = ocenaKlinike;
	}

	public HashMap<Lekar, Double> getProsecnaOcenaLekara() {
		return prosecnaOcenaLekara;
	}

	public void setProsecnaOcenaLekara(HashMap<Lekar, Double> prosecnaOcenaLekara) {
		this.prosecnaOcenaLekara = prosecnaOcenaLekara;
	}

	public Double getPrihodKlinike() {
		return prihodKlinike;
	}

	public void setPrihodKlinike(Double prihodKlinike) {
		this.prihodKlinike = prihodKlinike;
	}
	
	
}
