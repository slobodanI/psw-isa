package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.informatika.jpa.dto.LekDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Lek;
import rs.ac.uns.ftn.informatika.jpa.repository.LekRepository;

@Service
public class LekService 
{

	@Autowired
	private LekRepository lekRepository;
	
	@Autowired
	private JsonValidation jsonValidation;
	
	
	public Lek findOne(Long id) {
		return lekRepository.findById(id).orElseGet(null);
	}
	
	public List<Lek> findAll() {
		return lekRepository.findAll();
	}
	
	public Lek save(Lek lek) {
		return lekRepository.save(lek);
	}

	public void remove(Long id) {
		lekRepository.deleteById(id);
	}
	
	public List<LekDTO> vratiSveLekoveDTO(List<Lek> lekovi)
	{
		List<LekDTO> lekoviDTO = new ArrayList<>();
		for (Lek l : lekovi) {
			lekoviDTO.add(new LekDTO(l));
		}
		return lekoviDTO;
	}
	
	public String kreirajLek(LekDTO lekDTO)
	{
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			jsonValidation.validateJSON(mapper.writeValueAsString(lekDTO), "Lek.json");
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			return "Objekat se ne slaže sa JSON šemom";	
		} 
		
		Lek lek = new Lek();
		lek.setNaziv(lekDTO.getNaziv());
		lek.setSifra(lekDTO.getSifra());
		
		
		if(lek.getNaziv().isEmpty() || lek.getSifra().isEmpty())
		{
			return "Morate uneti šifru i naziv";
		}
		
		
		List<Lek> sviLekovi = this.findAll();
			
		for(Lek l : sviLekovi) 
		{
			if(l.getNaziv().equals(lek.getNaziv())) 
			{
				return "Taj naziv već postoji";
			}
		}
		for(Lek l : sviLekovi) 
		{
			if(l.getSifra().equals(lek.getSifra())) 
			{
				return "Ta šifra vec postoji";
			}
		}
		
		this.save(lek);
		
		return "Uspešno kreiran lek";
	}
	
}
