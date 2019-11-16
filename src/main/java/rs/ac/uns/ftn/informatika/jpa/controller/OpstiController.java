package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PorukaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.service.AdministratorKlinikeService;
import rs.ac.uns.ftn.informatika.jpa.service.AministratorKlinickogCentraService;
import rs.ac.uns.ftn.informatika.jpa.service.LekarService;
import rs.ac.uns.ftn.informatika.jpa.service.MedicinskaSestraService;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;


@RestController
@RequestMapping(value = "api")
public class OpstiController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private AministratorKlinickogCentraService administratorKlinickogCentraService;
	
	@Autowired
	private AdministratorKlinikeService administratorKlinikeService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private MedicinskaSestraService medicinskaSestraService;
	
	@PostMapping(value = "/savePacijent",consumes = "application/json")
	public ResponseEntity<PacijentDTO> savePacijent(@RequestBody PacijentDTO pacijentDTO) {
		
		if(pacijentDTO.getPassword().length() < 6) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
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
	
	@PostMapping(value = "/logIn",consumes = "application/json")
	public ResponseEntity<Void> logIn(@RequestBody PacijentDTO userInfo, @Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		List<Pacijent> sviPacijenti = pacijentService.findAll();
		for(Pacijent p : sviPacijenti) {
			if(p.getUsername().equals(userInfo.getUsername())) {
				if(p.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", p.getId());
					session.setAttribute("uloga", p.getUloga());
					return new ResponseEntity<Void>(HttpStatus.OK);				
				}
			}
		}
		
		List<AdministratorKlinickogCentra> sviAdministratoriKlinickogCentra = administratorKlinickogCentraService.findAll();
		for(AdministratorKlinickogCentra akc : sviAdministratoriKlinickogCentra) {
			if(akc.getUsername().equals(userInfo.getUsername())) {
				if(akc.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", akc.getId());
					session.setAttribute("uloga", akc.getUloga());
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		}
		
		List<AdministratorKlinike> sviAdministratoriKlinika = administratorKlinikeService.findAll();
		for(AdministratorKlinike ak : sviAdministratoriKlinika) {
			if(ak.getUsername().equals(userInfo.getUsername())) {
				if(ak.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", ak.getId());
					session.setAttribute("uloga", ak.getUloga());
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		}
		
		List<Lekar> sviLekari = lekarService.findAll();
		for(Lekar l : sviLekari) {
			if(l.getUsername().equals(userInfo.getUsername())) {
				if(l.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", l.getId());
					session.setAttribute("uloga", l.getUloga());
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		}
		
		List<MedicinskaSestra> sveMedicinskeSestre = medicinskaSestraService.findAll();
		for(MedicinskaSestra ms : sveMedicinskeSestre) {
			if(ms.getUsername().equals(userInfo.getUsername())) {
				if(ms.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", ms.getId());
					session.setAttribute("uloga", ms.getUloga());
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping(value = "/whoIsLoggedIn",produces = "application/json")
	public UserDTO whoIsLoggedIn(@Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String uloga = (String) session.getAttribute("uloga");
		
		if(id == null) {
			return null;
		}
		
		UserDTO userDTO = new UserDTO(id, uloga);
		
		return userDTO;
	}
	
	@PostMapping(value = "/logOut")
	public ResponseEntity<Void> logOut(@Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value="/prihvatiPacijenta/{id}")
	public ResponseEntity<PacijentDTO> updatePacijent(@PathVariable Long id) {

		Pacijent pacijent = pacijentService.findOne(id);

		if (pacijent == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		pacijent.setAktiviranNalog(true);

		pacijent = pacijentService.save(pacijent);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
