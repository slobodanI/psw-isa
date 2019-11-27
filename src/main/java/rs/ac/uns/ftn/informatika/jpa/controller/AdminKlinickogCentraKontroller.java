package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinickogCentraDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.service.AdministratorKlinikeService;
import rs.ac.uns.ftn.informatika.jpa.service.AministratorKlinickogCentraService;

@RestController
@RequestMapping(value = "adminKC")
public class AdminKlinickogCentraKontroller 
{
	@Autowired
	private AministratorKlinickogCentraService adminKCservice;
	
	@PostMapping(value="/registrujAdminKC", consumes="application/json")
	public ResponseEntity<AdministratorKlinickogCentraDTO> dodajAdminKC(@RequestBody AdministratorKlinickogCentraDTO admin) 
	{

		AdministratorKlinickogCentra administrator = adminKCservice.kreirajAdminKC(admin);
		
		if(administrator == null)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		adminKCservice.save(administrator);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
}
