package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.repository.AdministratorKlinickogCentraRepository;

@Service
public class AministratorKlinickogCentraService {

	@Autowired
	private AdministratorKlinickogCentraRepository administratorKlinickogCentraRepository;
	
	public AdministratorKlinickogCentra findOne(Long id) {
		return administratorKlinickogCentraRepository.findById(id).orElseGet(null);
	}

	public List<AdministratorKlinickogCentra> findAll() {
		return administratorKlinickogCentraRepository.findAll();
	}
	
	public AdministratorKlinickogCentra save(AdministratorKlinickogCentra adminKC) {
		return administratorKlinickogCentraRepository.save(adminKC);
	}

	public void remove(Long id) {
		administratorKlinickogCentraRepository.deleteById(id);
	}
}
