package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.PretragaLekara;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.LekarService;

@RestController
@RequestMapping(value = "/api/lekar")
public class LekarController {

	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private KlinikaService klinikaService;

	@GetMapping(value = "/lekari")
	public ResponseEntity<List<LekarDTO>> getLekari() {

		List<Lekar> lekari = lekarService.findAll();

		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar l : lekari) {
			lekariDTO.add(new LekarDTO(l));
		}

		return new ResponseEntity<>(lekariDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LekarDTO> getLekar(@PathVariable Long id) {
		Lekar lekar = lekarService.findOne(id);

		if (lekar == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new LekarDTO(lekar), HttpStatus.OK);

	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deleteLekar(@PathVariable Long id){
		
		Lekar lekar = lekarService.findOne(id);
		if(lekar != null) {
			lekarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PutMapping(value = "/updateLekar", consumes = "application/json")
	public ResponseEntity<LekarDTO> updateLekar(@RequestBody LekarDTO lekarDTO) {

		Lekar lekar = lekarService.findOne(lekarDTO.getId());

		if (lekar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		lekar.setIme(lekarDTO.getIme());
		lekar.setPrezime(lekarDTO.getPrezime());
		lekar.setPassword(lekarDTO.getPassword());

		lekar = lekarService.save(lekar);
		return new ResponseEntity<>(new LekarDTO(lekar), HttpStatus.OK);

	}
	
	@PostMapping(value="/saveLekar",consumes = "application/json")
	public ResponseEntity<LekarDTO> saveLekar(@RequestBody LekarDTO lekarDTO){
		
		if(lekarDTO.getPassword().length()<6) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klinika klinika = klinikaService.findOne(lekarDTO.getKlinika().getId());
		Lekar lekar = new Lekar();
		lekar.setIme(lekarDTO.getIme());
		lekar.setPrezime(lekarDTO.getPrezime());
		lekar.setUkupnaOcena(lekarDTO.getUkupnaOcena());
		lekar.setBrojOcena(lekarDTO.getBrojOcena());
		lekar.setUsername(lekarDTO.getUsername());
		lekar.setPassword(lekarDTO.getPassword());
		lekar.setRadnoVreme(lekarDTO.getRadnoVreme());
		lekar.setRadniKalendar(lekarDTO.getRadniKalendar());
		lekar.setUloga(lekarDTO.getUloga());
		lekar.setKlinika(klinika);
		
		List<Lekar> sviLekari = lekarService.findAll();
		for(Lekar l : sviLekari) {
			if(l.getUsername().equals(lekar.getUsername())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		lekar = lekarService.save(lekar);
		return new ResponseEntity<>(new LekarDTO(lekar),HttpStatus.CREATED);
		
	}
	
	@PostMapping(value="/pretragaLekara",consumes = "application/json")
	public ResponseEntity<List<LekarDTO>> pretragaLekara(@RequestBody PretragaLekara pretragaLekara){
		List<Lekar> lekari = lekarService.findAll();

		List<LekarDTO> nadjeniLekari = new ArrayList<>();
		for (Lekar l : lekari) {
			boolean flag=true;
			
			if(!pretragaLekara.getIme().equals("")) {
				if(!l.getIme().contains(pretragaLekara.getIme())) {
					flag=false;
					continue;
				}
				
			}
			if(!pretragaLekara.getPrezime().equals("")) {
				if(!l.getPrezime().contains(pretragaLekara.getPrezime())) {
					flag=false;
					continue;
				}
				
			}
			Double pros=(l.getUkupnaOcena())/(l.getBrojOcena());
			
			if(pretragaLekara.getProsecnaOcenaOd()!=null) {
				if(pros<pretragaLekara.getProsecnaOcenaOd()) {
					flag=false;
					continue;
					
				}
			}
			if(pretragaLekara.getProsecnaOcenaDo()!=null) {
				if(pros>pretragaLekara.getProsecnaOcenaDo()) {
					flag=false;
					continue;
					
				}
			}
			String[] radnov=l.getRadnoVreme().split("-");

			
			if(flag==true) {
			nadjeniLekari.add(new LekarDTO(l));
		}
		}
		
		return new ResponseEntity<>(nadjeniLekari, HttpStatus.OK);
		
	}
	
}
