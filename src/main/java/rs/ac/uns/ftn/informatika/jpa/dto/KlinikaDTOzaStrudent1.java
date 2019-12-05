package rs.ac.uns.ftn.informatika.jpa.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;


public class KlinikaDTOzaStrudent1 {
	
	private Long id;
	private String naziv;	
//	private int brojOcena;
//	private Double ukupnaOcena;
	private String ocena;
	private String adresa;
	private String opis;
//	private float latitude;
//	private float longitude;
	private Collection<LekarDTOzaStudent1> lekari = new ArrayList<LekarDTOzaStudent1>();
	
	//da ocena ima samo dve vrednosti posle zareza
	private DecimalFormat df2 = new DecimalFormat("#.##");
	
	public KlinikaDTOzaStrudent1() {
		// TODO Auto-generated constructor stub
	}

	public KlinikaDTOzaStrudent1(Klinika klinika) {
		id = klinika.getId();
		naziv = klinika.getNaziv();	
		ocena = df2.format(klinika.getUkupnaOcena() / klinika.getBrojOcena());
		adresa = klinika.getAdresa();
		opis = klinika.getOpis();
//		private float latitude;
//		private float longitude;
		LekarDTOzaStudent1 lekarDTO;
		for(Lekar l : klinika.getLekari()) {
			lekarDTO = new LekarDTOzaStudent1(l);
			lekari.add(lekarDTO);
		}
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
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

	public Collection<LekarDTOzaStudent1> getLekari() {
		return lekari;
	}

	public void setLekari(Collection<LekarDTOzaStudent1> lekari) {
		this.lekari = lekari;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
