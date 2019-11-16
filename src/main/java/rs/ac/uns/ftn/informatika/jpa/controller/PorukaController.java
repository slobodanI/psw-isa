package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
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

import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StudentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.model.Student;
import rs.ac.uns.ftn.informatika.jpa.service.PorukaService;


@RestController
@RequestMapping(value = "apiPoruke")
public class PorukaController {
	
	@Autowired
	private PorukaService porukaService;
	
	@GetMapping(value = "/getPoruke")
	public ResponseEntity<List<PorukaDTO>> vratiSvePoruke() {

		List<Poruka> svePoruke = porukaService.findAll();
		
		List<PorukaDTO> porukeDTO = new ArrayList<>();
		for (Poruka p : svePoruke) {
			porukeDTO.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(porukeDTO, HttpStatus.OK);
		
	}
	
	@PostMapping(value="/odgovoreno/{id}")
	public ResponseEntity<PorukaDTO> updatePoruka(@PathVariable Long id) {

		Poruka poruka = porukaService.findOne(id);

		if (poruka == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		poruka.setOdgovoreno(true);

		poruka = porukaService.save(poruka);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
