package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.ReceptDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.Recept;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.ReceptRepository;

@Service
public class ReceptService 
{
	@Autowired
	private ReceptRepository receptRepository;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private MedicinskaSestraService medSestraService;
	
	public Recept findOne(Long id) {
		return receptRepository.findById(id).orElseGet(null);
	}

	public List<Recept> findAll() {
		return receptRepository.findAll();
	}
	
	public Page<Recept> findAll(Pageable page) {
		return receptRepository.findAll(page);
	}

	public Recept save(Recept recept) {
		return receptRepository.save(recept);
	}

	public void remove(Long id) {
		receptRepository.deleteById(id);
	}

	//metoda vraca neoverene recepte klinike sa datim ID-jem
	public List<ReceptDTO> getRecepti(Long id) 
	{
		MedicinskaSestra ms = medSestraService.findOne(id);
		Klinika klinika = ms.getKlinika();
		Set<Pacijent> pacijenti = klinika.getPacijenti();
		
		List<ReceptDTO> receptiZaOveru = new ArrayList<ReceptDTO>();
		
		for (Pacijent p : pacijenti) 
		{
			ZdravstveniKarton zk = p.getZdravstveniKarton();
			Set<Pregled> pregledi = zk.getListaPregleda();
			
			for (Pregled preg : pregledi) 
			{
				if(preg.getLekar().getKlinika().getId()==klinika.getId())
				{
					Set<Recept> recepti = preg.getRecepti();
					
					for (Recept r : recepti) 
					{
						if(r.getOveren()==false)
						{
							receptiZaOveru.add(new ReceptDTO(r));
						}
					}
				}
			}
		}
		
		return receptiZaOveru;
	}

	public String overi(Long id, Long id_recepta) {
		
		MedicinskaSestra ms = medSestraService.findOne(id);
		Recept r = this.findOne(id_recepta);
		r.setMedicinskaSestra(ms);
		r.setOveren(true);
		this.save(r);
		Recept rec = this.findOne(id_recepta);
		ms.getRecepti().add(rec);
		medSestraService.save(ms);
		
		return "Uspešno overen recept";
	}
}
