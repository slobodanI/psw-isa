package rs.ac.uns.ftn.informatika.jpa.dto;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.Operacija;

public class OperacijaDTO {
	
	private Long id;
	private String informacije;
	private String datumVreme;
	private int cena;
	private Collection<LekarDTOzaStudent1> lekari = new ArrayList<LekarDTOzaStudent1>();// mozda samo ime lekara da stavim
	
	public OperacijaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public OperacijaDTO(Operacija operacija) {
		id = operacija.getId();
		informacije = operacija.getInformacije();
		//datumVreme = operacija.getDatumIVreme();
		cena = operacija.getCena();

		LekarDTOzaStudent1 lekarDTO;
		for(Lekar l : operacija.getLekari()) {
			lekarDTO = new LekarDTOzaStudent1(l);
			lekari.add(lekarDTO);
		}
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

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Collection<LekarDTOzaStudent1> getLekari() {
		return lekari;
	}

	public void setLekari(Collection<LekarDTOzaStudent1> lekari) {
		this.lekari = lekari;
	}
	
	
}
