package rs.ac.uns.ftn.informatika.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.model.Klinika;
import rs.ac.uns.ftn.informatika.jpa.service.KlinikaService;
import rs.ac.uns.ftn.informatika.jpa.service.PregledService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaExampleApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired KlinikaService klinikaService;
	
	@Autowired PregledService pregledService;
	
//	@Test
//	public void testFindAll() {
//		List<Klinika> students = klinikaService.findAll();
//		assertThat(students).hasSize(3);
//	}
	
	//***negativni***
		
	@Test 
	public void testZakaziPredefPregled1() {
		Long pregledID = null; 
		Long pacijentID = 1L;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	@Test 
	public void testZakaziPredefPregled2() {
		Long pregledID = -1L;
		Long pacijentID = 1L;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	@Test 
	public void testZakaziPredefPregled3() {
		Long pregledID = 1L; 
		Long pacijentID = null;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	@Test 
	public void testZakaziPredefPregled4() {
		Long pregledID = 1L; 
		Long pacijentID = -1L;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	@Test 
	public void testZakaziPredefPregled5() {
		Long pregledID = 1L; // nije predefinisan
		Long pacijentID = 1L;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testZakaziPredefPregled6() {
		Long pregledID = 1L; 
		Long pacijentID = 1000L; // ne postoji u bazi
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
	@Test(expected = NullPointerException.class)
	public void testZakaziPredefPregled7() {
		Long pregledID = 1000L; // ne postoji u bazi
		Long pacijentID = 1L;
		assertEquals(false, pregledService.zakaziPredefPregled(pregledID, pacijentID));
	}
	
		
	//***pozitivni*** 
	
	@Test
	@Transactional
	@Rollback(true)
	public void testZakaziPredefPregled8() {
		Long pregledID = 15L; 
		Long pacijentID = 3L;
		assertEquals(true, pregledService.zakaziPredefPregled(pregledID, pacijentID)); //poslace mail
	}
	
	//***negativni***
	
	@Test
	public void testPretraziKlinike1() {
		LocalDateTime juce = LocalDateTime.now().minusDays(1);
		Long idTipaPregleda = 1L;
		assertEquals(null, klinikaService.pretraziKlinike(juce, idTipaPregleda));
	}
	
	@Test
	public void testPretraziKlinike2() {
		LocalDateTime sutra = null;
		Long idTipaPregleda = 1L;
		assertEquals(null, klinikaService.pretraziKlinike(sutra, idTipaPregleda));
	}
	
	@Test
	public void testPretraziKlinike3() {
		LocalDateTime sutra = LocalDateTime.now().plusDays(1);
		Long idTipaPregleda = null;
		assertEquals(null, klinikaService.pretraziKlinike(sutra, idTipaPregleda));
	}
	
	@Test
	public void testPretraziKlinike4() {
		LocalDateTime sutra = LocalDateTime.now().plusDays(1);
		Long idTipaPregleda = 0L;
		assertEquals(null, klinikaService.pretraziKlinike(sutra, idTipaPregleda));
	}
	
	//***pozitivni*** 
	
	@Test
	public void testPretraziKlinike5() {
		LocalDateTime datum = LocalDateTime.of(2020,3,25,0,0);
		Long idTipaPregleda = 1L;
		
		List<Klinika> klinike = klinikaService.pretraziKlinike(datum, idTipaPregleda); 
				
		assertThat(klinike).hasSize(2);
		assertEquals(klinike.get(0).getNaziv(),"klinika1");
		
		assertEquals(klinike.get(1).getNaziv(),"klinika3");
	}
	
	//***negativni***
	
	@Test
	public void testZakaziPregled1() {
		Long pacijentID = 0L; // ***
		Long lekarID = 1L;
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0);
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled2() {
		Long pacijentID = null; // ***
		Long lekarID = 1L; 
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0);
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled3() {
		Long pacijentID = 1L; 
		Long lekarID = 0L; // ***
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0);
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled4() {
		Long pacijentID = 1L; 
		Long lekarID = null; // ***
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0);
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled5() {
		Long pacijentID = 1L; 
		Long lekarID = 1L;
		LocalDateTime termin = null; // ***
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled6() {
		Long pacijentID = 1L; 
		Long lekarID = 1L;
		LocalDateTime termin = LocalDateTime.now().minusDays(1); // ***
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test(expected = NullPointerException.class)
	public void testZakaziPregled7() {
		Long pacijentID = 1L; 
		Long lekarID = 1000L; // ***
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0); 
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test(expected = NullPointerException.class)
	public void testZakaziPregled8() {
		Long pacijentID = 1000L; // ***
		Long lekarID = 1L;
		LocalDateTime termin = LocalDateTime.of(2020,3,24,0,0);
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	@Test
	public void testZakaziPregled9() {
		Long pacijentID = 1L; 
		Long lekarID = 1L;
		LocalDateTime termin = LocalDateTime.of(2020,5,13,10,0); // vec postoji u bazi
		assertEquals(false, pregledService.zakaziPregled(lekarID, termin, pacijentID));
	}
	
	//***pozitivni***
	
	@Test
	@Transactional
	@Rollback(true)
	public void testZakaziPregled10() {
		Long pacijentID = 1L; 
		Long lekarID = 1L;		
		LocalDateTime termin = LocalDateTime.of(2020,10,13,10,0); 
		Long size = (long) pregledService.findAll().size();
		assertEquals(true, pregledService.zakaziPregled(lekarID, termin, pacijentID));
				
		assertEquals(size + 1, pregledService.findAll().size());
	}
	
	//***negativni***
	@Test
	public void testPotvrdiIliOdbiPregled1() {
		Long pregledID = null; //////
		String odluka = "potvrdi";
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test
	public void testPotvrdiIliOdbiPregled2() {
		Long pregledID = 0L; //////
		String odluka = "potvrdi";
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test
	public void testPotvrdiIliOdbiPregled3() {
		Long pregledID = 1L; 
		String odluka = null; //////////
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test
	public void testPotvrdiIliOdbiPregled4() {
		Long pregledID = 1L; 
		String odluka = "asd";
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test(expected = NullPointerException.class)
	public void testPotvrdiIliOdbiPregled5() {
		Long pregledID = 1000L; //////
		String odluka = "potvrdi";
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test
	public void testPotvrdiIliOdbiPregled6() {
		Long pregledID = 17L; 
		String odluka = "potvrdi"; ////// vec je prihvacen
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	@Test
	public void testPotvrdiIliOdbiPregled7() {
		Long pregledID = 17L;; 
		String odluka = "odustani"; ////// vec je obrisan
		assertEquals(false, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
	}
	
	//***pozitivni***
	@Test
	@Transactional
	@Rollback(true)
	public void testPotvrdiIliOdbiPregledPOZ() {
		Long pregledID = 18L; 
		String odluka = "potvrdi";
		assertEquals(false, pregledService.findOne(pregledID).isPrihvacen());
		
		assertEquals(true, pregledService.potvrdiIliOdbiPregled(pregledID, odluka));
		
		assertEquals(true, pregledService.findOne(pregledID).isPrihvacen());
	}
	
	
	//***negativni***
	@Test
	public void upisiSalu1() {
		Long idPregleda = 11L; //////
		Long idSale = null;
		assertEquals(null, pregledService.upisiSalu(idPregleda, idSale));
	}
	@Test
	public void upisiSalu2() {
		Long idPregleda = null; //////
		Long idSale = 1L;
		assertEquals(null, pregledService.upisiSalu(idPregleda, idSale));
	}
	@Test
	public void upisiSalu3() {
		Long idPregleda = -1L; //////
		Long idSale = 1L;
		assertEquals(null, pregledService.upisiSalu(idPregleda, idSale));
	}
	@Test
	public void upisiSalu4() {
		Long idPregleda = 11L; //////
		Long idSale = -1L;
		assertEquals(null, pregledService.upisiSalu(idPregleda, idSale));
	}
	@Test(expected = NullPointerException.class)
	public void upisiSalu5() {
		Long idPregleda = 11L; //////
		Long idSale = 1500L;
		assertEquals("Sala ne postoji", pregledService.upisiSalu(idPregleda, idSale));
	}
	@Test(expected = NullPointerException.class)
	public void upisiSalu6() {
		Long idPregleda = 1500L; //////
		Long idSale = 1L;
		assertEquals("Pregled ne postoji", pregledService.upisiSalu(idPregleda, idSale));
	}
	
	//***pozitivni***
	@Test
	@Transactional
	@Rollback(true)
	public void upisiSaluPOZ() {
		Long idPregleda = 23L; //////
		Long idSale = 1L;
		assertEquals("Sve ok", pregledService.upisiSalu(idPregleda, idSale));
	
	}
	
	//***negativni***
	
	@Test
	public void prviSlobodanTermin1() {
		Long idPregleda = null; //////
		assertEquals(null, pregledService.prviSlobodanTermin(idPregleda));
	}
	
	@Test
	public void prviSlobodanTermin2() {
		Long idPregleda = 0L; //////
		assertEquals(null, pregledService.prviSlobodanTermin(idPregleda));
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void prviSlobodanTermin3() {
		Long idPregleda = 1500L; //////
		assertEquals(null, pregledService.prviSlobodanTermin(idPregleda));
	}
	
	//***pozitivni***
	@Test
	@Transactional
	@Rollback(true)
	public void prviSlobodanTerminPOZ() {
		Long idPregleda = 23L; //////
		assertEquals("2021-05-25 11:00", pregledService.prviSlobodanTermin(idPregleda));
	
	}
	
	//***negativni***
	
	@Test
	public void nemaSale1() {
		Long idPregleda = null; //////
		assertEquals(null, pregledService.nemaSale(idPregleda));
	}
	
	@Test
	public void nemaSale2() {
		Long idPregleda = 0L; //////
		assertEquals(null, pregledService.nemaSale(idPregleda));
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void nemaSale3() {
		Long idPregleda = 1500L; //////
		assertEquals(null, pregledService.nemaSale(idPregleda));
	}
	
	//***pozitivni***
		@Test
		@Transactional
		@Rollback(true)
		public void nemaSalePOZ() {
			Long idPregleda = 22L; //////
			assertEquals("Sve ok", pregledService.nemaSale(idPregleda));
		
		}
	
}
