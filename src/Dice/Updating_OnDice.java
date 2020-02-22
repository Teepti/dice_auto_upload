package Dice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Updating_OnDice {
	public static void main(String[] args) throws Exception {
		try {
		    FileInputStream file = new FileInputStream(new File("/home/teepti2705/Desktop/Web_Application/Eclipse/Update_Resume1/Resources.xlsx"));
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    XSSFSheet sheet = workbook.getSheetAt(0);
		    Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			System.out.println(row.getRowNum());
			System.setProperty("webdriver.chrome.driver","/home/teepti2705/Desktop/Web_Application/Selenium/chromedriver");

			WebDriver driver = new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver,20);
		    JavascriptExecutor js = ((JavascriptExecutor) driver);
		    driver.get("https://www.dice.com/dashboard/login");

		
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
				 driver.findElement(By.id("email")).sendKeys(row.getCell(2).getStringCellValue());
				 driver.findElement(By.id("password")).sendKeys(row.getCell(3).getStringCellValue());
				 break;
				
			
			    
		    
		
				
				
			}
			System.out.println("");
			 driver.findElement(By.xpath("//button[@type = 'submit']")).click();
			    //wait.until(ExpectedConditions.elementToBeClickable(By.id("dice-login-customer-name")));
			    
			    driver.get("https://www.dice.com/dashboard");
			    WebElement Search = driver.findElement(By.xpath("//*[@id = 'searchable-confirmation-dashboard']//child::div[@class = 'modal-content']//child::div[@class = 'modal-footer']//child::button[@id = 'searchablebtndashboard']"));
			    //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", Search);
			    
			    wait.until(ExpectedConditions.visibilityOf(Search)).click();
			    try {
			    WebElement SurveyLogo = driver.findElement(By.xpath("//iframe[@title = 'Usabilla Feedback Form']"));
			    if(SurveyLogo.isDisplayed()) {
			    	driver.switchTo().frame(SurveyLogo);
			    	driver.findElement(By.id("button")).click();
			    }
			    }catch (Exception E) {
			    	//E.printStackTrace();
			    	System.out.println("Survey Logo is not Present");
			    }
			    
			    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("editProfile")))).click();
			    js.executeScript("window.scrollBy(0,100)");
			    String value = driver.findElement(By.id("contactLastNameInput")).getAttribute("value");
			    System.out.println(value);
			    char fl = value.charAt(0);
			    char ch;
			    if(Character.isLowerCase(fl)) 
			    	ch = Character.toUpperCase(fl);
			    else
			    	ch = Character.toLowerCase(fl);
			    String LastName = value.replace(fl, ch);
			    System.out.println(LastName);
			    driver.findElement(By.id("contactLastNameInput")).clear();
			    
			    
			    driver.findElement(By.id("contactLastNameInput")).sendKeys(LastName);
			    driver.findElement(By.id("profilePicSecId")).click();
			    WebElement SaveButton = driver.findElement(By.xpath("//*[@class = 'btn btn-default btn-lg pull-right hidden-xs hidden-sm ng-scope']"));
			    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", SaveButton);
			    SaveButton.click();
			    Thread.sleep(1000);
		        WebElement LastModifiedDate = driver.findElement(By.xpath("//h3[@class ='ng-scope ng-binding']"));
		        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", LastModifiedDate);
		        System.out.println(LastModifiedDate.getText());
		        String ModifiedDate = LastModifiedDate.getText().substring(13, LastModifiedDate.getText().length());
		        System.out.println(ModifiedDate);
		        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		        Date Updated = formatter.parse(ModifiedDate);
		        System.out.println(Updated);
		        
		        driver.findElement(By.id("dice-login-customer-name")).click();
			    driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		        driver.quit();
			
			
			  
	    
	    	
	    
	    	
			row = sheet.getRow(row.getRowNum() + 1);
		}
		file.close();
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
}
		
		
		

	   
	   




