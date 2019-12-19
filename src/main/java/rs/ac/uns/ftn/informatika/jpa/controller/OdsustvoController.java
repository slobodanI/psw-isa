package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.DijagnozaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.OdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.service.OdsustvoService;

@RestController
@RequestMapping(value = "odsustvo")
public class OdsustvoController 
{
	@Autowired
	private OdsustvoService odsustvoService;
	
	//metoda za kreiranje odsustva
	@PostMapping(value="/kreirajOdsustvo", consumes="application/json")
	public ResponseEntity<String> dodajOdsustvo(@RequestBody OdsustvoDTO ods) 
	{

		String poruka = odsustvoService.kreirajOdsustvo(ods);
				
				
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
}
