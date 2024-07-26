package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cartpage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;
import rahulshettyacademy.pageobjects.landingPage;

public class submitOrderTest extends BaseTest {
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitorder(HashMap<String,String>input) throws IOException
	{
		// TODO Auto-generated method stub
		
			
		ProductCatelogue productcatelogue=lpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productcatelogue.getProductList();
		productcatelogue.addProduct(input.get("product"));
		Cartpage cartpage = productcatelogue.goToCartPage();
		
		 
		boolean match=cartpage.verifyProductDisplay(input.get("product"));
		
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage=cartpage.goToCheckout();
		checkoutPage.selecCountry("india");
		ConfirmationPage confirmationpage=checkoutPage.submitorder();
		
		
		//driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMsg=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 
		
	}
	
	
	//to verify ZARA COAT 3 is displaying in order page
	
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistoryTest()
	{
		ProductCatelogue productcatelogue=lpage.loginApplication("dipesh@gmail.com", "Pass@123");
		OrderPage orderpage=productcatelogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap <String,String> map=new HashMap<>();
		map.put("email", "dipesh@gmail.com");
		map.put("password", "Pass@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String>map1=new HashMap<>();
		map1.put("email", "dnaina@gmail.com");
		map1.put("password", "Pass@123");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String,String>>data = getjsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData1()
	{
		return new Object[][] {{"dipesh@gmail.com","Pass@123","ZARA COAT 3"},{"dnaina@gmail.com","Pass@123","ADIDAS ORIGINAL"}};
	}*/
	

}
