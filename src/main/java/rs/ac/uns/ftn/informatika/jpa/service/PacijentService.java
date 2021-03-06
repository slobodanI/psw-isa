package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTOzaStrudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTOzaStudent1;
import rs.ac.uns.ftn.informatika.jpa.dto.PacijentDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ZdravsveniKartonInfoDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private ZdravstveniKartonService zkService;
	
	public Pacijent findOne(Long id) {
		return pacijentRepository.findById(id).orElseGet(null);
	}

	public List<Pacijent> findAll() {
		return pacijentRepository.findAll();
	}
	
	public Pacijent save(Pacijent pac) {
		return pacijentRepository.save(pac);
	}

	public void remove(Long id) {
		pacijentRepository.deleteById(id);
	}
	
	public Pacijent findOneByUsername(String username) {
		return pacijentRepository.findOneByUsername(username);
	}
	
	public Pacijent updatePacijent(Long id, PacijentDTO pacijentDTO) {
		
		Pacijent pacijent = findOne(id);		
		if (pacijent == null) {
			return null;
		}
		
		if(pacijentDTO.getPassword().length() < 6) {
			return null;
		}
		
		pacijent.setIme(pacijentDTO.getIme());
		pacijent.setPrezime(pacijentDTO.getPrezime());
		pacijent.setUsername(pacijentDTO.getUsername());
		pacijent.setPassword(pacijentDTO.getPassword());
		pacijent.setAdresa(pacijentDTO.getAdresa());
		pacijent.setGrad(pacijentDTO.getGrad());
		pacijent.setDrzava(pacijentDTO.getDrzava());
		pacijent.setBrojTel(pacijentDTO.getBrojTel());

		pacijent = save(pacijent);
		
		return pacijent;
	}
	
	//vraca sve pacijente klinike sa zadatim ID-om
	public List<PacijentDTO> vratiSvePacijenteKlinike(Long id) {
		
		List<Pacijent> lista = this.findAll();
		
		List<PacijentDTO> rezultat = new ArrayList<>();
		
		//idi kroz pacijente u tabeli...
		for (Pacijent pac : lista) 
		{
			Set<Klinika> klinike = pac.getKlinike();
			
			//...pa kroz njihove klinike...
			for (Klinika kl : klinike) 
			{
				//...i ako se id slaze sa id klinike dodaj u listu
				if (kl.getId()==id) 
				{
					rezultat.add(new PacijentDTO(pac));
				}
			}
		}
		
		return rezultat;
	}
	
	//vraca sve pacijente klinike sa zadatim ID-om lekara
	public List<PacijentDTO> vratiSvePacijenteLekara(Long id) {
		Lekar lekar=lekarService.findOne(id);
		Klinika klinika=lekar.getKlinika();
		List<Pacijent> lista = this.findAll();
		
		List<PacijentDTO> rezultat = new ArrayList<>();
		
		//idi kroz pacijente u tabeli...
		for (Pacijent pac : lista) 
		{
			Set<Klinika> klinike = pac.getKlinike();
			
			//...pa kroz njihove klinike...
			for (Klinika kl : klinike) 
			{
				//...i ako se id slaze sa id klinike dodaj u listu
				if (kl.getId()==klinika.getId()) 
				{
					rezultat.add(new PacijentDTO(pac));
				}
			}
		}
		
		return rezultat;
	}
	
	
	
	//vraca sve klinike u kojima je pacijent bio
	public List<KlinikaDTOzaStrudent1> getPoseceneKlinike(Long idPacijenta) {
		
		Pacijent pacijent = this.findOne(idPacijenta);		
		
		List<KlinikaDTOzaStrudent1> poseceneKlinike = new ArrayList<KlinikaDTOzaStrudent1>();
		
		for(Pregled p : pacijent.getZakazaniPregledi()) {// zakazani pregledi su zapravo svi pregledi
			//if(p.isObavljen()) { //pregled ne mora biti obavljen, mora samo biti zakazan
				KlinikaDTOzaStrudent1 klinika = new KlinikaDTOzaStrudent1(p.getLekar().getKlinika());
				
				//da nemam duplikata u listi
				boolean flag = false;
				for(KlinikaDTOzaStrudent1 k : poseceneKlinike) {
					if(k.getId().equals(klinika.getId())) {
						flag = true;
					}
				}
				if(flag == false) {
					poseceneKlinike.add(klinika);
				}
				
			//}
		}
		
		return poseceneKlinike;
		
	}
	
	//vraca sve klinike u kojima je pacijent bio
	public List<LekarDTOzaStudent1> getPoseceneLekare(Long idPacijenta) {
		
		Pacijent pacijent = this.findOne(idPacijenta);		
		
		List<LekarDTOzaStudent1> poseceniLekari = new ArrayList<LekarDTOzaStudent1>();
		
		for(Pregled p : pacijent.getZakazaniPregledi()) {// zakazani pregledi su zapravo svi pregledi
			//if(p.isObavljen()) { // pregled ne mora biti obavljen, mora samo biti zakazan
				LekarDTOzaStudent1 lekar = new LekarDTOzaStudent1(p.getLekar());
				
				//da nemam duplikata u listi
				boolean flag = false;
				for(LekarDTOzaStudent1 l : poseceniLekari) {
					if(l.getId().equals(lekar.getId())) {
						flag = true;
					}
				}
				if(flag == false) {
					poseceniLekari.add(lekar);
				}
				
			//}
		}
		
		return poseceniLekari;
		
	}
	
	//metoda vraca pacijente doktora sa nekim id-jem ali samo one koji cekaju na pregled i id njihovog pregleda
	public HashMap<Long, PacijentDTO> vratiSvePacijenteDoktora(Long id) {
			
		Lekar lek = lekarService.findOne(id);
			
		HashMap<Long, PacijentDTO> mapa = new HashMap<Long, PacijentDTO>();
			
		//vrati zakazane preglede...
		Set<Pregled> pregledi = lek.getListaZakazanihPregleda();
			
		//...idi kroz njih...
		for (Pregled pregled : pregledi) 
		{
			if(pregled.getPacijent()!=null)
			{
				
				//...ako dijagnoza jos nije doneta pregled nije obavljen...
				if(pregled.isObavljen() == false)
				{
					//...pa dodaj pacijenta u rezultat
					Pacijent pac = pregled.getPacijent();
					mapa.put(pregled.getId(), new PacijentDTO(pac));
				}
			}
		}
			
		return mapa;
	}
	
	//vrati zdravstveni karton pacijenta sa datim id-jem
	public ZdravstveniKarton getKarton(Long id) 
	{
		Pacijent pacijent = this.findOne(id);
		ZdravstveniKarton zk = pacijent.getZdravstveniKarton();
		return zk;
	}

	public String setKarton(Long id, ZdravsveniKartonInfoDTO zk) 
	{
		
		if(zk.getKrvnaGrupa().isEmpty() || zk.getDioptrija().isEmpty() || zk.getAlergije().isEmpty() || zk.getListaBolesti().isEmpty())
		{
			return "Morate popuniti sva polja";
		}
		
		if(zk.getVisina().isNaN() || zk.getTezina().isNaN())
		{
			return "Težina i visina moraju biti brojevi";
		}
		
		if(zk.getVisina()<0 || zk.getTezina()<0)
		{
			return "Težina i visina moraju biti pozitivni brojevi";
		}
		
		Pacijent pacijent = this.findOne(id);
		ZdravstveniKarton karton = pacijent.getZdravstveniKarton();
		karton.setKrvnaGrupa(zk.getKrvnaGrupa());
		karton.setDioptrija(zk.getDioptrija());
		karton.setVisina(zk.getVisina());
		karton.setTezina(zk.getTezina());
		karton.setAlergije(zk.getAlergije());
		karton.setListaBolesti(zk.getListaBolesti());
		zkService.save(karton);
		
		
		return "Uspešno unete informacije u karton";
	}
	
	
}
