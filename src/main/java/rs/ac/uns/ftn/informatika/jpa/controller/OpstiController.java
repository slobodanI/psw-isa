package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;


@RestController
@RequestMapping(value = "/api")
public class OpstiController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@PostMapping(value = "/savePacijent",consumes = "application/json")
	public ResponseEntity<PacijentDTO> savePacijent(@RequestBody PacijentDTO pacijentDTO) {

		Pacijent pacijent= new Pacijent();
		pacijent.setIme(pacijentDTO.getIme());
		pacijent.setPrezime(pacijentDTO.getPrezime());
		pacijent.setUsername(pacijentDTO.getUsername());
		pacijent.setPassword(pacijentDTO.getPassword());
		pacijent.setEmail(pacijentDTO.getEmail());
		pacijent.setAdresa(pacijentDTO.getAdresa());
		pacijent.setGrad(pacijentDTO.getGrad());
		pacijent.setDrzava(pacijentDTO.getDrzava());
		pacijent.setBrojTel(pacijentDTO.getBrojTel());
		pacijent.setLbo(pacijentDTO.getLbo());
		pacijent.setAktiviranNalog(false);

		List<Pacijent> sviPacijenti = pacijentService.findAll();
		for(Pacijent p : sviPacijenti) {
			if(p.getUsername().equals(pacijent.getUsername()) || p.getLbo().equals(pacijent.getLbo())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
				
		pacijent = pacijentService.save(pacijent);
		
		return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.CREATED);
		
	}
	
}
