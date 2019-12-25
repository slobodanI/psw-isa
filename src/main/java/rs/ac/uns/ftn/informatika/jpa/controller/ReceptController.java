package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.ReceptDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ReceptIdDTO;
import rs.ac.uns.ftn.informatika.jpa.service.ReceptService;


@RestController
@RequestMapping(value = "recept")
public class ReceptController 
{	
	@Autowired
	private ReceptService receptService;
	
	@GetMapping(value = "/recepti/{id}")
	public ResponseEntity<List<ReceptDTO>> getRecepti(@PathVariable Long id) {

		List<ReceptDTO> recepti = receptService.getRecepti(id);

		return new ResponseEntity<>(recepti, HttpStatus.OK);
	}
	
	@PostMapping(value = "/overi/{id}", consumes = "application/json")
	public ResponseEntity<String> overiRecept(@PathVariable Long id, @RequestBody ReceptIdDTO recID) 
	{
		Long id_recepta = recID.getId_recepta();

		String poruka = receptService.overi(id, id_recepta);

		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
}
