package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;
import rs.ac.uns.ftn.informatika.jpa.repository.TipPregledaRepository;

@Service
public class TipPregledaService {
	
	@Autowired
	private TipPregledaRepository tipPregledaRepository;
	
	public TipPregleda findOne(Long id) {
		return tipPregledaRepository.findById(id).orElseGet(null);
	}
	
	public List<TipPregleda> findAll(){
		return tipPregledaRepository.findAll();
	}
	public TipPregleda save(TipPregleda tip) {
		return tipPregledaRepository.save(tip);
	}
	public void remove (Long id) {
		tipPregledaRepository.deleteById(id);
	}
	
	
	
}
