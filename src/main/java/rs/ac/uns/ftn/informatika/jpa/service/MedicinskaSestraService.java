package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.repository.MedicinskaSestraRepository;

@Service
public class MedicinskaSestraService {

	@Autowired
	private MedicinskaSestraRepository medicinskaSestraRepository;
	
	public MedicinskaSestra findOne(Long id) {
		return medicinskaSestraRepository.findById(id).orElseGet(null);
	}

	public List<MedicinskaSestra> findAll() {
		return medicinskaSestraRepository.findAll();
	}
	
	public MedicinskaSestra save(MedicinskaSestra ms) {
		return medicinskaSestraRepository.save(ms);
	}

	public void remove(Long id) {
		medicinskaSestraRepository.deleteById(id);
	}
}
