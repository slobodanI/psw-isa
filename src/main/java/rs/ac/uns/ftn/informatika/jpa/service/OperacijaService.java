package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.OperacijaDTOStudent2;
import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.repository.OperacijaRepository;

@Service
public class OperacijaService {

	@Autowired
	private OperacijaRepository operacijaRepository;
	
	@Autowired
	private PacijentService pacijentService;
	
	public Operacija findOne(Long id) {
		return operacijaRepository.findById(id).orElseGet(null);
	}

	public List<Operacija> findAll() {
		return operacijaRepository.findAll();
	}
	
	public Page<Operacija> findAll(Pageable page) {
		return operacijaRepository.findAll(page);
	}

	public Operacija save(Operacija operacija) {
		return operacijaRepository.save(operacija);
	}

	public void remove(Long id) {
		operacijaRepository.deleteById(id);
	}
	
	public String dodajNovuOperaciju(OperacijaDTOStudent2 operacija) {
		
		
		Pacijent pacijent = pacijentService.findOne(operacija.getPacijent());
		Operacija novaOp = new Operacija();
		novaOp.setDatumOperacijeOd(operacija.getDatumOperacije());
		novaOp.setDatumOperacijeDo(operacija.getDatumOperacije());
		novaOp.setPacijent(pacijent);
		novaOp.setZdravstveniKarton(pacijent.getZdravstveniKarton());
		novaOp.setCena(5000);
		novaOp.setInformacije("");
		novaOp.setObavljen(false);
		
		
		this.save(novaOp);
		return null;
		
	}
	
}
