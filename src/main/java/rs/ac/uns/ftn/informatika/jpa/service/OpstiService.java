package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;

import org.springframework.stereotype.Service;

@Service
public class OpstiService {

	//postavljanje pacijentovog atributa AktiviranNalog na true
	public Pacijent serviceSetAktiviranNalog(Pacijent pacijent)
	{
		pacijent.setAktiviranNalog(true);
		return pacijent;
	}
	
}
