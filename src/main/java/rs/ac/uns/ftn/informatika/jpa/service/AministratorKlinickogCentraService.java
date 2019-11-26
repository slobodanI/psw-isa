package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinickogCentraDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinickogCentra;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.repository.AdministratorKlinickogCentraRepository;

@Service
public class AministratorKlinickogCentraService {

	@Autowired
	private AdministratorKlinickogCentraRepository administratorKlinickogCentraRepository;
	
	@Autowired
	private JsonValidation jsonValidation;
	
	@Autowired
	private AdministratorKlinikeService adminKservice;
	
	public AdministratorKlinickogCentra findOne(Long id) {
		return administratorKlinickogCentraRepository.findById(id).orElseGet(null);
	}

	public List<AdministratorKlinickogCentra> findAll() {
		return administratorKlinickogCentraRepository.findAll();
	}
	
	public AdministratorKlinickogCentra save(AdministratorKlinickogCentra adminKC) {
		return administratorKlinickogCentraRepository.save(adminKC);
	}

	public void remove(Long id) {
		administratorKlinickogCentraRepository.deleteById(id);
	}
	
	public AdministratorKlinickogCentra kreirajAdminKC(AdministratorKlinickogCentraDTO admin)
	{
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			jsonValidation.validateJSON(mapper.writeValueAsString(admin), "AdminKCschema.json");
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			return null;	
		} 
		
		
		AdministratorKlinickogCentra administrator = new AdministratorKlinickogCentra();
		administrator.setIme(admin.getIme());
		administrator.setPrezime(admin.getPrezime());
		administrator.setUsername(admin.getUsername());
		administrator.setPassword(admin.getPassword());
		administrator.setEmail(admin.getEmail());
		administrator.setPromenjenaLozinka(admin.getPromenjenaLozinka());
		administrator.setUloga(admin.getUloga());
		
		if(admin.getIme().isEmpty() || admin.getPrezime().isEmpty() || admin.getUsername().isEmpty() || admin.getEmail().isEmpty())
		{
			return null;
		}
		
		
		List<AdministratorKlinickogCentra> sviAdmini = this.findAll();
			
		for(AdministratorKlinickogCentra adm : sviAdmini) 
		{
			if(adm.getUsername().equals(administrator.getUsername())) 
			{
				return null;
			}
		}
		for(AdministratorKlinickogCentra adm : sviAdmini) 
		{
			if(adm.getEmail().equals(administrator.getEmail())) 
			{
				return null;
			}
		}
		
		
		return administrator;
	}
	
}
