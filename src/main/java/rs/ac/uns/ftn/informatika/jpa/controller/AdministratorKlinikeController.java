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

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.UpdateAdminKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.UpdateLekarDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
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
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdministratorKlinikeDTO> getLekar(@PathVariable Long id) {
		AdministratorKlinike administrator = adminKservice.findOne(id);

		if (administrator == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AdministratorKlinikeDTO(administrator), HttpStatus.OK);

	}
	
	@PutMapping(value = "/updateAdminK", consumes = "application/json")
	public ResponseEntity<UpdateAdminKlinikeDTO> updateAdmin( @RequestBody UpdateAdminKlinikeDTO adminDTO) {

		AdministratorKlinike admin = adminKservice.findOne(adminDTO.getId());

		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		admin.setIme(adminDTO.getIme());
		admin.setPrezime(adminDTO.getPrezime());
		admin.setPassword(adminDTO.getPassword());
		admin.setEmail(adminDTO.getEmail());

		admin = adminKservice.save(admin);
		return new ResponseEntity<>(new UpdateAdminKlinikeDTO(admin), HttpStatus.OK);

	}
	
	@GetMapping(value = "/promenjenaLozinka/{id}")
	public ResponseEntity<Boolean> promenjenaLozinka(@PathVariable Long id){
			Boolean pom=adminKservice.promenjenaLozinka(id);
			return new ResponseEntity<>(pom,HttpStatus.OK);
	}
	
}
