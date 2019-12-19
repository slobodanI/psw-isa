package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.OcenaKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.repository.KlinikaRepository;

@Service
public class KlinikaService {
	
	@Autowired
	private KlinikaRepository klinikaRepo;
	
	public List<Klinika> findAll(){
		return klinikaRepo.findAll();
	}
	
	public Klinika findOne(Long id) {
		return klinikaRepo.findById(id).orElseGet(null);
	}
	public Klinika save(Klinika klinika) {
		return klinikaRepo.save(klinika);
	}
	public void remove(Long id) {
		klinikaRepo.deleteById(id);
	}

	//metoda za dodavanje klinike
	public String kreirajKliniku(KlinikaDTO klinika) {
		
		try 
		{
			Float.parseFloat(Float.toString(klinika.getLatitude()));
			Float.parseFloat(Float.toString(klinika.getLongitude()));
		} 
		catch (Exception e) 
		{
			return "Latituda i longituda moraju biti brojevi";
		}
		
		Klinika novaKlinika = new Klinika();
		novaKlinika.setNaziv(klinika.getNaziv());
		novaKlinika.setAdresa(klinika.getAdresa());
		novaKlinika.setOpis(klinika.getOpis());
		novaKlinika.setSlobodniTerminiPregleda(klinika.getSlobodniTerminiPregleda());
		novaKlinika.setLatitude(klinika.getLatitude());
		novaKlinika.setLongitude(klinika.getLongitude());
		novaKlinika.setBrojOcena(0);
		novaKlinika.setUkupnaOcena(0d);
		novaKlinika.setPrihod(0d);
		Set<AdministratorKlinike> ak = new HashSet<AdministratorKlinike>();
		novaKlinika.setAdministratoriKlinike(ak);
		Set<Pacijent> pa = new HashSet<Pacijent>();
		novaKlinika.setPacijenti(pa);
		Set<Lekar> l = new HashSet<Lekar>();
		novaKlinika.setLekari(l);
		Set<MedicinskaSestra> ms = new HashSet<MedicinskaSestra>();
		novaKlinika.setMed_sestre(ms);
		Set<Sala> s = new HashSet<Sala>();
		novaKlinika.setSale(s);
		
		
		
		this.save(novaKlinika);
		
		return "Uspešno registrovana klinika";
	}
	
	//ocenjivanje klinike
	public Boolean oceniKliniku(OcenaKlinikeDTO ocenaKlinikeDTO) {
		
//		System.out.println("ID KLINIKE: " + ocenaKlinikeDTO.getIdKlinike());
//		System.out.println("OCENA KLINIKE: " + ocenaKlinikeDTO.getOcena());
		Klinika klinika = findOne(ocenaKlinikeDTO.getIdKlinike());
		if(klinika == null) {
			return false;
		}
		
		klinika.setUkupnaOcena(klinika.getUkupnaOcena() + ocenaKlinikeDTO.getOcena());
		klinika.setBrojOcena(klinika.getBrojOcena() + 1);
		save(klinika);
		
		return true;
	}
	
}
