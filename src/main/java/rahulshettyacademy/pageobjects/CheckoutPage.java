package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstatractComponent;

public class CheckoutPage extends AbstatractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="input[placeholder*=\"Country\"]")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results=By.cssSelector(".list-group"); 
	
	public void selecCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		 
		waitForElementToAppear(By.cssSelector(".list-group"));
		
		
		selectCountry.click();
	}
	
	public ConfirmationPage submitorder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
