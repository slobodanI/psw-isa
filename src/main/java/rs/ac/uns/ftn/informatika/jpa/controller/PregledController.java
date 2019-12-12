package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
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
	
}
