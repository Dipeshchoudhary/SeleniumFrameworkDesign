package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstatractComponent;

public class Cartpage extends AbstatractComponent {
	
	
	WebDriver driver;
	
	public Cartpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//li[@class=\"totalRow\"]/button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	
	
	public boolean verifyProductDisplay(String productName)
	{
		boolean match=productTitles.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
	} 
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
}
