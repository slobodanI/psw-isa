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
import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent2;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledKalendarDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PregledOtkaziDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.PromenaPregledaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.StariPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Cenovnik;
import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
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
	private AdministratorKlinikeService adminKlinikeService;
	
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
	
	@Autowired
	private CenovnikService cenovnikService;
	
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
		LocalDateTime sada = LocalDateTime.now();
		
		List<PregledDTOStudent1> predefinisatiPregledi = new ArrayList<PregledDTOStudent1>();
		for(Pregled pregled : sviPregledi) {
			if(pregled.getPacijent() == null){ // ovo znaci da je predefinisan pregled
				if(pregled.getSala().getKlinika().getId().equals(klinikaID)) { // pregled u toj klinici
					if(pregled.getDatumPregledaOd().isAfter(sada)) {
						PregledDTOStudent1 predefPregledDTO = new PregledDTOStudent1(pregled);
						predefinisatiPregledi.add(predefPregledDTO);
					}					
				}
			}
		}
		
		return predefinisatiPregledi;
	}
	
	
	public Boolean zakaziPredefPregled(Long pregledID, Long pacijentID) {
		
		if(pregledID != null) {
			if(pregledID <= 0) {
				return false;
			}
		} else {
			return false;
		}
		
		if(pacijentID != null) {
			if(pacijentID <= 0) {
				return false;
			}
		} else {
			return false;
		}
		
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
		pregled.setPrihvacen(true);
		save(pregled);
		
		EmailDTO emailDTO = new EmailDTO(pacijent.getId().intValue(), "Uspesno zakazan pregled: ", "Uspesno ste zakazali pregled preko profila klinike <br> Termin: "+ pregled.getDatumPregledaOd()+"", "");

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


	//vraca pregled koji ce biti prikazan kad se odabere sa kalendara
	public PregledKalendarDTO vratiPregledKalendar(Long id) {
		Pregled pregled = this.findOne(id);
		return new PregledKalendarDTO(pregled);
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
		noviPregled.setObavljen(false);
		noviPregled.setPrihvacen(false);
		noviPregled.setObrisan(false);
		
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
	
public String dodajNoviPregled(PregledDTOStudent2 pregled) {
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
		
		Pacijent pacijent = pacijentService.findOne(pregled.getPacijent());
		
		Pregled noviPregled = new Pregled();
		noviPregled.setDatumPregledaOd(pregled.getDatumPregledaOd());
		noviPregled.setDatumPregledaDo(pregled.getDatumPregledaDo());
		noviPregled.setLekar(lekar1);
		noviPregled.setTipPregleda(lekar1.getTipPregleda());
		noviPregled.setPacijent(pacijent);
		noviPregled.setZdravstveniKarton(pacijent.getZdravstveniKarton());
		noviPregled.setDijagnoza(null);
		noviPregled.setInformacije("");
		noviPregled.setObavljen(false);
		noviPregled.setCena(1000);
		noviPregled.setPopust(0);
		noviPregled.setObavljen(false);
		noviPregled.setPrihvacen(true);
		noviPregled.setObrisan(false);
		
		//dodaje u zauzetost Lekara
		ZauzetostLekara nova= new ZauzetostLekara();
		nova.setPocetak(pregled.getDatumPregledaOd());
		nova.setKraj(pregled.getDatumPregledaDo());
		nova.setLekar(lekar1);
		zauzetost.add(nova);
		zauzetostLekaraService.save(nova);
		
		this.save(noviPregled);

		return null;
		
	}


public List<PregledDTOStudent2> vratiZahteveZaPregled(Long idAdmina){
	AdministratorKlinike admin = adminKlinikeService.findOne(idAdmina);
	List<Pregled> sviPregledi = this.findAll();
	List<Pregled> pomocnaLista = new ArrayList<>();
	List<PregledDTOStudent2> rezultat = new ArrayList<>();
	for(Pregled p : sviPregledi) {
		if(p.getLekar().getKlinika().getId() == admin.getKlinika().getId()) {
			if(p.isObavljen()==false) {
				if(p.isPrihvacen()==false) {
					if(p.isObrisan()==false) {
					pomocnaLista.add(p);
					}
				}
			}
		}
	}
	
	for(Pregled pom : pomocnaLista) {
		if(pom.getSala()==null) {
			rezultat.add(new PregledDTOStudent2(pom));
		}
	}
	
	return rezultat;	
}
	

	//zakazivanje pregleda od strane pacijenta
	//izabrao je tip pregleda, kliniku, lekara, termin // lekar sadrzi kliniku i tip pregleda
	public boolean zakaziPregled(Long lekarID, LocalDateTime termin, Long pacijentID) {
		
		//provera prosledjenih parametara
		if(lekarID != null) {
			if(lekarID <= 0) {
				System.out.println("-lekar id je manje od nula!" + lekarID);
				return false;
			}
		} else {
			System.out.println("-lekar id null");
			return false;
		}
		
		if(pacijentID != null) {
			if(pacijentID <= 0) {
				System.out.println("-pacijent id je manje od nula!" + pacijentID);
				return false;
			}
		} else {
			System.out.println("-lekar id null");
			return false;
		}
		
		LocalDateTime sada = LocalDateTime.now();
		
		if(termin != null) {
			if(termin.isBefore(sada)) {
				System.out.println("-termin je pre sada");
				return false;
			}
		} else {
			System.out.println("-termin je null");
			return false;
		}
		
		Lekar lekar = lekarService.findOne(lekarID);
		Pacijent pacijent = pacijentService.findOne(pacijentID);
		
		if(lekar == null || pacijent == null) {
			System.out.println("lekar ili pacijent je null: " + lekar );
			System.out.println("lekar ili pacijent je null: " + pacijent );
			return false;
		}
		
		//ako je pacijent jos jednom kliknuo na dugme za potvrdu pregleda
		//ili jednostavno da nema prelklapanja
		for(Pregled p : this.findAll()) {
			if(p.isObrisan() == false) { // ako je neki pregled koji je bio zakazan, obrisan iz nekog razloga
				if(termin.isBefore(p.getDatumPregledaDo()) && termin.plusHours(1).isAfter(p.getDatumPregledaOd())) {
					return false;
				}
			}			
		}
		
		//ako je sve uredu, kreiram pregled i sačuvam ga
		Pregled noviPregled = new Pregled();
		noviPregled.setSala(null);
		noviPregled.setDatumPregledaOd(termin);
		noviPregled.setDatumPregledaDo(termin.plusHours(1));
		noviPregled.setLekar(lekar);
		noviPregled.setTipPregleda(lekar.getTipPregleda());
		noviPregled.setPacijent(pacijent);
		noviPregled.setZdravstveniKarton(pacijent.getZdravstveniKarton());
		noviPregled.setDijagnoza(null);
		noviPregled.setInformacije("");
		noviPregled.setCena(1000/*lekar.getTipPregleda().getCena()*/); // ovo treba biti cena tipa pregleda
		noviPregled.setPopust(0);
		noviPregled.setObavljen(false);
		noviPregled.setPrihvacen(false);
		noviPregled.setObrisan(false);
		
		List<Cenovnik> cene = cenovnikService.findAll();
		for(Cenovnik cena : cene) {
			if(cena.getKlinika().getId().equals(lekar.getKlinika().getId()) && cena.getTipPregleda().equals(lekar.getTipPregleda())) {
				noviPregled.setCena(cena.getCena().intValue()); 
			}
		}
		
		this.save(noviPregled);
		
		//dodavanje u listu zauzetosti lekara
		ZauzetostLekara nova= new ZauzetostLekara();
		nova.setPocetak(noviPregled.getDatumPregledaOd());
		nova.setKraj(noviPregled.getDatumPregledaDo());
		nova.setLekar(lekar);
		//zauzetost.add(nova);
		zauzetostLekaraService.save(nova);
					
		EmailDTO emailDTO = new EmailDTO(pacijent.getId().intValue(), "Pristigao je novi zahtev za pregled.",
											"Pacijent je uspešno zakazao pregled. Treba da dodelite salu za pregled", "");
	

		try 
		{
			emailService.sendNotificaitionAsync(emailDTO);
		}
		catch( Exception e )
		{
//			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
		}
		
		
//		System.out.println("**********ISPISIVANJE ZAUZETOSTI LEKARA***********");
//		System.out.println("JA SAM DODAO: " + termin);
//		System.out.println("A LEKAR SADRZI: " );
//		for(ZauzetostLekara zau: lekar.getListaZauzetostiLekara()) {
//			System.out.println("-termin OD: " + zau.getPocetak());
//		}
		
		return true;
	}
	
	public boolean potvrdiIliOdbiPregled(Long pregledID, String odluka) {
		
		if(pregledID != null) {
			if(pregledID <= 0) {
				return false;
			}
		} else {
			return false;
		}
		
		if(odluka != null) {
			if(odluka.equals("potvrdi") == false && odluka.equals("odustani") == false) {
				return false;
			}
		} else {
			return false;
		}
		
		Pregled pregled = findOne(pregledID);
		boolean flag = false;
		Long lekarID = 0L;
		LocalDateTime termin = null;
		
		if(pregled == null) {
			return false;
		}
		
		//pacijent je vec rekao da PRIHVATA pregled
		if(pregled.isPrihvacen() == true) {
			return false;
		}
		
		//pacijent je vec rekao da ODBIJA pregled
		if(pregled.isObrisan() == true) {
			return false;
		}
		
		if(odluka.equals("potvrdi")) {
			pregled.setPrihvacen(true);
		}
		
		if(odluka.equals("odustani")) {
			pregled.setPrihvacen(false);
			pregled.setObrisan(true);
			flag = true;
			termin = pregled.getDatumPregledaOd();
			lekarID = pregled.getLekar().getId();
		}
		
		this.save(pregled);
		
		//ako je obrisan, treba ukloniti i zauzetost kod lekara
		if(flag == true) {
//			Lekar lekar = lekarService.findOne(lekarID);
//			
//			for(ZauzetostLekara zl : lekar.getListaZauzetostiLekara()) {
//				if(zl.getPocetak().isEqual(termin)) {
//					lekar.getListaZauzetostiLekara().remove(zl);
//				}
//			}
//			
//			lekarService.save(lekar);
			for(ZauzetostLekara zl : zauzetostLekaraService.findAll()) {
				if(zl.getPocetak().equals(termin) && zl.getLekar().getId().equals(lekarID)) {
					System.out.println("******Brise se: " +  zl.getPocetak() + ", KOD LEKAR ID: " + zl.getLekar().getId());
					zauzetostLekaraService.remove(zl.getId());
				}
			}
			
		}
		
		return true;
	}
	
	
	public List<PregledDTOStudent1> getZakazanePreglede(Long pacijentID) {
		
		List<Pregled> sviPregledi = findAll();
		LocalDateTime sada = LocalDateTime.now();
		
		List<PregledDTOStudent1> zakazaniPregledi = new ArrayList<PregledDTOStudent1>();
		for(Pregled pregled : sviPregledi) {
			if(pregled.isObavljen() == false && pregled.isObrisan() == false && pregled.isPrihvacen() == true) {
				if(pregled.getPacijent().getId().equals(pacijentID)) {	
					if(sada.isBefore(pregled.getDatumPregledaOd())) {
						PregledDTOStudent1 predefPregledDTO = new PregledDTOStudent1(pregled);
						zakazaniPregledi.add(predefPregledDTO);		
					}					
				}												
			}			
		}
		
		return zakazaniPregledi;
	}
	
	public List<PregledOtkaziDTO> getZakazanePregledeLekar(Long lekarID){
		List<Pregled> sviPregledi = findAll();
		LocalDateTime sada = LocalDateTime.now();
		
		List<PregledOtkaziDTO> zakazaniPregledi = new ArrayList<PregledOtkaziDTO>();
		for(Pregled pregled : sviPregledi) {
			if(pregled.isObavljen() == false && pregled.isObrisan() == false && pregled.isPrihvacen() == true) {
				if(pregled.getLekar().getId().equals(lekarID)) {	
					if(sada.isBefore(pregled.getDatumPregledaOd())) {
						PregledOtkaziDTO predefPregledDTO = new PregledOtkaziDTO(pregled);
						zakazaniPregledi.add(predefPregledDTO);		
					}					
				}												
			}			
		}
		
		return zakazaniPregledi;
		
		
	}
	
	
public Boolean otkaziZakazanPregled(Long pregledID) {
		
		if(pregledID != null) {
			if(pregledID <= 0) {
				return false;
			}
		} else {
			return false;
		}		
		
		Pregled pregled = findOne(pregledID);

		
		//ako postoje u bazi
		if(pregled == null) {
			return false;
		}
		
		LocalDateTime sutra = LocalDateTime.now().plusDays(1);
		Long lekarID = pregled.getLekar().getId();
		LocalDateTime termin = pregled.getDatumPregledaOd();
		
		//ako je vec obrisan...
		if(pregled.isObrisan() == true) {
			return false;
		}
		
		//otkazivanje mora biti najranije 24h pre pregleda
		if(sutra.isBefore(pregled.getDatumPregledaOd())) {
			pregled.setObrisan(true);
			pregled.setPrihvacen(false);
					
			//obrisi termin iz zauzetostiLekara
			for(ZauzetostLekara zl : zauzetostLekaraService.findAll()) {
				if(zl.getPocetak().equals(termin) && zl.getLekar().getId().equals(lekarID)) {
					System.out.println("****Brise se ZAKAZANI PREGLED, TERMIN : " + zl.getPocetak() );
					zauzetostLekaraService.remove(zl.getId());
					break;
				}
			}
//			Long lekarID = pregled.getLekar().getId();
//			Lekar lekar = lekarService.findOne(lekarID);			
			
//			for(ZauzetostLekara zl : lekar.getListaZauzetostiLekara()) {
//				if(zl.getPocetak().isEqual(pregled.getDatumPregledaOd()) && zl.getLekar().getId().equals(lekarID)) {
//					System.out.println("*******Brise se iz zauzetosti lekara: " + zl.getPocetak());
//					Set<ZauzetostLekara> zauzetost = lekar.getListaZauzetostiLekara();
//					zauzetost.remove(zl);
//					lekar.setListaZauzetostiLekara(zauzetost);
//					
//					lekarService.save(lekar);
//					break;
//				}
//			}
				
			
		} else {
			return false;
		}
			
		save(pregled);
				
		return true;
	}
	
public String upisiSalu(Long idPregleda,Long idSale) {
	
	if(idPregleda != null) {
		if(idPregleda <= 0) {
			return null;
		}
	} else {
		return null;
	}	
	if(idSale != null) {
		if(idSale <= 0) {
			return null;
		}
	} else {
		return null;
	}	
	
	Pregled pregled = this.findOne(idPregleda);
	Sala sala = salaService.findOne(idSale);
	if(pregled!=null) {
		if(sala!=null) {
			pregled.setSala(sala);
			String datumivreme[]=pregled.getDatumPregledaOd().toString().split("T");
			
			EmailDTO emailDTO = new EmailDTO(1, "Odluka o pregledu.",
					"Ako želite da prihvatite pregled: <br>"
					+"Vreme pregleda: "+ datumivreme[0] + " "+datumivreme[1]+"<br>"
					+"Sala pregleda: " +pregled.getSala().getNaziv()+"<br>"
					+"Lekar: "+pregled.getLekar().getPrezime()+" "+pregled.getLekar().getIme()+"<br>"
					+"Cena: "+pregled.getCena()+"<br>"
					+" onda kliknite ovde: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=potvrdi, <br> a ako odbijate: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=odustani", "");

			
			try 
			{		
			emailService.sendNotificaitionAsync(emailDTO);
			}
			catch( Exception e )
			{
			//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
			}
			
			
			EmailDTO emailDTO2 = new EmailDTO(1, "Obavestenje o pregledu.",
					"Dodat je novi pregled: <br>"
					+"Vreme pregleda: "+ datumivreme[0] + " "+datumivreme[1]+"<br>"
					+"Sala pregleda: " +pregled.getSala().getNaziv()+"<br>"
					+"Pacijent: "+pregled.getPacijent().getPrezime()+" "+pregled.getPacijent().getIme()+"<br>", "");

			
			try 
			{		
			emailService.sendNotificaitionAsync(emailDTO2);
			}
			catch( Exception e )
			{
			//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
			}
			
			
			
			 
		}
	}
	this.save(pregled);
	return "Sve ok";
	
	
}

public String prviSlobodanTermin(Long pregledId) {
	
	Pregled pregled = this.findOne(pregledId);
	LocalDateTime vreme = pregled.getDatumPregledaOd();
	LocalDateTime pocetnoVreme = pregled.getDatumPregledaOd();
	Long idPregleda = pregled.getId();
	Klinika kl = pregled.getLekar().getKlinika();
	Set<Sala>SaleKlinike = kl.getSale();
	Lekar l1 = pregled.getLekar();
	Set<ZauzetostLekara> originalniLekarZ =l1.getListaZauzetostiLekara();
	
	//brisem iz zauzetosti lekara
	for(ZauzetostLekara zlo : originalniLekarZ) {
		if(zlo.getPocetak()==vreme && zlo.getKraj()==vreme.plusHours(1)) {
			originalniLekarZ.remove(zlo);
		}
	}
	
//	l1.setListaZauzetostiLekara(originalniLekarZ);
//	lekarService.save(l1);
	
//	List<ZauzetostLekara> zl = zauzetostLekaraService.findAll();
	//zauzetostLekaraService.remove(id);
	
	
	TipPregleda tip1=pregled.getTipPregleda();
	Sala konacnaSala = new Sala();
	Lekar konacniLekar = new Lekar();
	LocalDateTime konacnoVreme = null;
	boolean nadjenaSala = false;
	boolean nadjenLekar = false;
	
	boolean oba = false;
	while(oba == false) {
		
		if(vreme.getHour()>15) {
			vreme=vreme.plusDays(1);
			vreme=vreme.withHour(0);
			continue;
		}else if(vreme.getHour()<8) {
			vreme=vreme.plusHours(1);
			continue;
		}
	
	for(Sala s : SaleKlinike) {
		boolean salaVreme = true;
	//	System.out.println(s.getId());
		Set<ZauzetostSala> zauzetostSala = s.getListaZauzetostiSala();
		if(zauzetostSala != null) {
		for(ZauzetostSala z : zauzetostSala) {
		//	System.out.println(z.getKraj()+"aAA KRAJ   SSS");
		//	System.out.println(vreme);
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				
				salaVreme = false;
			}
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				salaVreme=false;
			}
		}
		}
		if(salaVreme == true) {
			konacnaSala = s;
			nadjenaSala = true;
			konacnoVreme=vreme;
			
		}
		
		
	}
	System.out.println(konacnoVreme);
	
	//pretrazujem lekare sve
	
	for(Lekar l : kl.getLekari()) {
	//	System.out.println(l.getTipPregleda().getId());
	//	System.out.println(tip1.getId());
		if(l.getTipPregleda().equals(tip1)) {
	//	System.out.println("DALI OVDE DODJE UOPSTE");
		boolean lekarVreme = true;
		Set<ZauzetostLekara> zauzetost = l.getListaZauzetostiLekara();
		Set<LekarOdsustvo> odsustvo = l.getListaOdsustava();
		
		if(zauzetost != null) {
			for(ZauzetostLekara z : zauzetost) {
				
				//System.out.println(z.getKraj());
				if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
					lekarVreme = false;
				}
				if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
					lekarVreme = false;
				}
			}
			}
			for(LekarOdsustvo o : odsustvo) {
				if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
					lekarVreme = false;
				}
				if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
					lekarVreme = false;
				}
			}
			if(vreme.getHour()< l.getRadnoVremeOd()) {
				lekarVreme = false;
			}
			if(vreme.getHour() > l.getRadnoVremeDo()) {
				lekarVreme = false;
			}
			if(vreme.getHour() < l.getRadnoVremeOd()) {
				lekarVreme = false;
			}
			if(vreme.getHour() > l.getRadnoVremeDo()) {
				lekarVreme = false;
			}
			if(vreme.isBefore(LocalDateTime.now())) {
				lekarVreme=false;
			}
			if(lekarVreme == true) {
				konacniLekar=l;
				nadjenLekar = true;
			}
		
		}	
		
	}
	
	
	///////////// proveravam da li je poctni lekar slobodan u tom terminu,ako mogu njega da ostavim

	if(l1.getTipPregleda().equals(tip1)) {
	//System.out.println("DALI OVDE DODJE UOPSTE");
	boolean lekarVreme = true;
	Set<ZauzetostLekara> zauzetost = l1.getListaZauzetostiLekara();
	Set<LekarOdsustvo> odsustvo = l1.getListaOdsustava();
	
	if(zauzetost != null) {
		for(ZauzetostLekara z : zauzetost) {
			
			//System.out.println(z.getKraj());
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
		}
		}
		for(LekarOdsustvo o : odsustvo) {
			if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
			if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
		}
		if(vreme.getHour()< l1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(vreme.getHour() > l1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(vreme.getHour() < l1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(vreme.getHour() > l1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(vreme.isBefore(LocalDateTime.now())) {
			lekarVreme=false;
		}
		if(lekarVreme == true) {
			konacniLekar=l1;
			nadjenLekar = true;
		}
	
	}	
	if(nadjenLekar==true && nadjenaSala == true) {
		oba=true;
		break;
	}
	vreme=vreme.plusHours(1);
	
	}
	String datumivreme[] = konacnoVreme.toString().split("T");
	String rezultat = datumivreme[0] + " " + datumivreme[1]; 
	return rezultat;
	
	
	/////////
	
	
	
}


public String nemaSale(Long pregledId) {
	Pregled pregled = this.findOne(pregledId);
	LocalDateTime vreme = pregled.getDatumPregledaOd();
	LocalDateTime pocetnoVreme = pregled.getDatumPregledaOd();
	Long idPregleda = pregled.getId();
	Klinika kl = pregled.getLekar().getKlinika();
	Set<Sala>SaleKlinike = kl.getSale();
	Lekar l1 = pregled.getLekar();
	Set<ZauzetostLekara> originalniLekarZ =l1.getListaZauzetostiLekara();
	
	//brisem iz zauzetosti lekara
	for(ZauzetostLekara zlo : originalniLekarZ) {
		if(zlo.getPocetak()==vreme && zlo.getKraj()==vreme.plusHours(1)) {
			originalniLekarZ.remove(zlo);
		}
	}
	
//	l1.setListaZauzetostiLekara(originalniLekarZ);
//	lekarService.save(l1);
	
//	List<ZauzetostLekara> zl = zauzetostLekaraService.findAll();
	//zauzetostLekaraService.remove(id);
	
	
	TipPregleda tip1=pregled.getTipPregleda();
	Sala konacnaSala = new Sala();
	Lekar konacniLekar = new Lekar();
	LocalDateTime konacnoVreme = null;
	boolean nadjenaSala = false;
	boolean nadjenLekar = false;
	
	boolean oba = false;
	while(oba == false) {
		
		if(vreme.getHour()>15) {
			vreme=vreme.plusDays(1);
			vreme=vreme.withHour(0);
			continue;
		}else if(vreme.getHour()<8) {
			vreme=vreme.plusHours(1);
			continue;
		}
	
	for(Sala s : SaleKlinike) {
		boolean salaVreme = true;
	//	System.out.println(s.getId());
		Set<ZauzetostSala> zauzetostSala = s.getListaZauzetostiSala();
		if(zauzetostSala != null) {
		for(ZauzetostSala z : zauzetostSala) {
		//	System.out.println(z.getKraj()+"aAA KRAJ   SSS");
		//	System.out.println(vreme);
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				
				salaVreme = false;
			}
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				salaVreme=false;
			}
		}
		}
		if(salaVreme == true) {
			konacnaSala = s;
			nadjenaSala = true;
			konacnoVreme=vreme;
			
		}
		
		
	}
	System.out.println(konacnoVreme);
	
	//pretrazujem lekare sve
	
	for(Lekar l : kl.getLekari()) {
	//	System.out.println(l.getTipPregleda().getId());
	//	System.out.println(tip1.getId());
		if(l.getTipPregleda().equals(tip1)) {
	//	System.out.println("DALI OVDE DODJE UOPSTE");
		boolean lekarVreme = true;
		Set<ZauzetostLekara> zauzetost = l.getListaZauzetostiLekara();
		Set<LekarOdsustvo> odsustvo = l.getListaOdsustava();
		
		if(zauzetost != null) {
			for(ZauzetostLekara z : zauzetost) {
				
				//System.out.println(z.getKraj());
				if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
					lekarVreme = false;
				}
				if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
					lekarVreme = false;
				}
			}
			}
			for(LekarOdsustvo o : odsustvo) {
				if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
					lekarVreme = false;
				}
				if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
					lekarVreme = false;
				}
			}
			if(vreme.getHour()< l.getRadnoVremeOd()) {
				lekarVreme = false;
			}
			if(vreme.getHour() > l.getRadnoVremeDo()) {
				lekarVreme = false;
			}
			if(vreme.getHour() < l.getRadnoVremeOd()) {
				lekarVreme = false;
			}
			if(vreme.getHour() > l.getRadnoVremeDo()) {
				lekarVreme = false;
			}
			if(vreme.isBefore(LocalDateTime.now())) {
				lekarVreme=false;
			}
			if(lekarVreme == true) {
				konacniLekar=l;
				nadjenLekar = true;
			}
		
		}	
		
	}
	
	
	///////////// proveravam da li je poctni lekar slobodan u tom terminu,ako mogu njega da ostavim

	if(l1.getTipPregleda().equals(tip1)) {
	//System.out.println("DALI OVDE DODJE UOPSTE");
	boolean lekarVreme = true;
	Set<ZauzetostLekara> zauzetost = l1.getListaZauzetostiLekara();
	Set<LekarOdsustvo> odsustvo = l1.getListaOdsustava();
	
	if(zauzetost != null) {
		for(ZauzetostLekara z : zauzetost) {
			
			//System.out.println(z.getKraj());
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
			if(!vreme.isAfter(z.getKraj()) && !vreme.isBefore(z.getPocetak())) {
				lekarVreme = false;
			}
		}
		}
		for(LekarOdsustvo o : odsustvo) {
			if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
			if(!vreme.isAfter(o.getKraj()) && !vreme.isBefore(o.getPocetak())) {
				lekarVreme = false;
			}
		}
		if(vreme.getHour()< l1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(vreme.getHour() > l1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(vreme.getHour() < l1.getRadnoVremeOd()) {
			lekarVreme = false;
		}
		if(vreme.getHour() > l1.getRadnoVremeDo()) {
			lekarVreme = false;
		}
		if(vreme.isBefore(LocalDateTime.now())) {
			lekarVreme=false;
		}
		if(lekarVreme == true) {
			konacniLekar=l1;
			nadjenLekar = true;
		}
	
	}	
	/////////
	//System.out.println("Lekar=   " + nadjenLekar+ "       sala:  "+nadjenaSala);
	
	///ako je pronadjen i lekar i sala,dodeli ih
	if(nadjenLekar==true && nadjenaSala == true) {
		oba=true;
		pregled.setDatumPregledaOd(konacnoVreme);
		pregled.setDatumPregledaDo(konacnoVreme.plusHours(1));
		pregled.setSala(konacnaSala);
		pregled.setLekar(konacniLekar);
		this.save(pregled);
		
		
		Set<ZauzetostLekara> zauzetost = konacniLekar.getListaZauzetostiLekara();
		ZauzetostLekara nova= new ZauzetostLekara();
		nova.setPocetak(konacnoVreme);
		nova.setKraj(konacnoVreme.plusHours(1));
		nova.setLekar(konacniLekar);
		zauzetost.add(nova);
		zauzetostLekaraService.save(nova);
		
		Set<ZauzetostSala> zauzetostSala = konacnaSala.getListaZauzetostiSala();
		ZauzetostSala novaz = new ZauzetostSala();
		novaz.setPocetak(konacnoVreme);
		novaz.setKraj(konacnoVreme.plusHours(1));
		novaz.setSala(konacnaSala);
		zauzetostSala.add(novaz);
		zauzetostSalaService.save(novaz);
		
		//ako je doslo do promena,posalji mail
		
			String datumivreme[]=pregled.getDatumPregledaOd().toString().split("T");
			
			EmailDTO emailDTO = new EmailDTO(1, "Odluka o pregledu.",
					"Ako želite da prihvatite pregled: <br>"
					+"Vreme pregleda: "+ datumivreme[0] + " "+datumivreme[1]+"<br>"
					+"Sala pregleda: " +pregled.getSala().getNaziv()+"<br>"
					+"Lekar: "+pregled.getLekar().getPrezime()+" "+pregled.getLekar().getIme()+"<br>"
					+"Cena: "+pregled.getCena()+"<br>"
					+" onda kliknite ovde: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=potvrdi, <br> a ako odbijate: <br> http://localhost:8080/PotvrdaMailom.html?pregledID="+idPregleda+"&odluka=odustani", "");

			
			try 
			{		
			emailService.sendNotificaitionAsync(emailDTO);
			}
			catch( Exception e )
			{
			//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
			}
			
			EmailDTO emailDTO2 = new EmailDTO(1, "Obavestenje o pregledu.",
					"Dodat je novi pregled: <br>"
					+"Vreme pregleda: "+ datumivreme[0] + " "+datumivreme[1]+"<br>"
					+"Sala pregleda: " +pregled.getSala().getNaziv()+"<br>"
					+"Pacijent: "+pregled.getPacijent().getPrezime()+" "+pregled.getPacijent().getIme()+"<br>", "");

			
			try 
			{		
			emailService.sendNotificaitionAsync(emailDTO2);
			}
			catch( Exception e )
			{
			//logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			System.out.println("### Greska prilikom slanja mail-a! ###");
			}
			
			
			
			
			
			
			pregled.setPrihvacen(false);
			this.save(pregled);
			
		
		
		break;
	}
	
	//iteracija
	vreme=vreme.plusHours(1);
	
	}
	return "Sve ok";
	
}

