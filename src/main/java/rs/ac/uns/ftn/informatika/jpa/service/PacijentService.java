package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	public Pacijent findOne(Long id) {
		return pacijentRepository.findById(id).orElseGet(null);
	}

	public List<Pacijent> findAll() {
		return pacijentRepository.findAll();
	}
	
	public Pacijent save(Pacijent pac) {
		return pacijentRepository.save(pac);
	}

	public void remove(Long id) {
		pacijentRepository.deleteById(id);
	}
}
