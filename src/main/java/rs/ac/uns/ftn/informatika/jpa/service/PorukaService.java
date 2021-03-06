package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.repository.PorukaRepository;

//servis za klasu Poruka
//(poruka koja stize administratoru kad se kreira zahtev za kreiranje naloga pacijenta)
@Service
public class PorukaService {
	
	@Autowired
	private PorukaRepository porukaRepository;
	
	public Poruka findOne(Long id) {
		return porukaRepository.findById(id).orElseGet(null);
	}

	public List<Poruka> findAll() {
		return porukaRepository.findAll();
	}
	
	public Poruka save(Poruka por) {
		return porukaRepository.save(por);
	}

	public void remove(Long id) {
		porukaRepository.deleteById(id);
	}
	
	public List<PorukaDTO> vratiSvePorukeDTO(List<Poruka> poruke)
	{
		List<PorukaDTO> porukeDTO = new ArrayList<>();
		for (Poruka p : poruke) {
			porukeDTO.add(new PorukaDTO(p));
		}
		return porukeDTO;
	}
	
	public Poruka serviceSetOdgovoreno(Poruka poruka)
	{
		poruka.setOdgovoreno(true);
		return poruka;
	}
}
