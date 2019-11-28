package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		return ZKRepository.findOneByPacijentId(idPacijenta);
	}
	
	
	
}
