package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarOdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Odsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.TipOdsustva;
import rs.ac.uns.ftn.informatika.jpa.repository.LekarOdsustvoRepository;

@Service
public class LekarOdsustvoService {
	
	@Autowired LekarOdsustvoRepository lorepository;
	
	@Autowired LekarService lekarService;
	
	@Autowired EmailService emailService;
	
	@Autowired AdministratorKlinikeService adminKlinikeService;
	
	@Autowired OdsustvoService odsustvoService;
	
	public LekarOdsustvo findOne(Long id) {
		return lorepository.findById(id).orElseGet(null);
	}

	public List<LekarOdsustvo> findAll() {
		return lorepository.findAll();
	}
	
	public LekarOdsustvo save(LekarOdsustvo o) {
		return lorepository.save(o);
	}

	public void remove(Long id) {
		lorepository.deleteById(id);
	}
	
public String kreirajOdsustvo(LekarOdsustvoDTO ods) {
		
		//pronadji med sestru koja je poslala zahtev
//		MedicinskaSestra ms = medSestraService.findOne(ods.getMedSestraId());
		Lekar lek = lekarService.findOne(ods.getLekarId());
		
		LocalDateTime datePocetak; 
		LocalDateTime dateKraj;
		try 
		{
			
			//System.out.println("##########" + ods.getPocetak() + "##############");
			
			//formatiranje vremena
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			        .appendPattern("dd-MM-yyyy[ HH:mm:ss]")
			        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			        .toFormatter();
			
			//parsiranje pocetka i kraja odsustva
			datePocetak = LocalDateTime.parse(ods.getPocetak(),formatter);
			dateKraj = LocalDateTime.parse(ods.getKraj(),formatter);
			
			//ako je pocetak posle kraja
			if(datePocetak.compareTo(dateKraj)>0)
			{
				return "Datum pocetka ne moze biti pre datuma kraja";
			}
			
			//uzimamo sva odsustva medicinske sestre
			Set<LekarOdsustvo> odsustva = lek.getListaOdsustava();
			
			//idemo kroz sva odsustva
			for (LekarOdsustvo odsustvo : odsustva) {
				LocalDateTime p2 = odsustvo.getPocetak();
				LocalDateTime k2 = odsustvo.getKraj();
				//gledamo da li postoji preklapanje
				Boolean overlap = datePocetak.isBefore(k2) && p2.isBefore(dateKraj);
				
				//ako postoji vrati poruku
				if(overlap)
				{
					return "Ne sme biti preklapanja izmedju odsustava. Proverite vaš kalendar";
				}
			}
			
		} 
		catch (Exception e) 
		{
			return "Datum nije uspesno konvertovan";
		}  
		
		//popuni odsustvo
		TipOdsustva tods = TipOdsustva.valueOf(ods.getTip());
		LekarOdsustvo odsustvo = new LekarOdsustvo();
		odsustvo.setPocetak(datePocetak);
		odsustvo.setKraj(dateKraj);
		odsustvo.setTip(tods);
		odsustvo.setOdobreno(true); //zasad uvek true dok se ne odradi prihvatanje
		odsustvo.setLekar(lek);
		
		this.save(odsustvo);
		
		//dodaj odsustvo med sestri
//		MedicinskaSestra med = odsustvo.getMedSestra();
//		Set<Odsustvo> odsustva = med.getListaOdsutstava();
//		odsustva.add(odsustvo);
//		medSestraService.save(med);
		
		Lekar lekar = odsustvo.getLekar();
		Set<LekarOdsustvo> odsustva = lekar.getListaOdsustava();
		odsustva.add(odsustvo);
		lekarService.save(lekar);
		
		return "Uspesno poslat zahtev za odsustvo";
	}


public String odobriOdsustvo(Long id) {
		
		LekarOdsustvo od = this.findOne(id);
		od.setOdobreno(true);
		this.save(od);
		
		String datumivremeP[]=od.getPocetak().toString().split("T");
		String datumivremeK[]=od.getKraj().toString().split("T");
		EmailDTO emailDTO2 = new EmailDTO(1, "Obavestenje o odsustvu.",
				"Odsustvo: <br>"
				+"Od: "+ datumivremeP[0] + " "+datumivremeP[1]+"<br>"
				+"Do: "+ datumivremeK[0] + " "+datumivremeK[1]+"<br>"
				+"je odobreno", "");

		
		try 
		{		
		emailService.sendNotificaitionAsync(emailDTO2);
		}
		catch( Exception e )
		{
		//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		System.out.println("### Greska prilikom slanja mail-a! ###");
		}
		
		
		
		return "Odobreno";
	}

public String odbijOdsustvo(Long id,String str) {
	LekarOdsustvo od = this.findOne(id);
	od.setOdobreno(false);
	this.save(od);
	String []text=str.split(":");
	String tekst=text[1].trim();
	tekst=tekst.substring(1,tekst.length()-2);
	
	
	String datumivremeP[]=od.getPocetak().toString().split("T");
	String datumivremeK[]=od.getKraj().toString().split("T");
	EmailDTO emailDTO2 = new EmailDTO(1, "Obavestenje o odsustvu.",
			"Odsustvo: <br>"
			+"Od: "+ datumivremeP[0] + " "+datumivremeP[1]+"<br>"
			+"Do: "+ datumivremeK[0] + " "+datumivremeK[1]+"<br>"
			+"je odbijeno"+"<br>"
			+"Razlog :"+"<br>"
			+tekst, "");

	
	try 
	{		
	emailService.sendNotificaitionAsync(emailDTO2);
	}
	catch( Exception e )
	{
	//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
	System.out.println("### Greska prilikom slanja mail-a! ###");
	}
	
	
	
	return "Odbijeno";
}

public List<LekarOdsustvoDTO> vratiZahteveZaOdsustva(Long id){
	AdministratorKlinike admin = adminKlinikeService.findOne(id);
	List<LekarOdsustvo> odsLekara = this.findAll();
	List<LekarOdsustvo> pomocnaLista = new ArrayList<LekarOdsustvo>();
	List<LekarOdsustvoDTO> rez = new ArrayList<LekarOdsustvoDTO>();
	
	for(LekarOdsustvo ods : odsLekara) {
		if(ods.getLekar().getKlinika().getId() == admin.getKlinika().getId()) {
			if(ods.getOdobreno()==null) {
				pomocnaLista.add(ods);
			}
		}
		
		
	}
	
	for(LekarOdsustvo o : pomocnaLista) {
		rez.add(new LekarOdsustvoDTO(o));
	}
	
	return rez;
	
	
}
	

}
