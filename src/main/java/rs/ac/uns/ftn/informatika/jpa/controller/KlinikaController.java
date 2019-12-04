package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
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
	
}
