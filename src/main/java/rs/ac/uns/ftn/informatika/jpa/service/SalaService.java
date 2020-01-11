package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.SalaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private AdministratorKlinikeService adminKlinikeService;
	
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
	

}