public void automatskoDodavanjeSala() {
	List<Pregled> sviPregledi = this.findAll();

	List<Pregled> zahtevi = new ArrayList<>();
	
	for(Pregled pom : sviPregledi) {
		if(pom.getSala()==null) {
			zahtevi.add(pom);
		}
	}
	
	if(zahtevi != null) {
		for(Pregled z : zahtevi) {
			nemaSale(z.getId());
		}
	}
	
}

public ArrayList<Integer> vratiZaGrafik(LocalDateTime Od,LocalDateTime Do,Long tip) {
	ArrayList<Integer> brojPregleda= new ArrayList<>();
//	ArrayList<LocalDateTime> datumi=new ArrayList<>();
	Integer brojac = 0;
	LocalDateTime pom = Od;
	
	
	while(pom.isBefore(Do)) {
	
		if(tip == 0) {
			pom=Od.plusMonths(1);
		}else if(tip == 1) {
			pom=Od.plusWeeks(1);
		}else {
			pom=Od.plusDays(1);
		}
		
		brojac=0;
		for(Pregled p : this.findAll()) {
			
			if(p.getDatumPregledaOd().isAfter(Od) && p.getDatumPregledaDo().isBefore(pom)) {
				brojac++;
			}
		}
		
		brojPregleda.add(brojac);
//		datumi.add(Od);
		if(tip == 0) {
			Od=Od.plusMonths(1);
		}else if(tip == 1) {
			Od=Od.plusWeeks(1);
		}else {
			Od=Od.plusDays(1);
		}
		
	}
	return brojPregleda;
	
}

public ArrayList<LocalDateTime> vratiDatumeZaGrafik(LocalDateTime Od,LocalDateTime Do,Long tip){
	
	ArrayList<LocalDateTime> datumi=new ArrayList<>();
	while(Od.isBefore(Do)) {
		datumi.add(Od);
		if(tip == 0) {
			Od=Od.plusMonths(1);
		}else if(tip == 1) {
			Od=Od.plusWeeks(1);
		}else {
			Od=Od.plusDays(1);
		}
	}
	return datumi;
	
}

public int vratiPrihodeKlinike(LocalDateTime Od,LocalDateTime Do) {
	int prihodi = 0;
	for(Pregled p : this.findAll()) {
		
		if(p.getDatumPregledaOd().isAfter(Od) && p.getDatumPregledaDo().isBefore(Do)) {
			prihodi+=p.getCena();
		}
	}
	return prihodi;
}


	
}
