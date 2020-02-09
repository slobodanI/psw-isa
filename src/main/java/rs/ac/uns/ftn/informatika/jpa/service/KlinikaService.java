package rs.ac.uns.ftn.informatika.jpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTOzaStudent1PRETRAGA;
import rs.ac.uns.ftn.informatika.jpa.dto.LekarDTOStudent1PretragaLekara;
import rs.ac.uns.ftn.informatika.jpa.dto.OcenaKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.LekarOdsustvo;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.repository.KlinikaRepository;

@Service
public class KlinikaService {
	
	@Autowired
	private KlinikaRepository klinikaRepo;
	
	@Autowired AdministratorKlinikeService adminKService;
	
	public List<Klinika> findAll(){
		return klinikaRepo.findAll();
	}
	
	public Klinika findOne(Long id) {
		return klinikaRepo.findById(id).orElseGet(null);
	}
	public Klinika save(Klinika klinika) {
		return klinikaRepo.save(klinika);
	}
	public void remove(Long id) {
		klinikaRepo.deleteById(id);
	}
	
	public Klinika updateKlinika(Long id,KlinikaDTO klinikaDTO) {
			AdministratorKlinike admin = adminKService.findOne(id);
			Klinika kl = admin.getKlinika();
			
			kl.setNaziv(klinikaDTO.getNaziv());
			kl.setOpis(klinikaDTO.getOpis());
			kl.setAdresa(klinikaDTO.getAdresa());
			
			klinikaRepo.save(kl);
			return kl;
			
	
	
	}

	//metoda za dodavanje klinike
	public String kreirajKliniku(KlinikaDTO klinika) {
		
		try 
		{
			Float.parseFloat(Float.toString(klinika.getLatitude()));
			Float.parseFloat(Float.toString(klinika.getLongitude()));
		} 
		catch (Exception e) 
		{
			return "Latituda i longituda moraju biti brojevi";
		}
		
		Klinika novaKlinika = new Klinika();
		novaKlinika.setNaziv(klinika.getNaziv());
		novaKlinika.setAdresa(klinika.getAdresa());
		novaKlinika.setOpis(klinika.getOpis());
		novaKlinika.setSlobodniTerminiPregleda(klinika.getSlobodniTerminiPregleda());
		novaKlinika.setLatitude(klinika.getLatitude());
		novaKlinika.setLongitude(klinika.getLongitude());
		novaKlinika.setBrojOcena(0);
		novaKlinika.setUkupnaOcena(0d);
		novaKlinika.setPrihod(0d);
		Set<AdministratorKlinike> ak = new HashSet<AdministratorKlinike>();
		novaKlinika.setAdministratoriKlinike(ak);
		Set<Pacijent> pa = new HashSet<Pacijent>();
		novaKlinika.setPacijenti(pa);
		Set<Lekar> l = new HashSet<Lekar>();
		novaKlinika.setLekari(l);
		Set<MedicinskaSestra> ms = new HashSet<MedicinskaSestra>();
		novaKlinika.setMed_sestre(ms);
		Set<Sala> s = new HashSet<Sala>();
		novaKlinika.setSale(s);
		
		
		
		this.save(novaKlinika);
		
		return "Uspešno registrovana klinika";
	}
	
