package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
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
		
		this.save(novaKlinika);
		
		return "Uspešno registrovana klinika";
	}
}
