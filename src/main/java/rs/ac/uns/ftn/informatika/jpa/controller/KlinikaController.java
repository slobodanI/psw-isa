package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTOzaStrudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTOzaStudent1PRETRAGA;
import rs.ac.uns.ftn.informatika.jpa.dto.OcenaKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PretragaKlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

	@Autowired
	private KlinikaService klinikaService;
	
	//metoda za kreiranje nove klinike
	@PostMapping(value="/kreirajKliniku", consumes="application/json")
	public ResponseEntity<String> dodajKliniku(@RequestBody KlinikaDTO klinika) 
	{
		
		String poruka = klinikaService.kreirajKliniku(klinika);
				
				
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
	//vracanje svih klinika, za prikaz na pocetnoj strani pacijenta
	@GetMapping(value = "/all")
	public ResponseEntity<List<KlinikaDTOzaStrudent1>> getAllKlinike() {

		List<Klinika> klinike = klinikaService.findAll();

		// convert clinics to DTOs
		List<KlinikaDTOzaStrudent1> klinikeDTO = new ArrayList<>();
		for (Klinika k : klinike) {
			klinikeDTO.add(new KlinikaDTOzaStrudent1(k));
		}

		return new ResponseEntity<>(klinikeDTO, HttpStatus.OK);
	}
	
	//vracanje klinike, za profil klinike
	@GetMapping(value = "/{id}")
	public ResponseEntity<KlinikaDTOzaStrudent1> getKlinika(@PathVariable Long id) {

		Klinika klinika = klinikaService.findOne(id);

		if(klinika == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new KlinikaDTOzaStrudent1(klinika), HttpStatus.OK);
	}
	
	@PutMapping(value = "/oceniKliniku")
	public ResponseEntity<Void> oceniKliniku(@RequestBody OcenaKlinikeDTO ocenaKlinikeDTO) {
				
		if(klinikaService.oceniKliniku(ocenaKlinikeDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	/*
	 * Pretrazivanje klinika na osnovu: TIPA PREGLEDA i DATUMA
	 * */
	@PostMapping(value = "/pretraziKlinike")
	public ResponseEntity<List<KlinikaDTOzaStudent1PRETRAGA>> pretragaKlinika(@RequestBody PretragaKlinikaDTO pretragaKlinikaDTO){
		
		System.out.println("***DATUM: " + pretragaKlinikaDTO.getDatum() );
		System.out.println("***TIP PREGLEDA: " + pretragaKlinikaDTO.getTipPregleda());
		List<Klinika> klinike = klinikaService.pretraziKlinike(pretragaKlinikaDTO.getDatum(), pretragaKlinikaDTO.getTipPregleda());
		if(klinike == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// convert clinics to DTOs
		List<KlinikaDTOzaStudent1PRETRAGA> klinikeDTO = new ArrayList<>();
		for (Klinika k : klinike) {
			System.out.println("NAZIV KLINIKE: " + k.getNaziv());
			System.out.println("ID KLINIKE: " + k.getId());
			klinikeDTO.add(new KlinikaDTOzaStudent1PRETRAGA(k));
		}
		
		return new ResponseEntity<>(klinikeDTO, HttpStatus.OK);
	}
	
}
