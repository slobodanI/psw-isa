package rs.ac.uns.ftn.informatika.jpa.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTOStudent1PretragaLekara;
import rs.ac.uns.ftn.informatika.jpa.dto.OcenaKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PretragaKlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PretragaLekaraPrekoKlinikeDTO;
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
	
	@GetMapping(value = "/pretraziLekareOdabraneKlinike/{klinikaID}/datum/{datum}/idTipaPregleda/{tipPregledaID}") 
	public ResponseEntity<List<LekarDTOStudent1PretragaLekara>> pretragaLekaraUKlinici(@PathVariable Long klinikaID, @PathVariable String datum, @PathVariable Long tipPregledaID){
		
		System.out.println("***KLINIKA ID: " + klinikaID );
		System.out.println("***DATUM: " + datum );
		System.out.println("***TIP PREGLEDA: " + tipPregledaID);
		
		String[] split = datum.split("T");
		String datumBezT = split[0] + " " + split[1];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime datumLDT = LocalDateTime.parse(datumBezT, formatter);
		
		List<LekarDTOStudent1PretragaLekara> lekariDTO = klinikaService.pretraziLekareUKlinici(klinikaID, datumLDT, tipPregledaID);
		if(lekariDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(lekariDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/pretraziLekarePrekoKlinike") 
	public ResponseEntity<List<LekarDTOStudent1PretragaLekara>> pretragaLekaraPrekoKlinike(@RequestBody PretragaLekaraPrekoKlinikeDTO plpkDTO){
		
		System.out.println("***KLINIKA ID: " + plpkDTO.getKlinikaID() );
		System.out.println("***DATUM: " + plpkDTO.getDatum() );
		System.out.println("***TIP PREGLEDA: " + plpkDTO.getTipPregleda());
		System.out.println("***IME LEKARA: " + plpkDTO.getImeLekara()); // bude prazan string, ako se ne unese nista
		System.out.println("***PREZIME LEKARA: " + plpkDTO.getPrezimeLekara()); // bude prazan string, ako se ne unese nista
		System.out.println("***OCENA VECA OD: " + plpkDTO.getOcenaVecaOd()); // bude null, ako se ne unese nista
		
		List<LekarDTOStudent1PretragaLekara> lekariDTO = klinikaService.pretraziLekareUKliniciPrekoKlinike(plpkDTO.getKlinikaID(), plpkDTO.getDatum(), plpkDTO.getTipPregleda(),
																										plpkDTO.getImeLekara(), plpkDTO.getPrezimeLekara(), plpkDTO.getOcenaVecaOd());
		if(lekariDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(lekariDTO, HttpStatus.OK);
	}
	
}
