package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cartpage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;
import rahulshettyacademy.pageobjects.landingPage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidaiton() throws IOException,InterruptedException
	{
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";	
		lpage.loginApplication("dnaina@gmail.co", "Pass@123");
		
		Assert.assertEquals("Incorrect email password.", lpage.getErrorMessage());
		
	}

	@Test
	public void ProductErrorValidaiton() throws IOException
	{
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";	
		ProductCatelogue productcatelogue=lpage.loginApplication("dbhargavi@gmail.com", "Pass@123");
		List<WebElement>products=productcatelogue.getProductList();
		productcatelogue.addProduct(productName);
		Cartpage cartpage = productcatelogue.goToCartPage();
		
		 
		boolean match=cartpage.verifyProductDisplay("ZARA COAT 33");
		
		Assert.assertFalse(match);
		
		 
		
	}
}
