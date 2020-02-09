package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ZauzetostSalaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostSala;
import rs.ac.uns.ftn.informatika.jpa.repository.ZauzetostSalaRepository;

@Service
public class ZauzetostSalaService {
	
	@Autowired
	private ZauzetostSalaRepository zauzetostSalaRepository;
	
	@Autowired SalaService salaService;
	
	public ZauzetostSala findOne(Long id) {
		return zauzetostSalaRepository.findById(id).orElseGet(null);
	}
	
	public List<ZauzetostSala> findAll(){
		return zauzetostSalaRepository.findAll();
	}
	
	public ZauzetostSala save(ZauzetostSala s) {
		return zauzetostSalaRepository.save(s);
	}
	public void remove(Long id) {
		zauzetostSalaRepository.deleteById(id);
	}
	
	public String dodajZauzetost(ZauzetostSalaDTO zau) {
		
	
		Sala sala = salaService.findOne(zau.getSalaId());
		
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
			
			Set<ZauzetostSala> zauzetost = sala.getListaZauzetostiSala();
			
			for(ZauzetostSala z:zauzetost) {
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
		
		ZauzetostSala zaz=new ZauzetostSala();
		zaz.setPocetak(datePocetak);
		zaz.setKraj(dateKraj);
		zaz.setSala(sala);
		this.save(zaz);
		
		Sala s = zaz.getSala();
		Set<ZauzetostSala> zauzetost = s.getListaZauzetostiSala();
		zauzetost.add(zaz);
		salaService.save(s);
		
		
		
		
		return "Uspesno dodato u listu zauzetosti";
		
		
	}
	
	

}
