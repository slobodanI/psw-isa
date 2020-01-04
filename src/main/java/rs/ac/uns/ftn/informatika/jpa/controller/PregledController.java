package rs.ac.uns.ftn.informatika.jpa.controller;

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

import rs.ac.uns.ftn.informatika.jpa.dto.PredefPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.PromenaPregledaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StariPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;

@RestController
@RequestMapping(value = "pregledi")
public class PregledController 
{
	@Autowired
	private PregledService pregledSevice;
	
	//metoda popunjava informacije jednog pregleda
	@PostMapping(value="/zavrsiPregled", consumes = "application/json")
	public ResponseEntity<ZavrsiPregledDTO> zavrsiPregled(@RequestBody ZavrsiPregledDTO preg)  
	{
		
		pregledSevice.popuniPregled(preg);
		
		return new ResponseEntity<ZavrsiPregledDTO>(HttpStatus.OK);
	}

	@GetMapping(value = "/predefPregledi/{klinikaID}")
	public ResponseEntity<List<PregledDTOStudent1>> dobaviPredefPreglede(@PathVariable Long klinikaID){
		
		List<PregledDTOStudent1> predefPregledi = pregledSevice.getPredefinisanePreglede(klinikaID);
		
		return new ResponseEntity<>(predefPregledi, HttpStatus.OK);
	}
	
	@PutMapping(value = "/zakaziPredefPregled/{pregledID}/pacijent/{pacijentID}")
	public ResponseEntity<Void> zakaziPredefPregled(@PathVariable Long pregledID, @PathVariable Long pacijentID){
		
		if(pregledSevice.zakaziPredefPregled(pregledID, pacijentID)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//metoda vraca obavljene preglede doktora sa datim ID-jem
	@GetMapping(value = "/vratiStarePreglede/{lekarID}")
	public ResponseEntity<List<StariPregledDTO>> dobaviStarePreglede(@PathVariable Long lekarID){
		
		List<StariPregledDTO> pregledi = pregledSevice.vratiStarePreglede(lekarID);
		
		return new ResponseEntity<>(pregledi, HttpStatus.OK);
	}
	
	//vraca pregled koji ce biti menjan
	@GetMapping(value = "/vratiPregled/{id}")
	public ResponseEntity<PromenaPregledaDTO> vratiPregled(@PathVariable Long id)
	{
			
		PromenaPregledaDTO pregled = pregledSevice.vratiPregled(id);
			
		return new ResponseEntity<>(pregled, HttpStatus.OK);
	}
	
	//menjanje pregleda
	@PostMapping(value = "/promeniPregled")
	public ResponseEntity<String> promeniPregled(@RequestBody PromenaPregledaDTO promena)
	{
		String poruka = pregledSevice.promeniPregled(promena);
		
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
	@PostMapping(value = "/dodajPredefinisaniPregled")
	public ResponseEntity<String> savePredefPregled(@RequestBody PredefPregledDTO pregledDTO){
		
		String pregled = pregledSevice.dodajPredefinisaniPregled(pregledDTO);
		
		if(pregled == "Ne moze kraj pregleda biti pre pocetka pregleda") {
			return new ResponseEntity<>("Ne moze kraj pregleda biti pre pocetka pregleda",HttpStatus.CONFLICT);
		}
		else if(pregled == "Odabrani lekar je zauzet u odabranom terminu.") {
			return new ResponseEntity<>("Odabrani lekar je zauzet u odabranom terminu.",HttpStatus.CONFLICT);
		}
		else if(pregled == "Odabrana sala je zauzeta u odabranom terminu.") {
			return new ResponseEntity<>("Odabrana sala je zauzeta u odabranom terminu.",HttpStatus.CONFLICT);
		}else {
		
		return new ResponseEntity<>("Uspesno dodat pregled",HttpStatus.OK);
		}
		
	}
	
	
	
	
	
	
	
}
