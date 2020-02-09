package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.HashMap;
import java.util.List;

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

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTOzaStrudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTOzaStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZdravsveniKartonInfoDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacijentDTO> getPacijent(@PathVariable Long id) {

		Pacijent pacijent = pacijentService.findOne(id);

		if (pacijent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PacijentDTO> updatePacijent(@PathVariable Long id, @RequestBody PacijentDTO pacijentDTO) {

		//Ako nema pacijentra sa trazen id-em ILI ako je nova lozinka prekratka
		if(pacijentService.updatePacijent(id, pacijentDTO) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
					
		return new ResponseEntity<>(new PacijentDTO(pacijentService.updatePacijent(id, pacijentDTO)), HttpStatus.OK);
	}
	
	//vraca pacijente klinike sa datim id-jem
	@GetMapping(value = "vratiPacijente/{id}", consumes = "application/json")
	public ResponseEntity<List<PacijentDTO>> vratiPacijenteKlinike(@PathVariable Long id) 
	{
		//System.out.println("###############-PRE-##############");
		List<PacijentDTO> pacijentiKlinike = pacijentService.vratiSvePacijenteKlinike(id);
		//System.out.println("###############-POSLE-##############");	
		return new ResponseEntity<>(pacijentiKlinike, HttpStatus.OK);
	}
	//vraca pacijente klinike pomocu lekara sa datim id-jem
	@GetMapping(value = "vratiPacijenteLekaraKlinike/{id}", consumes = "application/json")
	public ResponseEntity<List<PacijentDTO>> vratiPacijenteLekaraKl(@PathVariable Long id) 
	{
		//System.out.println("###############-PRE-##############");
		List<PacijentDTO> pacijentiKlinike = pacijentService.vratiSvePacijenteLekara(id);
		//System.out.println("###############-POSLE-##############");	
		return new ResponseEntity<>(pacijentiKlinike, HttpStatus.OK);
	}
	
	//vraca sve klinike u kojema je pacijent bio
	@GetMapping(value = "getPoseceneKlinike/{id}")
	public ResponseEntity<List<KlinikaDTOzaStrudent1>> getVisitedKlinike(@PathVariable Long id) 
	{
		List<KlinikaDTOzaStrudent1> poseceneKlinike = pacijentService.getPoseceneKlinike(id);
		
		return new ResponseEntity<>(poseceneKlinike, HttpStatus.OK);
	}
	
	//vraca sve lekara kod kojih je pacijent bio
	@GetMapping(value = "getPoseceneLekare/{id}")
	public ResponseEntity<List<LekarDTOzaStudent1>> getVisitedLekare(@PathVariable Long id) 
	{
		List<LekarDTOzaStudent1> poseceniLekari = pacijentService.getPoseceneLekare(id);
		
		return new ResponseEntity<>(poseceniLekari, HttpStatus.OK);
	}
	
	//vraca pacijente klinike sa datim id-jem lekara ciji pregled jos nije obavljen
	@GetMapping(value = "vratiSvePacijenteLekara/{id}", consumes = "application/json")
	public ResponseEntity<HashMap<Long, PacijentDTO>> vratiPacijenteLekara(@PathVariable Long id) 
	{
		//System.out.println("###############-PRE-##############");
		HashMap<Long, PacijentDTO> pacijentiLekara = pacijentService.vratiSvePacijenteDoktora(id);
			
		if (pacijentiLekara.isEmpty()) 
		{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
			
		//System.out.println("###############-POSLE-##############");	
		return new ResponseEntity<>(pacijentiLekara, HttpStatus.OK);
	}
	
	//vrati zdravstveni karton pacijenta sa datim id-jem
	@GetMapping(value = "getKartonInfo/{id}")
	public ResponseEntity<ZdravsveniKartonInfoDTO> getKarton(@PathVariable Long id) {

		ZdravstveniKarton zk = pacijentService.getKarton(id);

		if (zk == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ZdravsveniKartonInfoDTO(zk), HttpStatus.OK);
	}
	
	@PostMapping(value = "setKartonInfo/{id}")
	public ResponseEntity<String> setKarton(@PathVariable Long id, @RequestBody ZdravsveniKartonInfoDTO zk) {

		String poruka = pacijentService.setKarton(id, zk);

		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	
}
