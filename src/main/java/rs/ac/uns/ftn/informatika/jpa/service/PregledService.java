package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.repository.PregledRepository;

@Service
public class PregledService {
		
	@Autowired
	private PregledRepository pregledRepository;
	
	public Pregled findOne(Long id) {
		return pregledRepository.findById(id).orElseGet(null);
	}

	public List<Pregled> findAll() {
		return pregledRepository.findAll();
	}
	
	public Page<Pregled> findAll(Pageable page) {
		return pregledRepository.findAll(page);
	}

	public Pregled save(Pregled pregled) {
		return pregledRepository.save(pregled);
	}

	public void remove(Long id) {
		pregledRepository.deleteById(id);
	}
	
}
