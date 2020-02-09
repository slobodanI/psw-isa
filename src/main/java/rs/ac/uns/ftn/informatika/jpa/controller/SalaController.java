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

import rs.ac.uns.ftn.informatika.jpa.dto.DodajSaluDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.SalaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZauzetostSalaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.PretragaSale;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.OperacijaService;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;
import rs.ac.uns.ftn.informatika.jpa.service.SalaService;

@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private OperacijaService operacijaService;

	@GetMapping(value = "/sale")
	public ResponseEntity<List<SalaDTO>> getSale() {
		List<Sala> sale = salaService.findAll();

		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) {
			saleDTO.add(new SalaDTO(s));
		}

		return new ResponseEntity<>(saleDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SalaDTO> getSalu(@PathVariable Long id) {
		Sala sala = salaService.findOne(id);
		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteSalu(@PathVariable Long id) {
		List<Pregled> pregledi = pregledService.findAll();
		List<Operacija> operacije = operacijaService.findAll();
		boolean flag = false;
		
		
		Sala sala = salaService.findOne(id);
		if (sala != null) {
			for(Pregled p : pregledi) {
				if(p.getSala().equals(sala)) 
				{
					flag=true;
				}
		}	

			if(flag==false) {
			salaService.remove(sala.getId());
			return new ResponseEntity<>(HttpStatus.OK);
			}
			
			salaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping(value = "/updateSalu", consumes = "application/json")
	public ResponseEntity<SalaDTO> updateSalu(@RequestBody SalaDTO salaDTO) {

		Sala sala = salaService.findOne(salaDTO.getId());

		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		sala.setNaziv(salaDTO.getNaziv());

		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}

	@PostMapping(value = "/addSalu", consumes = "application/json")
	public ResponseEntity<SalaDTO> addSalu(@RequestBody DodajSaluDTO salaDTO) {

		Klinika klinika = klinikaService.findOne(salaDTO.getIdKlinike());
		Sala sala = new Sala();
		sala.setNaziv(salaDTO.getNaziv());
		sala.setKlinika(klinika);

		List<Sala> sveSale = salaService.findAll();
		for (Sala s : sveSale) {
			if (s.getNaziv().equals(sala.getNaziv())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}
	
	@PostMapping(value="/pretragaSala",consumes ="application/json")
	public ResponseEntity<List<SalaDTO>> pretragaSala(@RequestBody PretragaSale pretragaSala){
			List<Sala> sale = salaService.findAll();
			
			List<SalaDTO> nadjeneSale = new ArrayList<>();
			for(Sala s : sale) {
				boolean flag=true;
				
				if(!pretragaSala.getNaziv().equals("")) {
					if(!s.getNaziv().contains(pretragaSala.getNaziv())) {
						flag=false;
						continue;
					}
						
				}
//				Double pom = (double) pretragaSala.getId();
//				Double sid=(double) pretragaSala.getId();
//				if(pom != null) {
//					if(sid!=pom) {
//						flag=false;
//						continue;
//						
//					}
//					
//				}
				
				if(flag==true) {
				nadjeneSale.add(new SalaDTO(s));
			}
			}
			return new ResponseEntity<>(nadjeneSale, HttpStatus.OK);
		
	}
	
	//Dobavljanje sala koje su slobodne za vreme pregleda
	@GetMapping(value="/prikaziSaleZaPregled/{idPregleda}",consumes="application/json")
	public ResponseEntity<List<SalaDTO>> prikaziSaleZaPreglede(@PathVariable Long idPregleda)
	{
		List<SalaDTO> saleKlinike = salaService.vratiSlobodneSaleZaPregled(idPregleda);
		return new ResponseEntity<>(saleKlinike,HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "vratiSaleKlinike/{id}", consumes = "application/json")
	public ResponseEntity<List<SalaDTO>> vratiSaleKlinike(@PathVariable Long id) 
	{
		//System.out.println("###############-PRE-##############");
		List<SalaDTO> saleKlinike = salaService.vratiSveSaleKlinike(id);
		//System.out.println("###############-POSLE-##############");	
		return new ResponseEntity<>(saleKlinike, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getZauzetostSala/{id}",produces = "application/json")
	public ResponseEntity<List<ZauzetostSalaDTO>> getZauzetostSala(@PathVariable Long id){
		
		List<ZauzetostSalaDTO> z = salaService.getZauzetostSala(id);
		return new ResponseEntity<>(z, HttpStatus.OK);	
		
	}
	
	

}
