package Indeed;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Updating {
                 
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","/home/teepti2705/Desktop/Web_Application/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    WebDriverWait wait = new WebDriverWait(driver,20);
	    driver.manage().window().maximize();
	    driver.get("https://www.indeed.com");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,200)");
	    driver.findElement(By.xpath("//a[@class = 'jobsearch-TextLink-link']")).click();
	    driver.findElement(By.linkText("Sign in")).click();
	    driver.findElement(By.id("login-email-input")).sendKeys("priya.gulati@hotmailsus.net");
	    driver.findElement(By.id("login-password-input")).sendKeys("waheguru123");
	    driver.findElement(By.id("login-submit-button")).click();
	    driver.findElement(By.xpath("//*[@class = 'gnav-MenuToggle-label gnav-NavIcon']")).click();
	    driver.findElement(By.xpath("//a[@class ='gnav-AccountMenuLinks-link']")).click();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a.edit-button")))).click();
	    WebElement LastName = driver.findElement(By.xpath("//*[@id = 'input-undefined']//following::input"));
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", LastName);
	    String LastNameString = driver.findElement(By.id("input-undefined")).getAttribute("value");
	    char fl = LastNameString.charAt(0);
	    char ch;
	    if(Character.isLowerCase(fl)) 
	    	ch = Character.toUpperCase(fl);
	    else
	    	ch = Character.toLowerCase(fl);
	    LastNameString = LastNameString.replace(fl, ch);
	    driver.findElement(By.id("input-undefined")).clear();
	    driver.findElement(By.id("input-undefined")).sendKeys(LastNameString);
	    driver.findElement(By.xpath("//button[@class = 'icl-Button icl-Button--primary icl-Button--md toggleable-link-save toggleable-link']")).click();
	    js.executeScript("window.scrollBy(0,500)");
	    String LastUpdated = driver.findElement(By.xpath("//*[@class ='resume-last-updated']")).getText();
	    System.out.println(LastUpdated);
	    driver.quit();
	}
}
