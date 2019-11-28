package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.ZdravstveniKartonDTO;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.service.ZdravstveniKartonService;

@RestController
@RequestMapping(value = "zdravstveniKarton")
public class ZdravstveniKartonController {

	@Autowired
	private ZdravstveniKartonService ZKservice;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ZdravstveniKartonDTO> getPacijent(@PathVariable Long id) {

		ZdravstveniKarton zdravstveniKarton = ZKservice.findOne(id);

		if (zdravstveniKarton == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ZdravstveniKartonDTO(zdravstveniKarton), HttpStatus.OK);
	}
	
}
