package bootcamp_assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.net.HostSpecifier;


public class WebActions {
	
	  WebDriver driver;
	  HashMap<String, String> testdata = new HashMap<String, String>();
	  
	   
	  public void clickOn(String locator) throws InterruptedException {
		  
		  driver.findElement(By.xpath(locator)).click();
		  unconditionalWait(2);		  
		  
	  }
	 
	  public void sendKeys(String locator, String value) throws InterruptedException {
		  
		  driver.findElement(By.xpath(locator)).sendKeys(value);
		  unconditionalWait(1);		  
		  
	  }
	  
 
	  public void selectFromDropdown(String locator, String value) throws InterruptedException {
		  
		  Select option =new Select(driver.findElement(By.xpath(locator)));
		  option.selectByVisibleText(value);
		  //option.selectByValue(value);
		  unconditionalWait(1);		  
		  
	  }
	  
 
	  public void unconditionalWait(int i) throws InterruptedException {		  
		  	 
		  Thread.sleep(i*1000);				  
		  
	  }
	  
	  public void conditionalWait(int i, String locator) throws InterruptedException {		  
		  	 
		WebDriverWait wait =new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		  
	  }
	  
	  public String getText(String locator) throws InterruptedException {		  
		  	 
		  String text = driver.findElement(By.xpath(locator)).getText();
		  return text;
		  
	  }
	  
	  public void initiateBrowser(String browser) throws InterruptedException {		  
		  
		  if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "Drivers/InternetExplorerDriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("FF")) {
				System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
				driver = new ChromeDriver();
			}
		  
	  }
	  
	  //Excel data retrieval
	  
	  public HashMap<String, String> getData(String sheetName, String rowIdentifier) throws IOException{
		  
		  HashMap <String, String> fetchdata = new HashMap<String, String>();
		  String filepath = "C:/Users/kavyaka/workspace/FlightReservation/TestData/TestData.xlsx";
		  FileInputStream file = new FileInputStream(filepath);
		  XSSFWorkbook workbook = new XSSFWorkbook(file); 
		  XSSFSheet sheet = workbook.getSheetAt(0);
		  
		  int rowCount = sheet.getPhysicalNumberOfRows();
		  int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		  
		  for (int i=0; i<rowCount; i++){
			  if(sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(rowIdentifier)){
				  
				  for(int j=0; j<columnCount; j++){
					  
					  fetchdata.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());
					  System.out.println("Key"+ sheet.getRow(0).getCell(j).toString()+ "," + "value"+sheet.getRow(i).getCell(j).toString());
				  }
				  
				  break;				  
			  }
		  }
		  
		  file.close();
		  workbook.close();		  
		  return fetchdata;
	  }

}