	//ocenjivanje klinike
	public Boolean oceniKliniku(OcenaKlinikeDTO ocenaKlinikeDTO) {
		
//		System.out.println("ID KLINIKE: " + ocenaKlinikeDTO.getIdKlinike());
//		System.out.println("OCENA KLINIKE: " + ocenaKlinikeDTO.getOcena());
		Klinika klinika = findOne(ocenaKlinikeDTO.getIdKlinike());
		if(klinika == null) {
			return false;
		}
		
		klinika.setUkupnaOcena(klinika.getUkupnaOcena() + ocenaKlinikeDTO.getOcena());
		klinika.setBrojOcena(klinika.getBrojOcena() + 1);
		save(klinika);
		
		return true;
	}
	//vraca listu klinika koje imaju lekare koji imaju slobodan termin za uneti datum, i sposobni su za taj tip pregleda
	public List<Klinika> pretraziKlinike(LocalDateTime datum, Long idTipaPregleda) {
		
		//ovo ce trebati za testiranje
		LocalDateTime sada = LocalDateTime.now();
		
//		System.out.println("SADA JE: " + sada.toString());
		
		if(datum != null) {
			if(datum.isBefore(sada)) {
				return null;
			}			
		} else {
			return null;
		}
		
		if(idTipaPregleda != null) {
			if(idTipaPregleda <= 0) {
				return null;
			}
		} else {
			return null;
		}
		
		// ovo ide u return
		List<Klinika> listaKlinikaKojeMiOdgovaraju = new ArrayList<Klinika>();
		
		//prolazim kroz sve klinike
		List<Klinika> sveKlinike = findAll();
		for(Klinika k : sveKlinike) {
			//prolazim kroz sve lekare klinike
			for(Lekar lekar : k.getLekari()) {
				
				boolean flagZaOdsustva = false;
				
				// prvo proverim tip pregleda
				if(!lekar.getTipPregleda().getId().equals(idTipaPregleda)) {
					continue; // predji na sledeceng lekara
				}
				
				// treba proci kroz sva odsustva
				for(LekarOdsustvo odsustvo : lekar.getListaOdsustava()) {
					if(datum.isAfter(odsustvo.getPocetak()) && datum.isBefore(odsustvo.getKraj())) {
						flagZaOdsustva = true;
					}
				}
				
				//datum se nalazu u terminu nekog odsustva
				if(flagZaOdsustva == true) { 
					continue; // predji na sledeceg lekara
				}
				
				// kreiram listu svih mogucih slobodnih termina koje lekar moze imati u toku dana
				ArrayList<LocalDateTime> moguciTermini = new ArrayList<LocalDateTime>();
				
				for(int i = lekar.getRadnoVremeOd(); i <= lekar.getRadnoVremeDo() - 1; i++) {
					LocalDateTime termin = datum.withHour(i);
					moguciTermini.add(termin);
				}
				
				// prolazim kroz sve zakazane termine iz radnog kalendara(LISTA ZAUZETOSTI,a ne zakazaniPregledi)
//				for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {
////					System.out.println("Zauzetost lekara:" + zauzetost.getPocetak());
//					if(moguciTermini.contains(zauzetost.getPocetak())) {
//						moguciTermini.remove(zauzetost.getPocetak());
//					}
//				}
				
				ArrayList<LocalDateTime> moguciTermini2 = new ArrayList<LocalDateTime>();
				for(LocalDateTime pom : moguciTermini) {
					moguciTermini2.add(pom);
				}
				
				
				for(LocalDateTime termin2 : moguciTermini2) {
					LocalDateTime termin2KRAJ = termin2.plusHours(1);
					for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {				
						if(termin2.isBefore(zauzetost.getKraj()) && termin2KRAJ.isAfter(zauzetost.getPocetak())) {
							moguciTermini.remove(termin2);
							break;
						}
						
					}
					
				}
				
				// ako postoji makar jedan slobodan termin za taj dan
				if(moguciTermini.size() > 0) {
					listaKlinikaKojeMiOdgovaraju.add(k);
					break;// da nema duplikata medju klinikama, iskocim iz for petlje koja prolazi kroz lekare
				}
				
//				System.out.println(moguciTermini);
			}
		}
		
		return listaKlinikaKojeMiOdgovaraju;		
	}
	
	
	public List<LekarDTOStudent1PretragaLekara> pretraziLekareUKlinici(Long klinikaID, LocalDateTime datum, Long tipPregledaID){
		
		//ovo ce trebati za testiranje
		LocalDateTime sada = LocalDateTime.now();
		
		if(datum != null) {
			if(datum.isBefore(sada)) {
				return null;
			}			
		} else {
			return null;
		}
		
		if(tipPregledaID != null) {
			if(tipPregledaID <= 0) {
				return null;
			}
		} else {
			return null;
		}
		
		if(klinikaID != null) {
			if(klinikaID <= 0) {
				return null;
			}
		} else {
			return null;
		}
		
		// ovo ide u return
		List<LekarDTOStudent1PretragaLekara> listaLekaraKojiMiOdgovaraju = new ArrayList<LekarDTOStudent1PretragaLekara>();
		
		//trazim kliniku sa id: klinikaID
		List<Klinika> sveKlinike = findAll();
		for(Klinika k : sveKlinike) {
			
			if(k.getId() == klinikaID) {
				//prolazim kroz sve lekare klinike
				for(Lekar lekar : k.getLekari()) {
					
					boolean flagZaOdsustva = false;
					
					// prvo proverim tip pregleda
					if(!lekar.getTipPregleda().getId().equals(tipPregledaID)) {
						continue; // predji na sledeceng lekara
					}
					
					// treba proci kroz sva odsustva
					for(LekarOdsustvo odsustvo : lekar.getListaOdsustava()) {
						if(datum.isAfter(odsustvo.getPocetak()) && datum.isBefore(odsustvo.getKraj())) {
							flagZaOdsustva = true;
						}
					}
					
					//datum se nalazu u terminu nekog odsustva
					if(flagZaOdsustva == true) { 
						continue; // predji na sledeceg lekara
					}
					
					// kreiram listu svih mogucih slobodnih termina koje lekar moze imati u toku dana
					ArrayList<LocalDateTime> moguciTermini = new ArrayList<LocalDateTime>();
					
					for(int i = lekar.getRadnoVremeOd(); i <= lekar.getRadnoVremeDo() - 1; i++) {
						LocalDateTime termin = datum.withHour(i);
						moguciTermini.add(termin);
					}
					
					
					// prolazim kroz sve zakazane termine iz radnog kalendara(LISTA ZAUZETOSTI,a ne zakazaniPregledi)
//					for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {
////						//System.out.println("Zauzetost lekara:" + zauzetost.getPocetak());
////						if(moguciTermini.contains(zauzetost.getPocetak())) {
////							moguciTermini.remove(zauzetost.getPocetak());
////							continue;
////						}
////						for(int i = 0; i < moguciTermini.size(); i++) {
////							if(moguciTermini.get(i).isBefore(zauzetost.getKraj()) && moguciTermini.get(i).plusHours(1).isAfter(zauzetost.getPocetak())) {
////								moguciTermini.remove(i);
////								continue;
////							}
////						}
//						
//						for(LocalDateTime termin2 : moguciTermini) {
//							LocalDateTime termin2KRAJ = termin2.plusHours(1);
//							if(termin2.isAfter(zauzetost.getKraj()) || termin2KRAJ.isBefore(zauzetost.getPocetak())) {
//								moguciTermini2.add(termin2);
//							}
//						}
//						
//					}
					
					ArrayList<LocalDateTime> moguciTermini2 = new ArrayList<LocalDateTime>();
					for(LocalDateTime pom : moguciTermini) {
						moguciTermini2.add(pom);
					}
					
					
					for(LocalDateTime termin2 : moguciTermini2) {
						LocalDateTime termin2KRAJ = termin2.plusHours(1);
						for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {
//							System.out.println("DA LI JE : " + termin2 + ", PRE : " + zauzetost.getKraj());
//							System.out.println(",A DA LI JE : " + termin2KRAJ +  "POSLE : " + zauzetost.getPocetak());
							if(termin2.isBefore(zauzetost.getKraj()) && termin2KRAJ.isAfter(zauzetost.getPocetak())) {
//								System.out.println("********* BRISANJE : " + termin2 + "**************");
								moguciTermini.remove(termin2);
								break;
							}
							
						}
						
					}
					
					
					// ako postoji makar jedan slobodan termin za taj dan
					if(moguciTermini.size() > 0) {
						LekarDTOStudent1PretragaLekara lekarDTO = new LekarDTOStudent1PretragaLekara(lekar);
						lekarDTO.setMoguciTermini(moguciTermini);
						listaLekaraKojiMiOdgovaraju.add(lekarDTO);
						
					}
					
//					System.out.println(moguciTermini);
				}
			}
			
		}
		//ako je nekako poslat pogresan id klinike...
		if(listaLekaraKojiMiOdgovaraju.size() == 0) {
			return null;
		}
		
		return listaLekaraKojiMiOdgovaraju;
		
	}
	
public List<LekarDTOStudent1PretragaLekara> pretraziLekareUKliniciPrekoKlinike(Long klinikaID, LocalDateTime datum, Long tipPregledaID, String imeLekara, String prezimeLekara, Double ocenaVecaOd){
		
		//ovo ce trebati za testiranje
		LocalDateTime sada = LocalDateTime.now();
		
		if(datum != null) {
			if(datum.isBefore(sada)) {
				return null;
			}			
		} else {
			return null;
		}
		
		if(tipPregledaID != null) {
			if(tipPregledaID <= 0) {
				return null;
			}
		} else {
			return null;
		}
		
		if(klinikaID != null) {
			if(klinikaID <= 0) {
				return null;
			}
		} else {
			return null;
		}
		
		//ova polja nisu obavezna na za pretragu, pa zato mogu biti prazni stringovi
		if(imeLekara == null) {
			return null;
		}
		
		if(prezimeLekara == null) {
			return null;
		}
		
		if(ocenaVecaOd == null) {
			return null;
		}
		
		// ovo ide u return
		List<LekarDTOStudent1PretragaLekara> listaLekaraKojiMiOdgovaraju = new ArrayList<LekarDTOStudent1PretragaLekara>();
		
		//trazim kliniku sa id: klinikaID
		List<Klinika> sveKlinike = findAll();
		for(Klinika k : sveKlinike) {
			
			if(k.getId() == klinikaID) {
				//prolazim kroz sve lekare klinike
				for(Lekar lekar : k.getLekari()) {
					boolean flagZaOdsustva = false;
					
					// prvo proverim tip pregleda
					if(!lekar.getTipPregleda().getId().equals(tipPregledaID)) {
						continue; // predji na sledeceng lekara
					}
					
					//************OVO JE DODATNO, U ODNOSU NA PROSLU FUNKCIJU**********
					
					//provera imena
					if(imeLekara != "") {
						if(!lekar.getIme().contains(imeLekara)) {
							continue;
						}
					}
					
					//provera prezimena
					if(prezimeLekara != "") {
						if(!lekar.getPrezime().contains(prezimeLekara)) {
							continue;
						}
					}
					
					//provera prosecne ocene
					//vec sam proverio da li je null
					double prosecnaOcenaLekara = lekar.getUkupnaOcena() / lekar.getBrojOcena();
					if(prosecnaOcenaLekara < ocenaVecaOd) {
						continue;
					}
					
					//*****************************************************************
					
					// treba proci kroz sva odsustva
					for(LekarOdsustvo odsustvo : lekar.getListaOdsustava()) {
						if(datum.isAfter(odsustvo.getPocetak()) && datum.isBefore(odsustvo.getKraj())) {
							flagZaOdsustva = true;
						}
					}
					
					//datum se nalazu u terminu nekog odsustva
					if(flagZaOdsustva == true) { 
						continue; // predji na sledeceg lekara
					}
					
					// kreiram listu svih mogucih slobodnih termina koje lekar moze imati u toku dana
					ArrayList<LocalDateTime> moguciTermini = new ArrayList<LocalDateTime>();
					
					for(int i = lekar.getRadnoVremeOd(); i <= lekar.getRadnoVremeDo() - 1; i++) {
						LocalDateTime termin = datum.withHour(i);
						moguciTermini.add(termin);
					}
					
					// prolazim kroz sve zakazane termine iz radnog kalendara(LISTA ZAUZETOSTI,a ne zakazaniPregledi)
//					for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {
//						//System.out.println("Zauzetost lekara:" + zauzetost.getPocetak());
//						if(moguciTermini.contains(zauzetost.getPocetak())) {
//							moguciTermini.remove(zauzetost.getPocetak());
//						}
//					}
					
					ArrayList<LocalDateTime> moguciTermini2 = new ArrayList<LocalDateTime>();
					for(LocalDateTime pom : moguciTermini) {
						moguciTermini2.add(pom);
					}
					
					
					for(LocalDateTime termin2 : moguciTermini2) {
						LocalDateTime termin2KRAJ = termin2.plusHours(1);
						for(ZauzetostLekara zauzetost : lekar.getListaZauzetostiLekara()) {
//							System.out.println("DA LI JE : " + termin2 + ", PRE : " + zauzetost.getKraj());
//							System.out.println(",A DA LI JE : " + termin2KRAJ +  "POSLE : " + zauzetost.getPocetak());
							if(termin2.isBefore(zauzetost.getKraj()) && termin2KRAJ.isAfter(zauzetost.getPocetak())) {
//								System.out.println("********* BRISANJE : " + termin2 + "**************");
								moguciTermini.remove(termin2);
								break;
							}
							
						}
						
					}
					
					// ako postoji makar jedan slobodan termin za taj dan
					if(moguciTermini.size() > 0) {
						LekarDTOStudent1PretragaLekara lekarDTO = new LekarDTOStudent1PretragaLekara(lekar);
						lekarDTO.setMoguciTermini(moguciTermini);
						listaLekaraKojiMiOdgovaraju.add(lekarDTO);
						
					}
					
//					System.out.println(moguciTermini);
				}
			}
			
		}
		
		return listaLekaraKojiMiOdgovaraju;
		
	}
	
}