package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.MedicinskaSestraDTO;
import rs.ac.uns.ftn.informatika.jpa.model.MedicinskaSestra;
import rs.ac.uns.ftn.informatika.jpa.service.MedicinskaSestraService;

@RestController
@RequestMapping(value = "medicinska_sestra")
public class MedicinskaSestraController 
{
	@Autowired
	private MedicinskaSestraService medicinskaSestraService;
	
	//dobavlja medicinsku sestru po id-us
	@GetMapping(value = "/getMS/{id}", consumes = "application/json")
	public ResponseEntity<MedicinskaSestraDTO> getMedicinskaSestra(@PathVariable Long id) {

		MedicinskaSestra sestra = medicinskaSestraService.findOne(id);
		//System.out.println("############# ID: " + id + "#############");

		if (sestra == null) 
		{			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new MedicinskaSestraDTO(sestra), HttpStatus.OK);
	}
}
