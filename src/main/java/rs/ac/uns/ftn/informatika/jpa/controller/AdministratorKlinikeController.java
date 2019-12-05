package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinickogCentraDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.service.AdministratorKlinikeService;

@RestController
@RequestMapping(value = "adminK")
public class AdministratorKlinikeController {
	
	@Autowired
	private AdministratorKlinikeService adminKservice;
	
	@PostMapping(value="/registrujAdminK", consumes="application/json")
	public ResponseEntity<AdministratorKlinikeDTO> dodajAdminK(@RequestBody AdministratorKlinikeDTO admin) 
	{

		AdministratorKlinike administrator = adminKservice.kreirajAdminK(admin);
		
		if(administrator == null)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		adminKservice.save(administrator);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
}
