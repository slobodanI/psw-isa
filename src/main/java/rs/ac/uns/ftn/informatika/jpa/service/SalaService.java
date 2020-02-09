package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.SalaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZauzetostSalaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostSala;
import rs.ac.uns.ftn.informatika.jpa.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private AdministratorKlinikeService adminKlinikeService;
	
	@Autowired
	private PregledService pregledService;
	
	public Sala findOne(Long id) {
		return salaRepository.findById(id).orElseGet(null);
	}
	
	public List<Sala> findAll(){
		return salaRepository.findAll();
	}
  
	public Sala save(Sala sala) {
		return salaRepository.save(sala);
	}
  
	public void remove(Long id) {
		salaRepository.deleteById(id);
	}
	
	//vraca sve sale klinike pomocu id-a prijavljenog admina klinike
	public List<SalaDTO> vratiSveSaleKlinike(Long id){
		AdministratorKlinike admin = adminKlinikeService.findOne(id);
		Klinika klinika = admin.getKlinika();
		List<Sala> lista = this.findAll();
		
		List<SalaDTO> rezultat = new ArrayList<>();
		
		for(Sala s : lista) {
			if(s.getKlinika().getId() == klinika.getId()) {
				rezultat.add(new SalaDTO(s));
			}
		}
		return rezultat;
	}
	
	public List<SalaDTO> vratiSlobodneSale(Long id){
		AdministratorKlinike admin = adminKlinikeService.findOne(id);
		Klinika klinika = admin.getKlinika();
		List<Sala> lista = this.findAll();
		
		List<SalaDTO> rezultat = new ArrayList<>();
		
		for(Sala s : lista) {
			if(s.getKlinika().getId() == klinika.getId()) {
				rezultat.add(new SalaDTO(s));
			}
		}
		return rezultat;
		
	}
	
public List<ZauzetostSalaDTO> getZauzetostSala(Long id){
	
	Sala sala = this.findOne(id);
	List<ZauzetostSalaDTO> zauzetost = new ArrayList<ZauzetostSalaDTO>();
	
	for(ZauzetostSala z : sala.getListaZauzetostiSala()) {
		zauzetost.add(new ZauzetostSalaDTO(z));
	}
	
	return zauzetost;
}
	
	
	
public List<SalaDTO> vratiSlobodneSaleZaPregled(Long id){
		Pregled pregled = pregledService.findOne(id);
		LocalDateTime vremePocetka = pregled.getDatumPregledaOd();
		LocalDateTime vremeKraja = pregled.getDatumPregledaDo();
		Lekar lekar = pregled.getLekar();
		Klinika klinika = lekar.getKlinika();
		List<Sala> lista = this.findAll();
		List<Sala> pomocnaLista = new ArrayList<>();
		List<SalaDTO> rezultat = new ArrayList<>();
		
		for(Sala s : lista) {
			if(s.getKlinika().getId()==klinika.getId()) {
				pomocnaLista.add(s);
			}
		}
		
		for(Sala pom : pomocnaLista) {
			boolean slobodna=true;
			for(ZauzetostSala z : pom.getListaZauzetostiSala()) {
				if(pom.getListaZauzetostiSala()==null) {
					continue;
				}
				if(!vremePocetka.isAfter(z.getKraj()) && !vremePocetka.isBefore(z.getPocetak())) {
					slobodna=false;
				}
				if(!vremeKraja.isAfter(z.getKraj()) && !vremeKraja.isBefore(z.getPocetak())) {
					slobodna=false;
				}
				if(!z.getKraj().isAfter(vremeKraja) && !z.getKraj().isBefore(vremePocetka)){
					slobodna = false;
				}
				if(!z.getPocetak().isAfter(vremeKraja) && !z.getPocetak().isBefore(vremePocetka)) {
					slobodna = false;
				}
				
			}
			if(slobodna == true) {
				rezultat.add(new SalaDTO(pom));
			}
			
			
			
			
		}
		
		
		
		
		
		return rezultat;		
	}



}






