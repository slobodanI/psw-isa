package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.informatika.jpa.dto.DijagnozaDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.LekDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Dijagnoza;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.repository.DijagnozaRepository;

@Service
public class DijagnozaService 
{

	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	@Autowired
	private JsonValidation jsonValidation;
	
	
	public Dijagnoza findOne(Long id) {
		return dijagnozaRepository.findById(id).orElseGet(null);
	}
	
	public List<Dijagnoza> findAll() {
		return dijagnozaRepository.findAll();
	}
	
	public Dijagnoza save(Dijagnoza dijag) {
		return dijagnozaRepository.save(dijag);
	}

	public void remove(Long id) {
		dijagnozaRepository.deleteById(id);
	}
	
	//metoda vraca dijagnoze
	public List<DijagnozaDTO> vratiSveDijagnozeDTO(List<Dijagnoza> dijag)
	{
		List<DijagnozaDTO> dijagDTO = new ArrayList<>();
		for (Dijagnoza d : dijag) {
			dijagDTO.add(new DijagnozaDTO(d));
		}
		return dijagDTO;
	}
	
	//metoda za kreiranje dijagnoze
	public String kreirajDijagnozu(DijagnozaDTO dijagDTO)
	{
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			jsonValidation.validateJSON(mapper.writeValueAsString(dijagDTO), "Dijagnoza.json");
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			return "Objekat se ne slaže sa JSON šemom";	
		} 
		
		Dijagnoza dijag = new Dijagnoza();
		dijag.setNaziv(dijagDTO.getNaziv());
		dijag.setSifra(dijagDTO.getSifra());
		
		
		if(dijag.getNaziv().isEmpty() || dijag.getSifra().isEmpty())
		{
			return "Morate uneti šifru i naziv";
		}
		
		
		List<Dijagnoza> sveDijagnoze = this.findAll();
			
		for(Dijagnoza d : sveDijagnoze) 
		{
			if(d.getNaziv().equals(dijag.getNaziv())) 
			{
				return "Taj naziv već postoji";
			}
		}
		for(Dijagnoza d : sveDijagnoze) 
		{
			if(d.getSifra().equals(dijag.getSifra())) 
			{
				return "Ta šifra vec postoji";
			}
		}
		
		this.save(dijag);
		
		return "Uspešno kreiraa dijagnoza";
	}
	
}
