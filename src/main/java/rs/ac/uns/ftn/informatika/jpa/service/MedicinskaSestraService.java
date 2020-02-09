package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Odsustvo;
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

	//vraca odsustva medicinske sestre
	public List<OdsustvoDTO> getOdsustva(Long id) {
		
		MedicinskaSestra ms = this.findOne(id);
		
		List<OdsustvoDTO> odsustva = new ArrayList<OdsustvoDTO>();
		
		for (Odsustvo o : ms.getListaOdsutstava()) {
			odsustva.add(new OdsustvoDTO(o));
		}
		
		return odsustva;
	}
	
	
	public Boolean promenjenaLozinka(Long id) {
		
		MedicinskaSestra sestra = this.findOne(id);
		
		return sestra.getPromenjenaLozinka();
		
	}
	
	
	
}
