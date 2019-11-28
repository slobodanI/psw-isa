package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinickogCentraDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.service.LekService;

@RestController
@RequestMapping(value = "lek")
public class LekController 
{

	@Autowired
	private LekService lekService;
	
	//metoda za dobavljanje svih lekova
	@GetMapping(value = "/getLekovi")
	public ResponseEntity<List<LekDTO>> vratiSveLekove() {

		List<Lek> sviLekovi = lekService.findAll();
			
		List<LekDTO> lekoviDTO = lekService.vratiSveLekoveDTO(sviLekovi);
			
		return new ResponseEntity<>(lekoviDTO, HttpStatus.OK);
			
	}
	
	
	//metoda za kreiranje novog leka
	@PostMapping(value="/kreirajLek", consumes="application/json")
	public ResponseEntity<String> dodajAdminKC(@RequestBody LekDTO lek) 
	{

		String poruka = lekService.kreirajLek(lek);
		
		
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
}
