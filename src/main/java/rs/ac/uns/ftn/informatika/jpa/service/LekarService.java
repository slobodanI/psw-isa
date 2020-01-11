package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarOdsustvoDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarPregledKalendar;
import rs.ac.uns.ftn.informatika.jpa.dto.OcenaLekaraDTO;

import rs.ac.uns.ftn.informatika.jpa.dto.OperacijaKalendarDTO;

import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;

import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.repository.LekarRepository;

@Service
public class LekarService {

	@Autowired
	private LekarRepository lekarRepository;
	@Autowired
	private AdministratorKlinikeService adminKlinikeService;
	
	public Lekar findOne(Long id) {
		return lekarRepository.findById(id).orElseGet(null);
	}
	
	public List<Lekar> findAll(){
		return lekarRepository.findAll();
	}
  
	public Lekar save(Lekar lekar) {
		return lekarRepository.save(lekar);
	}
  
	public void remove(Long id) {
		lekarRepository.deleteById(id);
	}
	
	//ocenjivanje lekara
	public Boolean oceniLekara(OcenaLekaraDTO ocenaLekaraDTO) {
		
//		System.out.println("ID LEKARA: " + ocenaLekaraDTO.getIdLekara());
//		System.out.println("OCENA: " + ocenaLekaraDTO.getOcena());
		
		Lekar lekar = findOne(ocenaLekaraDTO.getIdLekara());
		if(lekar == null) {
			return false;
		}
		
		lekar.setUkupnaOcena(lekar.getUkupnaOcena() + ocenaLekaraDTO.getOcena());
		lekar.setBrojOcena(lekar.getBrojOcena() + 1);
		save(lekar);
		
		return true;
	}
	
	public List<LekarOdsustvoDTO> getOdsustva(Long id){
		Lekar lekar = this.findOne(id);
		List<LekarOdsustvoDTO> odsustva = new ArrayList<LekarOdsustvoDTO>();
		
		for(LekarOdsustvo o : lekar.getListaOdsustava()) {
			odsustva.add(new LekarOdsustvoDTO(o));
		}
		return odsustva;
	}

	//vraca preglede za prikaz na kalendar
	public List<LekarPregledKalendar> getPregledi(Long id) {
		Lekar lekar = this.findOne(id);
		Set<Pregled> pregledi = lekar.getListaZakazanihPregleda();
		
		List<LekarPregledKalendar> kal = new ArrayList<LekarPregledKalendar>();
		
		for (Pregled pregled : pregledi) 
		{
			//ako pregled nije obavljen dodaj u listu
			if(pregled.isObavljen() == false)
			{
				LekarPregledKalendar pr = new LekarPregledKalendar();
				pr.setId(pregled.getId());
				
				//formatiranje datuma i vremena
//				String[] split = pregled.getSatnica().split("-");
//				String[] date = pregled.getDatumPregleda().split("-");
//				String datum = date[2] + "-" + date[1] + "-" + date[0];
//				
//				pr.setPocetak(datum + "T" + split[0]);
//				pr.setKraj(datum + "T" + split[1]);
//				pr.setTip(pregled.getTipPregleda().toString());
//				pr.setLekarId(pregled.getLekar().getId());
				
				kal.add(pr);
			}
		}
		
		return kal;
	}

	//vraca operacije za prikaz na kalendar
	public List<OperacijaKalendarDTO> getOperacije(Long id) {
		Lekar lekar = this.findOne(id);
		Set<Operacija> operacije = lekar.getOperacije();
		
		List<OperacijaKalendarDTO> kal = new ArrayList<OperacijaKalendarDTO>();
		
		for (Operacija op : operacije) 
		{
			if(op.isObavljen() == false)
			{
				OperacijaKalendarDTO dto = new OperacijaKalendarDTO();
				dto.setId(op.getId());
				
//				String[] vreme = op.getSatnica().split("-");
//				String[] datum = op.getDatumOperacije().split("-");
//				String date = datum[2] + "-" + datum[1] + "-" + datum[0];
//				
//				dto.setPocetak(date + "T" + vreme[0]);
//				dto.setKraj(date + "T" + vreme[1]);
				
				kal.add(dto);
			}
		}
		
		return kal;
	}
	
	//vraca sve lekare klinike pomocu id-a prijavljenog admina klinike
	public List<LekarDTO> vratiSveLekareKlinike(Long id){
		AdministratorKlinike admin = adminKlinikeService.findOne(id);
		Klinika klinika = admin.getKlinika();
		List<Lekar> lista = this.findAll();
		
		List<LekarDTO> rezultat = new ArrayList<>();
		
		for(Lekar l : lista) {
			if (l.getKlinika().getId() == klinika.getId()){
				rezultat.add(new LekarDTO(l));
			}
		}
		
		return rezultat;
		
	}

	
	
}
