package bootcamp_assignment;

import org.testng.annotations.Test;

//import cucumber.api.java.en.When;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class Login_TestNG extends WebActions {

	// OBJECT LOCATORS

	// LOGIN SCREEN

	public String Home_Link = "//a[contains(text(), 'Home')]";
	public String Signon_Link = "//a[contains(text(), 'SIGN-ON')]";
	public String Username = "//input[@name='userName']";
	public String Password = "//input[@name='password']";
	public String Submit_Button = "//input[@name='login']";

	// FLIGHT FINDER SCREEN

	public String Trip_Oneway = "//input[@value='" + testdata.get("Trip_Type") + "']";
	public String Passengers_Count = "//select[@name='passCount']";
	public String Departing_From = "//select[@name='fromPort']";
	public String From_Month = "//select[@name='fromMonth']";
	public String From_Day = "//select[@name='fromDay']";
	public String Arriving_In = "//select[@name='toPort']";
	public String Returning_Month = "//select[@name='toMonth']";
	public String Returning_Day = "//select[@name='toDay']";
	public String Service_Class = "//input[@value='" + testdata.get("Service_Class") + "']";
	public String Airline = "//select[@name='airline']";

	public String Continue_FlightDetails = "//input[@name='findFlights']";

	// SELECT FLIGHT SCREEN

	public String Continue_Reserveflights = "//input[@name='reserveFlights']";

	// BOOK A FLIGHT SCREEN

	public String First_Name = "//input[@name='passFirst0']";
	public String Last_Name = "//input[@name='passLast0']";
	public String Credit_Number = "//input[@name='creditnumber']";
	public String Securepurchase_Button = "//input[@name='buyFlights']";

	// FLIGHT CONFIRMATION SCREEN

	public String Confirmation_Ref = "//table[@border='0'][@bgcolor='ffffff']/tbody/tr[1]/td/table/tbody/tr/td/b/font/font/b/font";
	public String SIGN_OFF = "//a[contains(text(), 'SIGN_OFF']";

	@Test
	//@When("^Validate help icons$")
	public void validateIcons() throws Throwable {
		
		try{
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();

		
		driver.get("https://www.westpac.co.nz/kiwisaver/calculators/kiwisaver-calculator/");
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@src, '/calculator-widgets/kiwisaver-calculator/')]")));
//we = wait.equals(util)
		driver.switchTo().frame(we);
		Thread.sleep(1000);
		Point Textboxlocation =driver.findElement(By.xpath("//div[@model='ctrl.data.CurrentAge']/descendant::input")).getLocation();
		driver.findElement(By.xpath("//div[@help-id='CurrentAge']/descendant::i")).click();
		Thread.sleep(3000);
		Point msglocation =driver.findElement(By.xpath("//p[contains(text(), 'This calculator has an age limit')]")).getLocation();
		
		System.out.println("Text Box location is"+Textboxlocation);
		System.out.println("Error msg Location is"+ msglocation);
		
		System.out.println(driver.findElements(By.xpath("//div[@cl-help-toggle='']/descendant::span[contains(text(), 'View help for this field')]/preceding-sibling::i[@class='icon']")).size());
		int size = driver.findElements(By.xpath("//div[@cl-help-toggle='']/descendant::span[contains(text(), 'View help for this field')]/preceding-sibling::i[@class='icon']")).size();
		//List<WebElement> weblist = driver.findElements(By.xpath("//div[@cl-help-toggle='']/descendant::span[contains(text(), 'View help for this field')]/preceding-sibling::i[@class='icon']"));
		
		List<WebElement> weblist = driver.findElements(By.xpath("//i[@class='icon']/ancestor::div[@cl-help-toggle='']"));
		for(int i=0; i< size; i++){
			String field = weblist.get(i).getAttribute("help-id");
			//String field = driver.findElement(By.xpath("//i[@class='icon']/ancestor::div[@cl-help-toggle='']")).getAttribute("help-id");
			System.out.println(field);
		}
		
		System.out.println(driver.findElement(By.xpath("//div[@help-id='Results']/descendant::i[@class='icon']")).isDisplayed());
		driver.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	//@Test
	public void Login() throws InterruptedException {

		driver.get("http://newtours.demoaut.com/mercurysignon.php");
		conditionalWait(15, Username);
		clickOn(Home_Link);
		clickOn(Signon_Link);
		// System.out.println(testdata.get("Username"));
		sendKeys(Username, testdata.get("Username"));
		sendKeys(Password, testdata.get("Password"));
		clickOn(Submit_Button);

	}

	//@Test(priority = 1)
	public void flightDetails() throws InterruptedException {
		System.out.println("I am in method 2" + testdata.get("Trip_Type"));
		conditionalWait(45, Passengers_Count);
		driver.findElement(By.xpath("//input[@value='" + testdata.get("Trip_Type") + "']")).click();
		// clickOn(Trip_Oneway);
		selectFromDropdown(Passengers_Count, testdata.get("Passengers_Count"));
		selectFromDropdown(Departing_From, testdata.get("Departing_From"));
		selectFromDropdown(From_Month, testdata.get("From_Month"));
		selectFromDropdown(From_Day, testdata.get("From_Day"));
		selectFromDropdown(Arriving_In, testdata.get("Arriving_In"));
		selectFromDropdown(Returning_Month, testdata.get("Returning_Month"));
		selectFromDropdown(Returning_Day, testdata.get("Returning_Day"));
		driver.findElement(By.xpath("//input[@value='" + testdata.get("Service_Class") + "']")).click();
		// clickOn(Service_Class);
		selectFromDropdown(Airline, testdata.get("Airline"));

		clickOn(Continue_FlightDetails);

	}

	//@Test(priority = 2)
	public void selectFlight() throws InterruptedException {
		conditionalWait(10, Continue_Reserveflights);
		clickOn(Continue_Reserveflights);

	}

	//@Test(priority = 3)
	public void bookFlight() throws InterruptedException {

		conditionalWait(10, First_Name);
		sendKeys(First_Name, testdata.get("First_Name"));
		sendKeys(Last_Name, testdata.get("Last_Name"));
		sendKeys(Credit_Number, testdata.get("Credit_Number"));
		clickOn(Securepurchase_Button);

	}

	//@Test(priority = 4)
	public void flightConfirmation() throws InterruptedException {
		//conditionalWait(10, First_Name);
		//String text = getText(Confirmation_Ref);
		//System.out.println("Flight Confirmation Reference :" + text);
		clickOn(SIGN_OFF);

	}

	//@BeforeSuite
	public void beforeClass() throws IOException, InterruptedException {

		testdata = getData("DataSheet", "FlightDetails");
		initiateBrowser(testdata.get("browser"));

	}

	 //@AfterSuite
	public void afterClass() {

		driver.close();
	}

}

