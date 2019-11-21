package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.SalaDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Sala;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.SalaService;

@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;
	@Autowired

	private KlinikaService klinikaService;

	@GetMapping(value = "/sale")
	public ResponseEntity<List<SalaDTO>> getSale() {
		List<Sala> sale = salaService.findAll();

		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) {
			saleDTO.add(new SalaDTO(s));
		}

		return new ResponseEntity<>(saleDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SalaDTO> getSalu(@PathVariable Long id) {
		Sala sala = salaService.findOne(id);
		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteSalu(@PathVariable Long id) {

		Sala sala = salaService.findOne(id);
		if (sala != null) {
			salaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping(value = "/updateSalu", consumes = "application/json")
	public ResponseEntity<SalaDTO> updateSalu(@RequestBody SalaDTO salaDTO) {

		Sala sala = salaService.findOne(salaDTO.getId());

		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		sala.setNaziv(salaDTO.getNaziv());
		sala.setZauzetost(salaDTO.getZauzetost());

		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}

	@PostMapping(value = "/addSalu", consumes = "application/json")
	public ResponseEntity<SalaDTO> addSalu(@RequestBody SalaDTO salaDTO) {

		Klinika klinika = klinikaService.findOne(salaDTO.getKlinika().getId());
		Sala sala = new Sala();
		sala.setNaziv(salaDTO.getNaziv());
		sala.setZauzetost(salaDTO.getZauzetost());
		sala.setKlinika(klinika);

		List<Sala> sveSale = salaService.findAll();
		for (Sala s : sveSale) {
			if (s.getNaziv().equals(sala.getNaziv())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);

	}

}
