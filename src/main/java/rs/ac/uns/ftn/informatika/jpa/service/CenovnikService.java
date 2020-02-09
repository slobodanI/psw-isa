package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.CenovnikDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Cenovnik;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.repository.CenovnikRepository;

@Service
public class CenovnikService {
	
	@Autowired CenovnikRepository cenovnikRepository;
	
	@Autowired KlinikaService klinikaService;
	
	@Autowired TipPregledaService tipPregledaService;
	
	@Autowired AdministratorKlinikeService adminKService;
	
	public Cenovnik findOne(Long id) {
		return cenovnikRepository.findById(id).orElseGet(null);
	}
	
	public List<Cenovnik> findAll(){
		return cenovnikRepository.findAll();
	}
	
	public void remove(Long id) {
		cenovnikRepository.deleteById(id);
	}
	public Cenovnik save(Cenovnik c) {
		return cenovnikRepository.save(c);
	}
	
	public String dodajCenu(CenovnikDTO c) {
		AdministratorKlinike admin = adminKService.findOne(c.getAdmin());
		Klinika kl = admin.getKlinika();
		TipPregleda tp = tipPregledaService.findOne(c.getTipPregleda());
		
		Cenovnik cen = new Cenovnik();
		cen.setCena(c.getCena());
		cen.setKlinika(kl);
		cen.setTipPregleda(tp);
		
		Klinika klinika = cen.getKlinika();
		Set<Cenovnik> cenovn = klinika.getCenovnik();
		cenovn.add(cen);
		klinikaService.save(klinika);
		
		return "Uspesno dodato u cenovnik";
		
	}

}
