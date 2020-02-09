package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ZauzetostLekaraDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.repository.ZauzetostLekaraRepository;

@Service
public class ZauzetostLekaraService {
	
	@Autowired
	private ZauzetostLekaraRepository zauzetostLekaraRepository;
	
	@Autowired LekarService lekarService;
	
	public ZauzetostLekara findOne(Long id) {
		return zauzetostLekaraRepository.findById(id).orElseGet(null);
	}
	
	public List<ZauzetostLekara> findAll(){
		return zauzetostLekaraRepository.findAll();
	}
	
	public ZauzetostLekara save(ZauzetostLekara z) {
		return zauzetostLekaraRepository.save(z);
	}
	public void remove(Long id) {
		zauzetostLekaraRepository.deleteById(id);
	}
	
	public String dodajZauzetost(ZauzetostLekaraDTO zau) {
		
		Lekar lekar=lekarService.findOne(zau.getLekarId());
		
		LocalDateTime datePocetak;
		LocalDateTime dateKraj;
		
		try {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			        .appendPattern("dd-MM-yyyy[ HH:mm:ss]")
			        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			        .toFormatter();
			
			datePocetak = LocalDateTime.parse(zau.getPocetak(),formatter);
			dateKraj = LocalDateTime.parse(zau.getKraj(),formatter);
			
			if(datePocetak.compareTo(dateKraj)>0) {
				return "Datum pocetka ne moze biti pre datuma kraja";
			}
			
			Set<ZauzetostLekara> zauzetost = lekar.getListaZauzetostiLekara();
			
			for(ZauzetostLekara z:zauzetost) {
				LocalDateTime p2=z.getPocetak();
				LocalDateTime k2=z.getKraj();
				Boolean overlap = datePocetak.isBefore(k2) && p2.isBefore(dateKraj);
				
				if(overlap) {
					return "Ne sme biti preklapanja izmedju zauzetosti.Proverite kalendar";
				}
				
				
 			}
			
			
		}
		catch(Exception e) {
			return "Datum nije uspesno konvertovan";
		}
		
		ZauzetostLekara zaz=new ZauzetostLekara();
		zaz.setPocetak(datePocetak);
		zaz.setKraj(dateKraj);
		zaz.setLekar(lekar);
		this.save(zaz);
		
		Lekar lek = zaz.getLekar();
		Set<ZauzetostLekara> zauzetost = lek.getListaZauzetostiLekara();
		zauzetost.add(zaz);
		lekarService.save(lek);
		
		return "Uspesno dodato u listu zauzetosti";
		
		
	}
	
	

}
