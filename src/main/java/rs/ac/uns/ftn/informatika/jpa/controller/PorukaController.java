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

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.PorukaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Kontroler za klasu Poruka
//(poruka koja stize administratoru kad se kreira zahtev za kreiranje naloga pacijenta)
@RestController
@RequestMapping(value = "apiPoruke")
public class PorukaController {
	
	private Logger logger = LoggerFactory.getLogger(PorukaController.class);
	
	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private EmailService emailService;
	
	//metoda za dobavljanje svih poruka
	@GetMapping(value = "/getPoruke")
	public ResponseEntity<List<PorukaDTO>> vratiSvePoruke() {

		List<Poruka> svePoruke = porukaService.findAll();
		
		List<PorukaDTO> porukeDTO = new ArrayList<>();
		for (Poruka p : svePoruke) {
			porukeDTO.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(porukeDTO, HttpStatus.OK);
		
	}
	
	//metoda koja postavlja atribut odgovoreno na true
	//oznacava da je na poruku vec odgovoreno
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
	
	@PostMapping(value="/posaljiEmail", consumes = "application/json")
	public ResponseEntity<EmailDTO> updatePoruka(@RequestBody EmailDTO email)  
	{
		try 
		{
			//emailService.sendNotificaitionSync(email);
			emailService.sendNotificaitionAsync(email);
		}
		catch( Exception e )
		{
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			return new ResponseEntity<EmailDTO>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<EmailDTO>(HttpStatus.OK);
	}
	
}
