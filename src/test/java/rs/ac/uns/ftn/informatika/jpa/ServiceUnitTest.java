package rs.ac.uns.ftn.informatika.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.dto.EmailDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.model.Lekar;
import rs.ac.uns.ftn.informatika.jpa.model.Pacijent;
import rs.ac.uns.ftn.informatika.jpa.model.Pregled;
import rs.ac.uns.ftn.informatika.jpa.model.TipPregleda;
import rs.ac.uns.ftn.informatika.jpa.model.ZauzetostLekara;
import rs.ac.uns.ftn.informatika.jpa.model.ZdravstveniKarton;
import rs.ac.uns.ftn.informatika.jpa.repository.KlinikaRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.LekarRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.PacijentRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.PregledRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ZauzetostLekaraRepository;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.PacijentService;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceUnitTest {
		
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@MockBean
	private PregledRepository pregledRepository;
	
	@MockBean
	private KlinikaRepository klinikaRepository;
	
	@MockBean
	private PacijentRepository pacijentRepository;
	
	@MockBean
	private ZauzetostLekaraRepository zauzetostLekaraService;
	
	@MockBean
	private LekarRepository lekarRepositoty;
	
	@MockBean
	private EmailDTO emailDTO;
		
	public static final Pregled noviPregled = new Pregled(15L, "", null, null, LocalDateTime.of(2019, 5, 22, 10, 0), LocalDateTime.of(2019, 5, 22, 10, 30), "", null, null, 10, null, 1000, false, false, false);
	public static final Pacijent pacijent3 = new Pacijent(3l, "ime", "prezime", "user", "pas", "mail@mail", null, null, null, null, 678796797L, true);
	public static final EmailDTO emailDTOobj = null;
	public static final Lekar lekar = new Lekar(1l, "ime", "prezime", 50, new Double(5), null, null, "", null, 8, 16, "", "");
	public static final HashSet<Lekar> lekari = new HashSet<Lekar>(Arrays.asList(lekar));
	public static final Klinika klinika = new Klinika(null, null, null, null, null, 10000, 10000, lekari , null, null);
	public static final Lekar LekarZakazi = new Lekar(); 
	 
	@Before
	public void setUp() {
		Long pregledIDbefore = 15L; 
		Long pacijentIDbefore = 3L;
		Long lekarID = 1L;
		TipPregleda tp = new TipPregleda(10L, "naziv");
		lekar.setTipPregleda(tp);
		
		ZdravstveniKarton zk = new ZdravstveniKarton(10L, "", "", new Double(5), new Double(5), "", null, "");
		pacijent3.setZdravstveniKarton(zk);
		
		List<Pregled> pregledAll = new ArrayList<>();
		pregledAll.add(noviPregled);
		
//		insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan)
//			values (2, null, null, '', '2019-05-22 10:00','2019-05-22 10:30', 10, 1000,3,null, 2, false, false, false);
					
		when(pregledRepository.findById(pregledIDbefore)).thenReturn(Optional.of(noviPregled));
		when(pacijentRepository.findById(pacijentIDbefore)).thenReturn(Optional.of(pacijent3));
		
		//when(new EmailDTO(15, "Uspesno zakazan pregled: ", "Uspesno ste zakazali pregled preko profila klinike <br> Termin: "+ pregled15.getDatumPregledaOd()+"", "")).thenReturn(Optional.of(emailDTOobj));
//		when(klinikaRepository.findAll()).thenReturn(new List<Klinika>(Arrays.asList(klinika)));
		
		ZauzetostLekara nova= new ZauzetostLekara();
		nova.setPocetak(noviPregled.getDatumPregledaOd());
		nova.setKraj(noviPregled.getDatumPregledaDo());
		nova.setLekar(lekar);
		
		when(pregledRepository.findAll()).thenReturn(pregledAll);
		when(pregledRepository.save(noviPregled)).thenReturn(null);
		when(zauzetostLekaraService.save(nova)).thenReturn(null);
		when(lekarRepositoty.findById(lekarID)).thenReturn(Optional.of(lekar));
		when(pacijentRepository.findById(1L)).thenReturn(Optional.of(pacijent3));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testZakaziPredefPregledUNIT() {
		Long pregledID = 15L; 
		Long pacijentID = 3L;
		assertEquals(true, pregledService.zakaziPredefPregled(pregledID, pacijentID)); //poslace mail
		
		verify(pregledRepository, times(1)).findById(pregledID);
		verify(pacijentRepository, times(1)).findById(pacijentID);
	}
	
//	@Test
//	public void testPretraziKlinikeUNIT() {
//		LocalDateTime datum = LocalDateTime.of(2020,3,25,0,0);
//		Long idTipaPregleda = 1L;
//		
//		List<Klinika> klinike = klinikaService.pretraziKlinike(datum, idTipaPregleda); 
//		
//		assertThat(klinike).hasSize(1);
//		assertEquals(klinike.get(0).getNaziv(),"klinika1");		
//		
//		verify(klinikaService, times(1)).findById(pregledID);
//		verify(pacijentRepository, times(1)).findById(pacijentID);
//	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testZakaziPregledUNIT() {
		Long pacijentID = 1L; 
		Long lekarID = 1L;		
		LocalDateTime termin = LocalDateTime.of(2020,10,13,10,0); 
		assertEquals(true, pregledService.zakaziPregled(lekarID, termin, pacijentID));
		
		verify(pregledRepository, times(1)).findAll();
		verify(lekarRepositoty, times(1)).findById(lekarID);
		verify(pacijentRepository, times(1)).findById(pacijentID);
		//verify(pregledRepository, times(1)).save(noviPregled);
	}
	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void testPotvrdiIliOdbiPregledUNIT() {
//		Long pregledID = 18L; 
//		String odluka = "potvrdi";
//		assertEquals(true, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
//		
//		verify(pregledRepository, times(1)).findAll();
//		verify(lekarRepositoty, times(1)).findById(lekarID);
//		verify(pacijentRepository, times(1)).findById(pacijentID);
//	}
	
}
