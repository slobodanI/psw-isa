package rs.ac.uns.ftn.informatika.jpa.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.dto.KlinikaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;

@Controller
@RequestMapping(value = "/api/klinika")
public class ControllerKlinika {

	@Autowired
	private KlinikaService klinikaService;
	
	@RequestMapping(value="/klinike",
			produces = "application/json",
			method = RequestMethod.GET
			)
	@ResponseBody
	public ResponseEntity<List<KlinikaDTO>> getAllKlinike() {
		List<Klinika> klinike = klinikaService.findAll();
		
		List<KlinikaDTO> klinikeDTO = new ArrayList<KlinikaDTO>();
		for( Klinika k : klinike) {
			klinikeDTO.add(new KlinikaDTO(k));
		}
		
		return new ResponseEntity<>(klinikeDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view",
			produces = {MediaType.TEXT_HTML_VALUE},
			method = RequestMethod.GET)
	public String view() {	
		//System.out.println("asdasdasdasdasdasdasdasd");
		return "/proba.html";
	}
	
	@RequestMapping(value = "/viewLekar",
			produces = {MediaType.TEXT_HTML_VALUE},
			method = RequestMethod.GET)
	public String viewLekar() {
		return "/profilLekara.html";
	}
	
}
