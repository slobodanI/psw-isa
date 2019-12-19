package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Odsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.TipOdsustva;
import rs.ac.uns.ftn.informatika.jpa.repository.OdsustvoRepository;

@Service
public class OdsustvoService 
{
	@Autowired
	private OdsustvoRepository odsustvoRepository;
	
	@Autowired
	private MedicinskaSestraService medSestraService;
	
	public Odsustvo findOne(Long id) {
		return odsustvoRepository.findById(id).orElseGet(null);
	}

	public List<Odsustvo> findAll() {
		return odsustvoRepository.findAll();
	}
	
	public Odsustvo save(Odsustvo o) {
		return odsustvoRepository.save(o);
	}

	public void remove(Long id) {
		odsustvoRepository.deleteById(id);
	}

	//metoda za kreiranje odsustva za medicinsku sestru
	public String kreirajOdsustvo(OdsustvoDTO ods) {
		
		//pronadji med sestru koja je poslala zahtev
		MedicinskaSestra ms = medSestraService.findOne(ods.getMedSestraId());
		
		LocalDateTime datePocetak; 
		LocalDateTime dateKraj;
		try 
		{
			
			//System.out.println("##########" + ods.getPocetak() + "##############");
			
			//formatiranje vremena
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			        .appendPattern("dd-MM-yyyy[ HH:mm:ss]")
			        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			        .toFormatter();
			
			//parsiranje pocetka i kraja odsustva
			datePocetak = LocalDateTime.parse(ods.getPocetak(),formatter);
			dateKraj = LocalDateTime.parse(ods.getKraj(),formatter);
			
			//ako je pocetak posle kraja
			if(datePocetak.compareTo(dateKraj)>0)
			{
				return "Datum pocetka ne moze biti pre datuma kraja";
			}
			
			//uzimamo sva odsustva medicinske sestre
			Set<Odsustvo> odsustva = ms.getListaOdsutstava();
			
			//idemo kroz sva odsustva
			for (Odsustvo odsustvo : odsustva) {
				LocalDateTime p2 = odsustvo.getPocetak();
				LocalDateTime k2 = odsustvo.getKraj();
				//gledamo da li postoji preklapanje
				Boolean overlap = datePocetak.isBefore(k2) && p2.isBefore(dateKraj);
				
				//ako postoji vrati poruku
				if(overlap)
				{
					return "Ne sme biti preklapanja izmedju odsustava. Proverite vaš kalendar";
				}
			}
			
		} 
		catch (Exception e) 
		{
			return "Datum nije uspesno konvertovan";
		}  
		
		//popuni odsustvo
		TipOdsustva tods = TipOdsustva.valueOf(ods.getTip());
		Odsustvo odsustvo = new Odsustvo();
		odsustvo.setPocetak(datePocetak);
		odsustvo.setKraj(dateKraj);
		odsustvo.setTip(tods);
		odsustvo.setOdobreno(true); //zasad uvek true dok se ne odradi prihvatanje
		odsustvo.setMedSestra(ms);
		
		this.save(odsustvo);
		
		//dodaj odsustvo med sestri
		MedicinskaSestra med = odsustvo.getMedSestra();
		Set<Odsustvo> odsustva = med.getListaOdsutstava();
		odsustva.add(odsustvo);
		medSestraService.save(med);
		
		return "Uspesno poslat zahtev za odsustvo";
	}
}
