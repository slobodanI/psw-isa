package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PredefPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.PromenaPregledaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StariPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Recept;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostSala;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.PregledRepository;

@Service
public class PregledService {
	

	
	@Autowired
	private PregledRepository pregledRepository;
	
	@Autowired
	private TipPregledaService tipPregledaService;
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private ReceptService receptService;
	
	@Autowired
	private ZdravstveniKartonService zkService;
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private ZauzetostLekaraService zauzetostLekaraService;
	
	@Autowired
	private ZauzetostSalaService zauzetostSalaService;
	
	@Autowired
	private TipPregledaService tipService;
	
	public Pregled findOne(Long id) {
		return pregledRepository.findById(id).orElseGet(null);
	}

	public List<Pregled> findAll() {
		return pregledRepository.findAll();
	}
	
	public Page<Pregled> findAll(Pageable page) {
		return pregledRepository.findAll(page);
	}

	public Pregled save(Pregled pregled) {
		return pregledRepository.save(pregled);
	}

	public void remove(Long id) {
		pregledRepository.deleteById(id);
	}

	//metoda popunjava dijagnozu i informacije jednog pregleda
	public void popuniPregled(ZavrsiPregledDTO preg) 
	{
		//iscitaj i popuni pregled
		Pregled pregled = this.findOne(preg.getId_pregleda());
		pregled.setInformacije(preg.getInfo());
		pregled.setDijagnoza(dijagnozaService.findOne(preg.getId_dijagnoze()));
		pregled.setObavljen(true);
		this.save(pregled);
		
		//sifre lekova koje treba dodati
		ArrayList<Long> lista = preg.getId_leka_lista();
		
		for (Long id : lista) 
		{
			Lek lek = lekService.findOne(id);
			Pacijent pac = pregled.getPacijent();
			
			String sifra = lek.getSifra();
			String naziv = lek.getNaziv();
			Long lbo = pac.getLbo();
			String ime = pac.getIme();
			String prezime = pac.getPrezime();
			
			//popuni recept
			Recept recept = new Recept();
			recept.setSifra_Leka(sifra);
			recept.setNazivLeka(naziv);
			recept.setLbo(lbo);
			recept.setImePacijenta(ime);
			recept.setPrezimePacijenta(prezime);
			recept.setOveren(false);
			recept.setMedicinskaSestra(null);
			recept.setPregled(pregled);
			
			receptService.save(recept);
		}
		
		//dodaj pregled u karton pacijenta
		Pacijent pac = pregled.getPacijent();
		ZdravstveniKarton zk = pac.getZdravstveniKarton();
		//System.out.println("############" + zk.getId() + "#############");
		Set<Pregled> pregledi = zk.getListaPregleda();
		pregledi.add(pregled);
		zkService.save(zk);
		
		/*
		//test citanje dijagnoza pacijenta
		Pacijent pac1 = pregled.getPacijent();
		ZdravstveniKarton zk1 = pac1.getZdravstveniKarton();
		Set<Pregled> pregledi1 = zk1.getListaPregleda();
		
		for (Pregled pregled2 : pregledi1) {
			System.out.println("##" + pregled2.getId().toString() + "-" + pregled2.getDijagnoza().getNaziv() + "##");
		}
		*/
	}
	
	public List<PregledDTOStudent1> getPredefinisanePreglede(Long klinikaID) {
		
		List<Pregled> sviPregledi = findAll();
		
		List<PregledDTOStudent1> predefinisatiPregledi = new ArrayList<PregledDTOStudent1>();
		for(Pregled pregled : sviPregledi) {
			if(pregled.getSala().getKlinika().getId().equals(klinikaID)){ // pregled u toj klinici
				if(pregled.getPacijent() == null) { // ovo znaci da je predefinisan pregled
					PregledDTOStudent1 predefPregledDTO = new PregledDTOStudent1(pregled);
					predefinisatiPregledi.add(predefPregledDTO);
				}
			}
		}
		
		return predefinisatiPregledi;
	}
	
	
	public Boolean zakaziPredefPregled(Long pregledID, Long pacijentID) {
		
		Pacijent pacijent = pacijentService.findOne(pacijentID);
		Pregled pregled = findOne(pregledID);
		
		//ako postoje u bazi
		if(pacijent == null || pregled == null) {
			return false;
		}
		
		//poslat je ID od pregleda koji nije predefinisan
		if(pregled.getPacijent() != null) {
			return false;
		}
		
		pregled.setPacijent(pacijent);
		pregled.setZdravstveniKarton(pacijent.getZdravstveniKarton());
		save(pregled);
		
		EmailDTO emailDTO = new EmailDTO(pacijent.getId().intValue(), "Uspesno zakazan pregled", "Uspesno ste zakazali pregled preko profila klinike", "");

		try 
		{
			emailService.sendNotificaitionAsync(emailDTO);
		}
		catch( Exception e )
		{
//			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
		}
		
		return true;
	}

	//metoda vraca obavljene preglede doktora sa datim ID-jem
	public List<StariPregledDTO> vratiStarePreglede(Long lekarID) 
	{
		Lekar lekar = lekarService.findOne(lekarID);
		Set<Pregled> pregledi = lekar.getListaZakazanihPregleda();
		
		List<StariPregledDTO> rezultat = new ArrayList<StariPregledDTO>();
		
		for (Pregled p : pregledi) 
		{
			if(p.isObavljen()==true)
			{
				rezultat.add(new StariPregledDTO(p));
			}
		}
		
		return rezultat;
	}
	
