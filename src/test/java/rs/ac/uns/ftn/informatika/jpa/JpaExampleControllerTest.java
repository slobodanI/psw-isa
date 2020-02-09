package rs.ac.uns.ftn.informatika.jpa;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import rs.ac.uns.ftn.informatika.jpa.dto.PretragaKlinikaDTO;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@TestPropertySource("classpath:test.properties") prekopirao sam iz application.prop u test.prop, zbog baze
public class JpaExampleControllerTest {
	
	private static final String URL_PREFIX_PREGLED = "/pregledi";
	private static final String URL_PREFIX_KLINIKA = "/klinika";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.
    			webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
    public void testPretraziKlinike() throws Exception {
    	
		
//		Long tipPregledaID = 1L;
		// ovo ne radi
//		LocalDateTime datum = LocalDateTime.of(2020, 4, 28, 0, 0);						
//		PretragaKlinikaDTO pretragaKlinikaDTO = new PretragaKlinikaDTO(datum, tipPregledaID);		
//		String json = TestUtil.json(pretragaKlinikaDTO);
		
		//ovo  radi
		String json = "{\"datum\" : \"2020-05-28T00:00\", \"tipPregleda\": \"1\"}";
		//method je post, 
		this.mockMvc.perform(post(URL_PREFIX_KLINIKA + "/pretraziKlinike")
			.contentType(contentType)
			.content(json))
	    	.andExpect(status().isOk()) 
	    	.andExpect(content().contentType(contentType))
	    	.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$.[*].naziv").value(hasItem("klinika1")))
			.andExpect(jsonPath("$.[*].naziv").value(hasItem("klinika3")));
    }
	// /zakaziPredefPregled/{pregledID}/pacijent/{pacijentID}
	
	@Test
	@Transactional
    @Rollback(true)
    public void testZakaziPredefPregled() throws Exception {

		this.mockMvc.perform(put(URL_PREFIX_PREGLED + "/zakaziPredefPregled/15/pacijent/3"))
	    	.andExpect(status().isOk());
	    	
    }
		
	@Test
	@Transactional
    @Rollback(true)
    public void testZakaziPregled() throws Exception {
		
		String json = "{\"termin\" : \"2020-05-28T09:00\", \"lekarID\": \"1\", \"pacijentID\": \"1\"}";
		
		this.mockMvc.perform(post(URL_PREFIX_PREGLED + "/zakaziPregled")
			.contentType(contentType)
			.content(json))
	    	.andExpect(status().isOk());
	    	
    }
	
	@Test
	@Transactional
    @Rollback(true)
    public void testPotvrdiIliOdbiPregled() throws Exception {	
		String json = "{\"pregledID\": \"18\", \"odluka\": \"potvrdi\"}";
		
		this.mockMvc.perform(put(URL_PREFIX_PREGLED + "/potvrdaPregleda")
			.contentType(contentType)
			.content(json))
	    	.andExpect(status().isOk());
	    	
    }
	
	@Test
	@Transactional
    @Rollback(true)
    public void nemaSlobodneSale() throws Exception {	

		
		this.mockMvc.perform(put(URL_PREFIX_PREGLED + "/nemaSlobodneSale/22")
			.contentType(MediaType.TEXT_PLAIN)
			.content("Sve ok"))
	    	.andExpect(status().isOk());
	    	
    }
	
	
}
