package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
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
	
	public Pacijent updatePacijent(Long id, PacijentDTO pacijentDTO) {
		
		Pacijent pacijent = findOne(id);		
		if (pacijent == null) {
			return null;
		}
		
		if(pacijentDTO.getPassword().length() < 6) {
			return null;
		}
		
		pacijent.setIme(pacijentDTO.getIme());
		pacijent.setPrezime(pacijentDTO.getPrezime());
		pacijent.setUsername(pacijentDTO.getUsername());
		pacijent.setPassword(pacijentDTO.getPassword());
		pacijent.setAdresa(pacijentDTO.getAdresa());
		pacijent.setGrad(pacijentDTO.getGrad());
		pacijent.setDrzava(pacijentDTO.getDrzava());
		pacijent.setBrojTel(pacijentDTO.getBrojTel());

		pacijent = save(pacijent);
		
		return pacijent;
	}
}
