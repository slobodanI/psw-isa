package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.repository.KlinikaRepository;

@Service
public class KlinikaService {
	
	@Autowired
	private KlinikaRepository klinikaRepo;
	
	public List<Klinika> findAll(){
		return klinikaRepo.findAll();
	}
	
	public Klinika findOne(Long id) {
		return klinikaRepo.findById(id).orElseGet(null);
	}
	public Klinika save(Klinika klinika) {
		return klinikaRepo.save(klinika);
	}
	public void remove(Long id) {
		klinikaRepo.deleteById(id);
	}
}
