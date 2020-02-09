package rs.ac.uns.ftn.informatika.jpa.model;

public class PretragaLekara {
	private String ime;
	private String prezime;
	private Double prosecnaOcenaOd;
	private Double prosecnaOcenaDo;
	private int radnoVremeOd;
	private int radnoVremeDo;
	
	public PretragaLekara() {
		
	}
	
	public PretragaLekara(String ime, String prezime, Double prosecnaOcenaOd, Double prosecnaOcenaDo, int radnoVremeOd,
			int radnoVremeDo) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.prosecnaOcenaOd = prosecnaOcenaOd;
		this.prosecnaOcenaDo = prosecnaOcenaDo;
		this.radnoVremeOd = radnoVremeOd;
		this.radnoVremeDo = radnoVremeDo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Double getProsecnaOcenaOd() {
		return prosecnaOcenaOd;
	}

	public void setProsecnaOcenaOd(Double prosecnaOcenaOd) {
		this.prosecnaOcenaOd = prosecnaOcenaOd;
	}

	public Double getProsecnaOcenaDo() {
		return prosecnaOcenaDo;
	}

	public void setProsecnaOcenaDo(Double prosecnaOcenaDo) {
		this.prosecnaOcenaDo = prosecnaOcenaDo;
	}

	public int getRadnoVremeOd() {
		return radnoVremeOd;
	}

	public void setRadnoVremeOd(int radnoVremeOd) {
		this.radnoVremeOd = radnoVremeOd;
	}

	public int getRadnoVremeDo() {
		return radnoVremeDo;
	}

	public void setRadnoVremeDo(int radnoVremeDo) {
		this.radnoVremeDo = radnoVremeDo;
	}
	
	

}
