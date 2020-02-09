package rs.ac.uns.ftn.informatika.jpa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	
	 private WebDriver driver;

	    @FindBy(xpath = "//*[@id=\"ul-listaPregleda\"]/li/p/button")
	    private WebElement btnZakazi;

	    


	    public SearchPage() {
	    }

	    public SearchPage(WebDriver driver) {
	        this.driver = driver;
	    }

//	    public void ensureIsDisplayed(){
//	        (new WebDriverWait(driver, 10))
//	                .until(ExpectedConditions.elementToBeClickable(btnZakazi)); // ovde je greska, treba cast?
//	    }



	    public WebElement getBtnZakazi() {
	        return btnZakazi;
	    }


	
}
