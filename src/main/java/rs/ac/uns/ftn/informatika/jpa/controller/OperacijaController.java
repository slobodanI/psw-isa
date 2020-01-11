package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.OperacijaDTOStudent2;
import rs.ac.uns.ftn.informatika.jpa.service.OperacijaService;

@RestController
@RequestMapping(value = "operacije")
public class OperacijaController {
	
	@Autowired
	private OperacijaService operacijaService;
	
	@PostMapping(value = "/dodajNovuOperaciju")
	public ResponseEntity<String> saveNovuOperaciju(@RequestBody OperacijaDTOStudent2 operacijaDTO){
		
		String operacija = operacijaService.dodajNovuOperaciju(operacijaDTO);
		return new ResponseEntity<>("Uspesno dodata operacija",HttpStatus.OK);
		
		
	}
	

}
