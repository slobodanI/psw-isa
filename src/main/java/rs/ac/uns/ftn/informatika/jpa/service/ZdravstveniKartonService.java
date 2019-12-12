package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Operacija;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.ZdravstveniKartonRepository;

@Service
public class ZdravstveniKartonService {
	
	@Autowired
	private ZdravstveniKartonRepository ZKRepository;
	
	public ZdravstveniKarton findOne(Long id) {
		return ZKRepository.findById(id).orElseGet(null);
	}

	public List<ZdravstveniKarton> findAll() {
		return ZKRepository.findAll();
	}
	
	public Page<ZdravstveniKarton> findAll(Pageable page) {
		return ZKRepository.findAll(page);
	}

	public ZdravstveniKarton save(ZdravstveniKarton zk) {
		return ZKRepository.save(zk);
	}

	public void remove(Long id) {
		ZKRepository.deleteById(id);
	}
	
	public ZdravstveniKarton pronadjiZKpoIDuPacijenta(Long idPacijenta) {
		//zdravstveni karton treba da sadrzi samo obavljene preglede
		ZdravstveniKarton zdravstveniKarton = ZKRepository.findOneByPacijentId(idPacijenta);
		
		Set<Pregled> listaPregledaObaljveni = new HashSet<Pregled>();
		Set<Operacija> listaOperacijaObavljeni = new HashSet<Operacija>();
		
		for(Pregled p : zdravstveniKarton.getListaPregleda()) {
			if(p.isObavljen()) {
				listaPregledaObaljveni.add(p);
			}
		}
		
		for(Operacija o : zdravstveniKarton.getListaOperacija()) {
			if(o.isObavljen()) {
				listaOperacijaObavljeni.add(o);
			}
		}
		
		zdravstveniKarton.setListaPregleda(listaPregledaObaljveni);
		zdravstveniKarton.setListaOperacija(listaOperacijaObavljeni);
		
		return ZKRepository.findOneByPacijentId(idPacijenta);
	}
	
	
	
}
