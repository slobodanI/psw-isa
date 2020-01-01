package rs.ac.uns.ftn.informatika.jpa.dto;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;

public class LekarDTOStudent1PretragaLekara {
	private Long id;
	private String ime;
	private String prezime;
	private String ocena;
	private List<LocalDateTime> moguciTermini = new ArrayList<LocalDateTime>();
	
	//da ocena ima samo dve vrednosti posle zareza
	private DecimalFormat df2 = new DecimalFormat("#.##");
	
	public LekarDTOStudent1PretragaLekara() {
		// TODO Auto-generated constructor stub
	}

	public LekarDTOStudent1PretragaLekara(Lekar lekar) {
		this.id = lekar.getId();
		this.ime = lekar.getIme();
		this.prezime = lekar.getPrezime();
		this.ocena = df2.format(lekar.getUkupnaOcena() / lekar.getBrojOcena());
		//moguce termine cu dodati u servisu, kada ih obradim
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public List<LocalDateTime> getMoguciTermini() {
		return moguciTermini;
	}

	public void setMoguciTermini(List<LocalDateTime> moguciTermini) {
		this.moguciTermini = moguciTermini;
	}
	
	
	
}
