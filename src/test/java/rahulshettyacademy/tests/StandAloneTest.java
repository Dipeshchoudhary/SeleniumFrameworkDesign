package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.landingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		landingPage lpage=new landingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("dipesh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pass@123");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"mb-3\"]")));
		
		
		List<WebElement> products = driver.findElements(By.cssSelector("div[class*=\"mb-3\"]"));
		
		WebElement prod=products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		//.card-body button:last-of-type
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		 
		driver.findElement(By.cssSelector("button[routerlink*=\"cart\"]")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(cartproduct ->
				cartproduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		
		driver.findElement(By.xpath("//li[@class=\"totalRow\"]/button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder*=\"Country\"]")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		
		driver.findElement(By.xpath("(//button[contains(@class,\"ta-item\")])[2]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		
		String confirmMsg=driver.findElement(By.cssSelector("hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
