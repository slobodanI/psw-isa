package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Recept;
import rs.ac.uns.ftn.informatika.jpa.repository.ReceptRepository;

@Service
public class ReceptService 
{
	@Autowired
	private ReceptRepository receptRepository;
	
	public Recept findOne(Long id) {
		return receptRepository.findById(id).orElseGet(null);
	}

	public List<Recept> findAll() {
		return receptRepository.findAll();
	}
	
	public Page<Recept> findAll(Pageable page) {
		return receptRepository.findAll(page);
	}

	public Recept save(Recept recept) {
		return receptRepository.save(recept);
	}

	public void remove(Long id) {
		receptRepository.deleteById(id);
	}
}
