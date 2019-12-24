package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.LekarOdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.service.LekarOdsustvoService;

@RestController
@RequestMapping(value = "lekarOdsustvo")
public class LekarOdsustvoController {
	
	@Autowired
	private LekarOdsustvoService loService;
	
	@PostMapping(value="/kreirajOdsustvo", consumes="application/json")
	public ResponseEntity<String> dodajOdsustvo(@RequestBody LekarOdsustvoDTO ods) 
	{

		String poruka = loService.kreirajOdsustvo(ods);
				
				
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}

}
