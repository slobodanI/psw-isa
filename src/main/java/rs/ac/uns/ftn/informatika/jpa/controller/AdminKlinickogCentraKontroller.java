package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinickogCentraDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.UpdateLekarDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
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
	
	@GetMapping(value = "/promenjenaLozinka/{id}")
	public ResponseEntity<Boolean> promenjenaLozinka(@PathVariable Long id){
			Boolean pom=adminKCservice.promenjenaLozinka(id);
			return new ResponseEntity<>(pom,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdministratorKlinickogCentraDTO> getAdmin(@PathVariable Long id) {
		
		AdministratorKlinickogCentra admin = adminKCservice.findOne(id);
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new AdministratorKlinickogCentraDTO(admin), HttpStatus.OK);

	}
	
	@PutMapping(value = "/updateAdmin", consumes = "application/json")
	public ResponseEntity<AdministratorKlinickogCentraDTO> updateLekar( @RequestBody AdministratorKlinickogCentraDTO adminDTO) {

		
		AdministratorKlinickogCentra administrator = adminKCservice.findOne(adminDTO.getId());
		if (administrator == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		administrator.setIme(adminDTO.getIme());
		administrator.setPrezime(adminDTO.getPrezime());
		administrator.setPassword(adminDTO.getPassword());

		administrator = adminKCservice.save(administrator);
		return new ResponseEntity<>(new AdministratorKlinickogCentraDTO(administrator), HttpStatus.OK);

	}
	
	
	
}
