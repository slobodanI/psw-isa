package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StudentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Student;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacijentDTO> getPacijent(@PathVariable Long id) {

		Pacijent pacijent = pacijentService.findOne(id);

		if (pacijent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PacijentDTO> updatePacijent(@PathVariable Long id, @RequestBody PacijentDTO pacijentDTO) {

		//Ako nema pacijentra sa trazen id-em ILI ako je nova lozinka prekratka
		if(pacijentService.updatePacijent(id, pacijentDTO) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
					
		return new ResponseEntity<>(new PacijentDTO(pacijentService.updatePacijent(id, pacijentDTO)), HttpStatus.OK);
	}
	
	
}
