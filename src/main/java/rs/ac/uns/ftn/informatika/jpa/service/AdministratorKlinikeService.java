package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.AdministratorKlinikeDTO;
import rs.ac.uns.ftn.informatika.jpa.model.AdministratorKlinike;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.repository.AdministratorKlinikeRepository;

@Service
public class AdministratorKlinikeService {
	
	
	@Autowired
	private AdministratorKlinikeRepository administratorKlinikeRepository;
	
	@Autowired
	KlinikaService klinikaService;
	
	public AdministratorKlinike findOne(Long id) {
		return administratorKlinikeRepository.findById(id).orElseGet(null);
	}
	
	public List<AdministratorKlinike> findAll() {
		return administratorKlinikeRepository.findAll();
	}
	
	public AdministratorKlinike save(AdministratorKlinike adminK) {
		return administratorKlinikeRepository.save(adminK);
	}

	public void remove(Long id) {
		administratorKlinikeRepository.deleteById(id);
	}
	
	public AdministratorKlinike kreirajAdminK(AdministratorKlinikeDTO admin)
	{
		/*
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			jsonValidation.validateJSON(mapper.writeValueAsString(admin), "AdminKCschema.json");
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			return null;	
		} */
		
		Klinika klinika = klinikaService.findOne(admin.getKlinika().getId());
		//System.out.println(klinika.toString());
		
		AdministratorKlinike administrator = new AdministratorKlinike();
		administrator.setIme(admin.getIme());
		administrator.setPrezime(admin.getPrezime());
		administrator.setUsername(admin.getUsername());
		administrator.setPassword(admin.getPassword());
		administrator.setEmail(admin.getEmail());
		administrator.setKlinika(klinika);
		administrator.setPromenjenaLozinka(admin.getPromenjenaLozinka());
		administrator.setUloga(admin.getUloga());
		
		if(admin.getIme().isEmpty() || admin.getPrezime().isEmpty() || admin.getUsername().isEmpty() || admin.getEmail().isEmpty())
		{
			return null;
		}
		
		
		List<AdministratorKlinike> sviAdmini = this.findAll();
			
		for(AdministratorKlinike adm : sviAdmini) 
		{
			if(adm.getUsername().equals(administrator.getUsername())) 
			{
				return null;
			}
		}
		for(AdministratorKlinike adm : sviAdmini) 
		{
			if(adm.getEmail().equals(administrator.getEmail())) 
			{
				return null;
			}
		}
		
		
		return administrator;
	}
	
	
	public Boolean promenjenaLozinka(Long id) {
		
		AdministratorKlinike admin = this.findOne(id);
		
		return admin.getPromenjenaLozinka();
		
	}
}
