package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OcenaLekaraDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.repository.LekarRepository;

@Service
public class LekarService {

	@Autowired
	private LekarRepository lekarRepository;
	
	public Lekar findOne(Long id) {
		return lekarRepository.findById(id).orElseGet(null);
	}
	
	public List<Lekar> findAll(){
		return lekarRepository.findAll();
	}
  
	public Lekar save(Lekar lekar) {
		return lekarRepository.save(lekar);
	}
  
	public void remove(Long id) {
		lekarRepository.deleteById(id);
	}
	
	//ocenjivanje lekara
	public Boolean oceniLekara(OcenaLekaraDTO ocenaLekaraDTO) {
		
//		System.out.println("ID LEKARA: " + ocenaLekaraDTO.getIdLekara());
//		System.out.println("OCENA: " + ocenaLekaraDTO.getOcena());
		
		Lekar lekar = findOne(ocenaLekaraDTO.getIdLekara());
		if(lekar == null) {
			return false;
		}
		
		lekar.setUkupnaOcena(lekar.getUkupnaOcena() + ocenaLekaraDTO.getOcena());
		lekar.setBrojOcena(lekar.getBrojOcena() + 1);
		save(lekar);
		
		return true;
	}
	
}
