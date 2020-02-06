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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PredefPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent2;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledKalendarDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledOdlukaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledZakazivanjeDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PromenaPregledaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StariPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.service.EmailService;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;

@RestController
@RequestMapping(value = "pregledi")
public class PregledController 
{
	@Autowired
	private PregledService pregledSevice;
	
	@Autowired
	private EmailService emailService;
	
	//metoda popunjava informacije jednog pregleda
	@PostMapping(value="/zavrsiPregled", consumes = "application/json")
	public ResponseEntity<ZavrsiPregledDTO> zavrsiPregled(@RequestBody ZavrsiPregledDTO preg)  
	{
		
		pregledSevice.popuniPregled(preg);
		
		return new ResponseEntity<ZavrsiPregledDTO>(HttpStatus.OK);
	}

	@GetMapping(value = "/predefPregledi/{klinikaID}")
	public ResponseEntity<List<PregledDTOStudent1>> dobaviPredefPreglede(@PathVariable Long klinikaID){
		
		List<PregledDTOStudent1> predefPregledi = pregledSevice.getPredefinisanePreglede(klinikaID);
		
		return new ResponseEntity<>(predefPregledi, HttpStatus.OK);
	}
	
	@PutMapping(value = "/zakaziPredefPregled/{pregledID}/pacijent/{pacijentID}")
	public ResponseEntity<Void> zakaziPredefPregled(@PathVariable Long pregledID, @PathVariable Long pacijentID){
		
		if(pregledSevice.zakaziPredefPregled(pregledID, pacijentID)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//metoda vraca obavljene preglede doktora sa datim ID-jem
	@GetMapping(value = "/vratiStarePreglede/{lekarID}")
	public ResponseEntity<List<StariPregledDTO>> dobaviStarePreglede(@PathVariable Long lekarID){
		
		List<StariPregledDTO> pregledi = pregledSevice.vratiStarePreglede(lekarID);
		
		return new ResponseEntity<>(pregledi, HttpStatus.OK);
	}
	
	//vraca pregled koji ce biti menjan
	@GetMapping(value = "/vratiPregled/{id}")
	public ResponseEntity<PromenaPregledaDTO> vratiPregled(@PathVariable Long id)
	{
			
		PromenaPregledaDTO pregled = pregledSevice.vratiPregled(id);
			
		return new ResponseEntity<>(pregled, HttpStatus.OK);
	}
	
	//menjanje pregleda
	@PostMapping(value = "/promeniPregled")
	public ResponseEntity<String> promeniPregled(@RequestBody PromenaPregledaDTO promena)
	{
		String poruka = pregledSevice.promeniPregled(promena);
		
		return new ResponseEntity<>(poruka, HttpStatus.OK);
	}
	

	//vraca pregled koji ce biti prikazan kad se odabere sa kalendara
	@GetMapping(value = "/vratiPregledKalendar/{id}")
	public ResponseEntity<PregledKalendarDTO> vratiPregledKalendar(@PathVariable Long id)
	{	
		PregledKalendarDTO pregled = pregledSevice.vratiPregledKalendar(id);
				
		return new ResponseEntity<>(pregled, HttpStatus.OK);
	}

	@PostMapping(value = "/dodajPredefinisaniPregled")
	public ResponseEntity<String> savePredefPregled(@RequestBody PredefPregledDTO pregledDTO){
		
		String pregled = pregledSevice.dodajPredefinisaniPregled(pregledDTO);
		
		if(pregled == "Ne moze kraj pregleda biti pre pocetka pregleda") {
			return new ResponseEntity<>("Ne moze kraj pregleda biti pre pocetka pregleda",HttpStatus.CONFLICT);
		}
		else if(pregled == "Odabrani lekar je zauzet u odabranom terminu.") {
			return new ResponseEntity<>("Odabrani lekar je zauzet u odabranom terminu.",HttpStatus.CONFLICT);
		}
		else if(pregled == "Odabrana sala je zauzeta u odabranom terminu.") {
			return new ResponseEntity<>("Odabrana sala je zauzeta u odabranom terminu.",HttpStatus.CONFLICT);
		}else {
		
		return new ResponseEntity<>("Uspesno dodat pregled",HttpStatus.OK);
		}
		
	}
	
	@PostMapping(value = "/dodajNoviPregled")
	public ResponseEntity<String> saveNoviPregled(@RequestBody PregledDTOStudent2 pregledDTO){
		
		String pregled = pregledSevice.dodajNoviPregled(pregledDTO);
		if(pregled == "Ne moze kraj pregleda biti pre pocetka pregleda") {
			return new ResponseEntity<>("Ne moze kraj pregleda biti pre pocetka pregleda",HttpStatus.CONFLICT);
		}
		else if(pregled == "Odabrani lekar je zauzet u odabranom terminu.") {
			return new ResponseEntity<>("Odabrani lekar je zauzet u odabranom terminu.",HttpStatus.CONFLICT);
		}else {
			
			return new ResponseEntity<>("Uspesno dodat pregled",HttpStatus.OK);
			}
		
		
	}
	
	//zakazivanje pregleda od strane pacijenta
	//izabrao je tip pregleda, kliniku, lekara, termin // lekar sadrzi kliniku i tip pregleda
	@PostMapping(value = "/zakaziPregled")
	public ResponseEntity<Void> zakaziPregled(@RequestBody PregledZakazivanjeDTO pregledZakazivanjeDTO, @Context HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		// IZ NEKOG RAZLOGA "Pacijent" != "Pacijent", ... nisam koristi .equals() ...
		Long pacijentID = (long) 0;
		String uloga = "";
		
//		System.out.println("***ISPIS PRISTIGLIH PODATAKA");
//		System.out.println("lekarID : " + pregledZakazivanjeDTO.getLekarID());
//		System.out.println("termin:" + pregledZakazivanjeDTO.getTermin());
//		System.out.println("Sesija: " + session.toString());
//		System.out.println("idPacijenta: " + session.getAttribute("id"));
//		System.out.println("uloga: " + session.getAttribute("uloga"));
		
//		***ISPIS PRISTIGLIH PODATAKA
//		lekarID : 1
//		termin:2020-02-28T08:00
//		Sesija: org.apache.catalina.session.StandardSessionFacade@99b8788
//		idPacijenta: 1
//		uloga: Pacijent
		
//		if(session == null) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
//		}
				
		pacijentID = (Long) session.getAttribute("id");
		uloga = (String) session.getAttribute("uloga");
		
//		if(uloga != null) { // zbog testova, kako da posaljem sesiju tj. request ???
//			if(uloga.equals("Pacijent")) {
				if(pregledSevice.zakaziPregled(pregledZakazivanjeDTO.getLekarID(), pregledZakazivanjeDTO.getTermin(), pregledZakazivanjeDTO.getPacijentID())) {
					return new ResponseEntity<>(HttpStatus.OK);
				}
//			} 
//		}
		
						
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
	}
	//kada kliknem na link iz maila za potvrdu, dolazim ovde
	@PutMapping(value = "/potvrdaPregleda")
	public ResponseEntity<Void> potvrdiIliOdbiPregled(@RequestBody PregledOdlukaDTO pregledOdlukaDTO){
		
		if( pregledSevice.potvrdiIliOdbiPregled(pregledOdlukaDTO.getPregledID(), pregledOdlukaDTO.getOdluka()) ) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//za STUDENTA 2, kada bude slao mail pacijentu
	@GetMapping(value = "/testPotvrdePregleda/{idPregleda}")
	public ResponseEntity<Void> testPotvrdePregleda(@PathVariable Long idPregleda){
		
		//treba poslati idPregleda koji ima: obrisan=false i prihvacen=false
		//po defaultu su ove vrednosti obirsan=false i prihvacen=true
		//kada admin promeni termin ili lekara, treba da postavi prihvacen=false i da posalje mail
		EmailDTO emailDTO = new EmailDTO(1, "Odluka o pregledu.",
				"Ako želite da prihvatite pregled: ...info o pregledu... onda kliknite ovde: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=potvrdi, <br> a ako odbijate: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=odustani", "");

		
		try 
		{		
		emailService.sendNotificaitionAsync(emailDTO);
		}
		catch( Exception e )
		{
		//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		System.out.println("### Greska prilikom slanja mail-a! ###");
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/zakazaniPregleda/{pacijentID}")
	public ResponseEntity<List<PregledDTOStudent1>> dobaviZakazanePreglede(@PathVariable Long pacijentID){
		
		List<PregledDTOStudent1> zakazaniPregledi = pregledSevice.getZakazanePreglede(pacijentID);
		
		return new ResponseEntity<>(zakazaniPregledi, HttpStatus.OK);
	}
	
	@PutMapping(value = "/otkaziPregled/{pregledID}")
	public ResponseEntity<Void> otkaziPregled(@PathVariable Long pregledID){
		
		if(pregledSevice.otkaziZakazanPregled(pregledID)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
