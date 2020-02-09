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

import rs.ac.uns.ftn.informatika.jpa.dto.CenovnikDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.TipPregledaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Cenovnik;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;
import rs.ac.uns.ftn.informatika.jpa.service.AdministratorKlinikeService;
import rs.ac.uns.ftn.informatika.jpa.service.CenovnikService;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;
import rs.ac.uns.ftn.informatika.jpa.service.TipPregledaService;

@RestController
@RequestMapping(value = "/api/tipPregleda")
public class TipPregledaController {
	
	@Autowired
	private TipPregledaService tipPregledaService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private AdministratorKlinikeService adminKService;
	
	@Autowired 
	private CenovnikService cenovnikService;
	
	@Autowired
	private PregledService pregledService;
	
	@GetMapping(value = "/tipoviPregleda")
	public ResponseEntity<List<TipPregledaDTO>> getTipovePregleda() {
		List<TipPregleda> tipoviPregleda = tipPregledaService.findAll();

		List<TipPregledaDTO> tipoviDTO = new ArrayList<>();
		for (TipPregleda t : tipoviPregleda) {
			tipoviDTO.add(new TipPregledaDTO(t));
		}

		return new ResponseEntity<>(tipoviDTO, HttpStatus.OK);

	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipPregledaDTO> getTipPregleda(@PathVariable Long id) {
		TipPregleda tipPregleda = tipPregledaService.findOne(id);
		if (tipPregleda == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.OK);

	}
	
	@PutMapping(value = "/getCenu",consumes = "application/json")
	public ResponseEntity<Long> getCenu(@RequestBody CenovnikDTO cenovnikDTO) {
		AdministratorKlinike admin = adminKService.findOne(cenovnikDTO.getAdmin());
		Klinika kl = admin.getKlinika();
		Long rez = null;
		TipPregleda tipPregleda = tipPregledaService.findOne(cenovnikDTO.getTipPregleda());
		
		

		boolean flag = false;
		List<Cenovnik> sveCene = cenovnikService.findAll();
		for (Cenovnik c : sveCene) {
			if (c.getKlinika().equals(kl) && c.getTipPregleda().equals(tipPregleda)) {
				rez=c.getCena();
				System.out.println(rez);
				flag= true;
			}
		}
		if(flag==false) { rez =0L;}

		return new ResponseEntity<>(rez,HttpStatus.OK);
		
		
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteTipPregleda(@PathVariable Long id) {
		List<Pregled> pregledi = pregledService.findAll();
		boolean flag = false;
		TipPregleda tp = tipPregledaService.findOne(id);
		if (tp != null) {
			
			for(Pregled p : pregledi) {
				if(p.getTipPregleda().equals(tp)) 
				{
					flag=true;
				}
			}
			if(flag == false) {
			tipPregledaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	@PutMapping(value = "/updateTipPregleda", consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> updateSalu(@RequestBody TipPregledaDTO tpDTO) {

		TipPregleda tipPregleda = tipPregledaService.findOne(tpDTO.getId());

		if (tipPregleda == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		tipPregleda.setNaziv(tpDTO.getNaziv());


		tipPregleda = tipPregledaService.save(tipPregleda);
		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.OK);

	}
	
	@PostMapping(value = "/addTipPregleda", consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> addSalu(@RequestBody TipPregledaDTO tpDTO) {

		
		TipPregleda tipPregleda = new TipPregleda();
		tipPregleda.setNaziv(tpDTO.getNaziv());

		List<TipPregleda> sviTipovi = tipPregledaService.findAll();
		for (TipPregleda t : sviTipovi) {
			if (t.getNaziv().equals(tipPregleda.getNaziv())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		tipPregleda = tipPregledaService.save(tipPregleda);
		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.OK);

	}
	
	@PostMapping(value = "/addCenu", consumes = "application/json")
	public ResponseEntity<String> addCenu(@RequestBody CenovnikDTO cenovnikDTO) {
		 
		AdministratorKlinike admin = adminKService.findOne(cenovnikDTO.getAdmin());
		Klinika kl = admin.getKlinika();
		
		TipPregleda tipPregleda = tipPregledaService.findOne(cenovnikDTO.getTipPregleda());
		
		

		boolean flag = false;
		List<Cenovnik> sveCene = cenovnikService.findAll();
		for (Cenovnik c : sveCene) {
			if (c.getKlinika().equals(kl) && c.getTipPregleda().equals(tipPregleda)) {
				c.setCena(cenovnikDTO.getCena());
				cenovnikService.save(c);
				flag = true;
			}
		}
		
		if(flag == false) {
			Cenovnik cen = new Cenovnik();
			cen.setCena(cenovnikDTO.getCena());
			cen.setKlinika(kl);
			cen.setTipPregleda(tipPregleda);
			cenovnikService.save(cen);
		}
		
		
		
		return new ResponseEntity<>("Sve ok", HttpStatus.OK);

	}
	
	
	
	@PostMapping(value="/pretragaTipovaPregleda",consumes ="application/json")
	public ResponseEntity<List<TipPregledaDTO>> pretragaSala(@RequestBody TipPregledaDTO tpDTO){
			List<TipPregleda> tipovi = tipPregledaService.findAll();
			
			List<TipPregledaDTO> nadjeniPregledi = new ArrayList<>();
			for(TipPregleda t : tipovi) {
				boolean flag=true;
				
				if(!tpDTO.getNaziv().equals("")) {
					if(!t.getNaziv().contains(tpDTO.getNaziv())) {
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
					nadjeniPregledi.add(new TipPregledaDTO(t));
			}
			}
			return new ResponseEntity<>(nadjeniPregledi, HttpStatus.OK);
		
	}
	

}
