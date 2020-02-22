package Dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Uploading {
public static void main(String[] args) throws InterruptedException {
		
		
		//System.setProperty("webdriver.gecko.driver","/home/teepti2705/Desktop/Web_Application/Selenium/geckodriver-v0.25.0-linux64/geckodriver");
		System.setProperty("webdriver.chrome.driver","/home/teepti2705/Desktop/Web_Application/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    WebDriverWait wait = new WebDriverWait(driver,20);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.dice.com/dashboard");
	    driver.findElement(By.linkText("Register")).click();
	    driver.findElement(By.id("fname")).sendKeys("Tanner");
	    driver.findElement(By.id("lname")).sendKeys("Smith779");
	    driver.findElement(By.id("email")).sendKeys("TannerSmith7792@gmail.com");
	    driver.findElement(By.id("password")).sendKeys("waheguru123");
	    driver.findElement(By.id("passwordConfirmation")).sendKeys("waheguru123");
	 
	    
	    Thread.sleep(3000);
	    js.executeScript("window.scrollBy(0,200)");
	    WebElement RegisterButton = driver.findElement(By.xpath("//*[@id ='people']//following::button"));
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", RegisterButton);
	    RegisterButton.click();
	    	
	   
	    
	    //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dice-login-customer-name")));
	   
	   
	    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("thirdPartyYes")))).click();
	   
	  
	    	WebElement elementUpload = driver.findElement(By.xpath("//input[@class = 'upload' and @type = 'file']"));
	    	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elementUpload);
	    	//wait.until(ExpectedConditions.visibilityOf(elementUpload));
	    	elementUpload.sendKeys("/home/teepti2705/Downloads/Tanner Wood.docx");
	    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("emp-type-btn")))).click();
	    	java.util.List<WebElement> allOptions = driver.findElements(By.xpath("//ul[@id='employment-types-dd']//li"));
	    	for(WebElement option : allOptions) {
	    		System.out.println(option.getText());
	    		//if(option.getText().toString() == "Part-time" || option.getText().toString() == "Contracts" || option.getText().toString() == "Third Party") {
	    		if("Part-time".equals(option.getText()) || "Contracts".equals(option.getText()) || "Third Party".equals(option.getText()) || "Contract".contains(option.getText())) {
	    			option.click();
	    		    Thread.sleep(3000);
	    		}
	    		
	    	}
	    	driver.findElement(By.xpath("//*[@class='container page-wrap']")).click();
	    	
	    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("work-authorization-btn")))).click();
	    	java.util.List<WebElement> Options = driver.findElements(By.xpath("//ul[@id='work-authorization-dd']//li"));
	   
	    	for(WebElement option : Options) {
	    		System.out.println(option.getText());
	    		if("US Citizen".equals(option.getText()))
	    			option.click();
	    	}
	    
	    	js.executeScript("window.scrollBy(0,1500)");
	    	driver.findElement(By.id("diceAlert")).click();
	    	driver.findElement(By.xpath("//*[@class = 'row submit-group']//child::div[1]/button[@type = 'submit']")).click();;
	    	driver.findElement(By.id("dice-login-customer-name")).click();
		    driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	    
	}

}
