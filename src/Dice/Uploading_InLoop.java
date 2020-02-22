package Dice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Uploading_InLoop {
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
				driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    WebDriverWait wait = new WebDriverWait(driver,20);
			    JavascriptExecutor js = (JavascriptExecutor) driver;
		        driver.get("https://www.dice.com/dashboard");
			    driver.findElement(By.linkText("Register")).click();
			
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					
						
					driver.findElement(By.id("fname")).sendKeys(row.getCell(0).getStringCellValue());
				    driver.findElement(By.id("lname")).sendKeys(row.getCell(1).getStringCellValue());
				    driver.findElement(By.id("email")).sendKeys(row.getCell(2).getStringCellValue());
				    driver.findElement(By.id("password")).sendKeys(row.getCell(3).getStringCellValue());
				    driver.findElement(By.id("passwordConfirmation")).sendKeys(row.getCell(4).getStringCellValue());
				    break;
				    
			    
			
					
					
				}
				System.out.println("");
				Thread.sleep(3000);
			    js.executeScript("window.scrollBy(0,200)");
			    WebElement RegisterButton = driver.findElement(By.xpath("//*[@id ='people']//following::button"));
			    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", RegisterButton);
			    RegisterButton.click();
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
			    driver.quit();
				row = sheet.getRow(row.getRowNum() + 1);
			}
			file.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

}
