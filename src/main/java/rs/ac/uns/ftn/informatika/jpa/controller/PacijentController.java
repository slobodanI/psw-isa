package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacijentDTO> getPacijent(@PathVariable Long id) {

		Pacijent pacijent = pacijentService.findOne(id);

		// studen must exist
		if (pacijent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.OK);
	}
	
	
}
