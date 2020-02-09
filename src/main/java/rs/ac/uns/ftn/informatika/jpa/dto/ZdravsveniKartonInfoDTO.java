package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;

public class ZdravsveniKartonInfoDTO {
	private Long id;	
	private String krvnaGrupa;
	private String dioptrija;
	private Double visina;
	private Double tezina;
	private String alergije;
	private String listaBolesti;
	
	public ZdravsveniKartonInfoDTO()
	{
		
	}

	public ZdravsveniKartonInfoDTO(Long id, String krvnaGrupa, String dioptrija, Double visina, Double tezina,
			String alergije, String listaBolesti) {
		super();
		this.id = id;
		this.krvnaGrupa = krvnaGrupa;
		this.dioptrija = dioptrija;
		this.visina = visina;
		this.tezina = tezina;
		this.alergije = alergije;
		this.listaBolesti = listaBolesti;
	}
	
	public ZdravsveniKartonInfoDTO(ZdravstveniKarton zk)
	{
		this(zk.getId(),zk.getKrvnaGrupa(),zk.getDioptrija(),zk.getVisina(),zk.getTezina(),zk.getAlergije(),zk.getListaBolesti());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public String getDioptrija() {
		return dioptrija;
	}

	public void setDioptrija(String dioptrija) {
		this.dioptrija = dioptrija;
	}

	public Double getVisina() {
		return visina;
	}

	public void setVisina(Double visina) {
		this.visina = visina;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public String getAlergije() {
		return alergije;
	}

	public void setAlergije(String alergije) {
		this.alergije = alergije;
	}

	public String getListaBolesti() {
		return listaBolesti;
	}

	public void setListaBolesti(String listaBolesti) {
		this.listaBolesti = listaBolesti;
	}

	@Override
	public String toString() {
		return "ZdravsveniKartonInfoDTO [id=" + id + ", krvnaGrupa=" + krvnaGrupa + ", dioptrija=" + dioptrija
				+ ", visina=" + visina + ", tezina=" + tezina + ", alergije=" + alergije + ", listaBolesti="
				+ listaBolesti + "]";
	}
	
	
}
