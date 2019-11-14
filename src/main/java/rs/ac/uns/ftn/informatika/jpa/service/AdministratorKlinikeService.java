package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.repository.AdministratorKlinikeRepository;

@Service
public class AdministratorKlinikeService {
	
	@Autowired
	private AdministratorKlinikeRepository administratorKlinikeRepository;
	
	public AdministratorKlinike findOne(Long id) {
		return administratorKlinikeRepository.findById(id).orElseGet(null);
	}
	
	public List<AdministratorKlinike> findAll() {
		return administratorKlinikeRepository.findAll();
	}
	
	public AdministratorKlinike save(AdministratorKlinike adminK) {
		return administratorKlinikeRepository.save(adminK);
	}

	public void remove(Long id) {
		administratorKlinikeRepository.deleteById(id);
	}
}
