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

import rs.ac.uns.ftn.informatika.jpa.dto.DijagnozaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.service.DijagnozaService;

@RestController
@RequestMapping(value = "dijagnoza")
public class DijagnozaController 
{

	@Autowired
	private DijagnozaService dijagnozaService;
	
	//metoda za dobavljanje svih dijagnoza
	@GetMapping(value = "/getDijagnoza")
	public ResponseEntity<List<DijagnozaDTO>> vratiSveDijagnoze() 
	{

		List<Dijagnoza> sveDijagnoze = dijagnozaService.findAll();
				
		List<DijagnozaDTO> dijagnozeDTO = dijagnozaService.vratiSveDijagnozeDTO(sveDijagnoze);
				
		return new ResponseEntity<>(dijagnozeDTO, HttpStatus.OK);
				
	}
	
	//metoda za kreiranje nove dijagnoze
	@PostMapping(value="/kreirajDijagnozu", consumes="application/json")
	public ResponseEntity<String> dodajAdminKC(@RequestBody DijagnozaDTO dijag) 
	{

		String poruka = dijagnozaService.kreirajDijagnozu(dijag);
			
			
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
}
