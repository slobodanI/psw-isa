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

import rs.ac.uns.ftn.informatika.jpa.dto.DijagnozaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarOdsustvoDTO;
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
	
	@PutMapping(value="/odobriOdsustvo/{id}")
	public ResponseEntity<String> odobriOdsustvo(@PathVariable("id") Long id){
		
		String str=odsustvoService.odobriOdsustvo(id);
		return new ResponseEntity<>(str,HttpStatus.OK);
		
	}

	
	@PutMapping(value="/odbijOdsustvo/{id}")
	public ResponseEntity<String> odbijOdsustvo(@PathVariable("id") Long id,@RequestBody String string){
		
		String st = odsustvoService.odbijOdsustvo(id, string);
		System.out.println("AAAAAAA"+string);
		return new ResponseEntity<>(st,HttpStatus.OK);
	}
	
	@GetMapping(value = "/vratiZahteveZaOdsustva/{id}")
	public ResponseEntity<List<OdsustvoDTO>> vratiZahteveZaOdsustva(@PathVariable("id") Long id){
		List<OdsustvoDTO> lista = odsustvoService.vratiZahteveZaOdsustva(id);
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
}
	

