package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;

public interface ZdravstveniKartonRepository extends JpaRepository<ZdravstveniKarton, Long>{
	
	ZdravstveniKarton findOneByPacijentId(Long idPacijenta);
	
	//@Query("select zk from zdravstveni_karton zk where zk.pacijent_id = ?1")
	//ZdravstveniKarton pronadjiZKpoIDuPacijenta(Long idPacijenta);
	
}