	//vraca pregled koji ce biti menjan
	public PromenaPregledaDTO vratiPregled(Long id) 
	{
		Pregled preg = this.findOne(id);
		PromenaPregledaDTO promena = new PromenaPregledaDTO(preg);
		
		return promena;
	}
	
	
	//promena pregleda
	public String promeniPregled(PromenaPregledaDTO promena) 
	{
		if(promena.getInformacije().isEmpty())
		{
			return "Morate uneti informacije";
		}
		
		Pregled pregled = this.findOne(promena.getId());
		pregled.setInformacije(promena.getInformacije());
		Dijagnoza dij = dijagnozaService.findOne(promena.getDijagnozaId());
		pregled.setDijagnoza(dij);
		TipPregleda tip = tipService.findOne(promena.getTipId());
		pregled.setTipPregleda(tip);
		this.save(pregled);
		
		return "Stari pregled uspešno promenjen";
	}
	
	public String dodajPredefinisaniPregled(PredefPregledDTO pregled) {
		boolean lekarVreme = true;
		
		Lekar lekar1 = lekarService.findOne(pregled.getLekar());
		Set<ZauzetostLekara> zauzetost = lekar1.getListaZauzetostiLekara();
		Set<LekarOdsustvo> odsustvo = lekar1.getListaOdsustava();
		
		//provere da li je pregled u terminu u kojem je odabrani lekar zauzet
		if(zauzetost != null) {
		for(ZauzetostLekara z : zauzetost) {
			System.out.println(pregled.getDatumPregledaOd());
			System.out.println(z.getKraj());
			if(!pregled.getDatumPregledaOd().isAfter(z.getKraj()) && !pregled.getDatumPregledaOd().isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
			if(!pregled.getDatumPregledaDo().isAfter(z.getKraj()) && !pregled.getDatumPregledaDo().isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
		}
		}
		for(LekarOdsustvo o : odsustvo) {
			if(!pregled.getDatumPregledaDo().isAfter(o.getKraj()) && !pregled.getDatumPregledaDo().isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
			if(!pregled.getDatumPregledaOd().isAfter(o.getKraj()) && !pregled.getDatumPregledaOd().isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
		}
		if(pregled.getDatumPregledaOd().getHour()< lekar1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(pregled.getDatumPregledaOd().getHour() > lekar1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(pregled.getDatumPregledaDo().getHour() < lekar1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(pregled.getDatumPregledaDo().getHour() > lekar1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(pregled.getDatumPregledaOd().isBefore(LocalDateTime.now())) {
			lekarVreme=false;
		}
		if(!pregled.getDatumPregledaDo().isAfter(pregled.getDatumPregledaOd())) {
			return "Ne moze kraj pregleda biti pre pocetka pregleda";
		}
		if(lekarVreme == false) {
			return "Odabrani lekar je zauzet u odabranom terminu.";
		}
		
		boolean salaVreme = true;
		Sala sala = salaService.findOne(pregled.getSala());
		Set<ZauzetostSala> zauzetostSala = sala.getListaZauzetostiSala();
		
		for(ZauzetostSala z : zauzetostSala) {
			if(!pregled.getDatumPregledaOd().isAfter(z.getKraj()) && !pregled.getDatumPregledaOd().isBefore(z.getPocetak())) {
				salaVreme = false;
			}
			if(!pregled.getDatumPregledaDo().isAfter(z.getKraj()) && !pregled.getDatumPregledaDo().isBefore(z.getPocetak())) {
				salaVreme=false;
			}
		}
		if(salaVreme == false) {
			return "Odabrana sala je zauzeta u odabranom terminu.";
		}
		
		Pregled noviPregled = new Pregled();
		noviPregled.setDatumPregledaOd(pregled.getDatumPregledaOd());
		noviPregled.setDatumPregledaDo(pregled.getDatumPregledaDo());
		noviPregled.setTipPregleda(tipPregledaService.findOne(pregled.getTipPregleda()));
		noviPregled.setLekar(lekar1);
		noviPregled.setSala(sala);
		noviPregled.setCena(pregled.getCena());
		noviPregled.setPopust(pregled.getPopust());
		noviPregled.setDijagnoza(null);
		noviPregled.setInformacije("");
		noviPregled.setObavljen(false);
		noviPregled.setPacijent(null);
		noviPregled.setZdravstveniKarton(null);
		
		//dodajemo zauzetost u tabelu zauzetosti
		ZauzetostLekara nova= new ZauzetostLekara();
		nova.setPocetak(pregled.getDatumPregledaOd());
		nova.setKraj(pregled.getDatumPregledaDo());
		nova.setLekar(lekar1);
		zauzetost.add(nova);
		zauzetostLekaraService.save(nova);
		
		ZauzetostSala novaz = new ZauzetostSala();
		novaz.setPocetak(pregled.getDatumPregledaOd());
		novaz.setKraj(pregled.getDatumPregledaDo());
		novaz.setSala(sala);
		zauzetostSala.add(novaz);
		zauzetostSalaService.save(novaz);
		
		this.save(noviPregled);

		return null;
		
		
	}
	
	
	
}
