package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Poruka;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;

@Service
public class OpstiService {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private AministratorKlinickogCentraService administratorKlinickogCentraService;
	
	@Autowired
	private AdministratorKlinikeService administratorKlinikeService;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private MedicinskaSestraService medicinskaSestraService;
	
	//postavljanje pacijentovog atributa AktiviranNalog na true i dodaje zdravstveni karton
	public Pacijent serviceSetAktiviranNalog(Pacijent pacijent)
	{
		pacijent.setAktiviranNalog(true);
		ZdravstveniKarton zk = new ZdravstveniKarton();
		zk.setKrvnaGrupa("TBA");
		zk.setDioptrija("TBA");
		zk.setTezina(0d);
		zk.setVisina(0d);
		zk.setAlergije("TBA");
		zk.setListaBolesti("TBA");
		zk.setPacijent(pacijent);
		zk.setListaPregleda(new HashSet<Pregled>());
		zk.setListaOperacija(new HashSet<Operacija>());
		pacijent.setZdravstveniKarton(zk);
		
		return pacijent;
	}
	
	public Boolean savePacijentRegistration(PacijentDTO pacijentDTO) {
		
		if(pacijentDTO.getPassword().length() < 6) {
			return false;
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
				return false;
			}
		}
		
		List<AdministratorKlinickogCentra> sviAKC = administratorKlinickogCentraService.findAll();
		for(AdministratorKlinickogCentra akc : sviAKC) {
			if(akc.getUsername().equals(pacijent.getUsername())) {
				return false;
			}
		}
		
		List<AdministratorKlinike> sviAK = administratorKlinikeService.findAll();
		for(AdministratorKlinike ak : sviAK) {
			if(ak.getUsername().equals(pacijent.getUsername())) {
				return false;
			}
		}
		
		List<Lekar> sviLekari = lekarService.findAll();
		for(Lekar l : sviLekari) {
			if(l.getUsername().equals(pacijent.getUsername())) {
				return false;
			}
		}
		
		List<MedicinskaSestra> sveMS = medicinskaSestraService.findAll();
		for(MedicinskaSestra ms : sveMS) {
			if(ms.getUsername().equals(pacijent.getUsername())) {
				return false;
			}
		}
				
		pacijent = pacijentService.save(pacijent);
		
//		System.out.println("######################### PACIJENT ID:" + pacijent.getId());
		
		Poruka poruka = new Poruka(pacijent.getId(), "Zahtev " + pacijent.getIme() + " " + pacijent.getPrezime(), "Zahtev od " + pacijent.getIme() + " " + pacijent.getPrezime(),
									"mailSaKogaSaljemoSve@gmail.com", pacijent.getEmail(), false);
		porukaService.save(poruka);
		
		return true;
	}
	
	public Boolean logIn(HttpSession session, PacijentDTO userInfo) {
		
		List<Pacijent> sviPacijenti = pacijentService.findAll();
		for(Pacijent p : sviPacijenti) {
			if(p.getUsername().equals(userInfo.getUsername())) {
				if(p.getPassword().equals(userInfo.getPassword())) {
					if(p.getAktiviranNalog().equals(true)) {
						session.setAttribute("id", p.getId());
						session.setAttribute("uloga", p.getUloga());
						return true;
					}
				}
			}
		}
		
		List<AdministratorKlinickogCentra> sviAdministratoriKlinickogCentra = administratorKlinickogCentraService.findAll();
		for(AdministratorKlinickogCentra akc : sviAdministratoriKlinickogCentra) {
			if(akc.getUsername().equals(userInfo.getUsername())) {
				if(akc.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", akc.getId());
					session.setAttribute("uloga", akc.getUloga());
					return true;
				}
			}
		}
		
		List<AdministratorKlinike> sviAdministratoriKlinika = administratorKlinikeService.findAll();
		for(AdministratorKlinike ak : sviAdministratoriKlinika) {
			if(ak.getUsername().equals(userInfo.getUsername())) {
				if(ak.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", ak.getId());
					session.setAttribute("uloga", ak.getUloga());
					return true;
				}
			}
		}
		
		List<Lekar> sviLekari = lekarService.findAll();
		for(Lekar l : sviLekari) {
			if(l.getUsername().equals(userInfo.getUsername())) {
				if(l.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", l.getId());
					session.setAttribute("uloga", l.getUloga());
					return true;
				}
			}
		}
		
		List<MedicinskaSestra> sveMedicinskeSestre = medicinskaSestraService.findAll();
		for(MedicinskaSestra ms : sveMedicinskeSestre) {
			if(ms.getUsername().equals(userInfo.getUsername())) {
				if(ms.getPassword().equals(userInfo.getPassword())) {
					session.setAttribute("id", ms.getId());
					session.setAttribute("uloga", ms.getUloga());
					return true;
				}
			}
		}
		
		return false;
		
	}
	
}
