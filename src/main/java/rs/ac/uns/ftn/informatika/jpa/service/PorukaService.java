package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.repository.PorukaRepository;

@Service
public class PorukaService {
	
	@Autowired
	private PorukaRepository porukaRepository;
	
	public Poruka findOne(Long id) {
		return porukaRepository.findById(id).orElseGet(null);
	}

	public List<Poruka> findAll() {
		return porukaRepository.findAll();
	}
	
	public Poruka save(Poruka por) {
		return porukaRepository.save(por);
	}

	public void remove(Long id) {
		porukaRepository.deleteById(id);
	}
}
