package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	private SalaRepository salaRepository;
	
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

}
