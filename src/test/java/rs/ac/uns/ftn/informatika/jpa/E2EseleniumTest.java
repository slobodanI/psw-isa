//package rs.ac.uns.ftn.informatika.jpa;
//
//import static org.testng.AssertJUnit.assertEquals;
//
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class E2EseleniumTest {
//
//	private WebDriver browser;
//	
//	private SearchPage searchPage;
//	
//	@BeforeMethod
//    public void setUp() {
//        // instantiate chrome browser
//        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
//        browser = new ChromeDriver();
//
//        // instantiate firefox browser
////        System.setProperty("webdriver.gecko.driver","src/test/java/resources/geckodriver");
////        FirefoxOptions firefoxOptions = new FirefoxOptions();
////        firefoxOptions.setCapability("marionette",true);
////
////
////        browser = new FirefoxDriver(firefoxOptions);
//
//        //maximize window
//        browser.manage().window().maximize();
//
//        //navigate
//        browser.navigate().to("https://ruter.no/en/?fbclid=IwAR0trS_FoLyeE0D5yVE_e5LyEN-r2mRn5pSMZV1fBkBMsOK31sIql1uC978");
//
//        searchPage = PageFactory.initElements(browser, SearchPage.class);
//
//    }
//	
//	@Test
//    public void testZakaziPredefPregled() {
//
////        searchPage.ensureIsDisplayed();
//
////        searchPage.getBtnZakazi().click();
//
//  
//
//    }
//	
//}
