package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PregledDTOStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.ZavrsiPregledDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Recept;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.PregledRepository;

@Service
public class PregledService {
		
	@Autowired
	private PregledRepository pregledRepository;
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private ReceptService receptService;
	
	@Autowired
	private ZdravstveniKartonService zkService;
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
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
	
}
