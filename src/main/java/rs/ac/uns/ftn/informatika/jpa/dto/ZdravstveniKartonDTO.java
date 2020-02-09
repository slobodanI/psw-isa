package rs.ac.uns.ftn.informatika.jpa.dto;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;

public class ZdravstveniKartonDTO {
	
	private Long id;	
	private String krvnaGrupa;
	private String dioptrija;
	private Double visina;
	private Double tezina;
	private String alergije;
	private Collection<PregledDTO> listaPregleda = new ArrayList<PregledDTO>();
	private Collection<OperacijaDTO> listaOperacija = new ArrayList<OperacijaDTO>();
	private String listaBolesti;
	
	public ZdravstveniKartonDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ZdravstveniKartonDTO(ZdravstveniKarton zk) {
		id = zk.getId();
		krvnaGrupa = zk.getKrvnaGrupa();
		dioptrija = zk.getDioptrija();
		visina = zk.getVisina();
		tezina = zk.getTezina();
		alergije = zk.getAlergije();
		
		PregledDTO pregledDTO;
		for(Pregled p : zk.getListaPregleda()) {
			//System.out.println("Pregled: " + p.getCena() + p.getDatumPregleda() + p.getInformacije() + p.getSatnica() + p.getTipPregleda() + p.getId() + p.getDijagnoza());
			if(p.isObavljen()) {
				pregledDTO = new PregledDTO(p);
				//System.out.println("Pregled DTO: " + pregledDTO.getCena() + pregledDTO.getDatumPregleda() + pregledDTO.getInformacije() + pregledDTO.getId());
				listaPregleda.add(pregledDTO);
			}
			
		}

		OperacijaDTO operacijaDTO;
		for(Operacija o : zk.getListaOperacija()) {
			if(o.isObavljen()) {
				operacijaDTO = new OperacijaDTO(o);
				listaOperacija.add(operacijaDTO);
			}
			
		}
		
		listaBolesti = zk.getListaBolesti();
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

	public Collection<PregledDTO> getListaPregleda() {
		return listaPregleda;
	}

	public void setListaPregleda(Collection<PregledDTO> listaPregleda) {
		this.listaPregleda = listaPregleda;
	}

	public Collection<OperacijaDTO> getListaOperacija() {
		return listaOperacija;
	}

	public void setListaOperacija(Collection<OperacijaDTO> listaOperacija) {
		this.listaOperacija = listaOperacija;
	}

	public String getListaBolesti() {
		return listaBolesti;
	}

	public void setListaBolesti(String listaBolesti) {
		this.listaBolesti = listaBolesti;
	}
	
	
}
