package ForAppTesting;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Common_Functions {

	private static WebDriver GetDriver(String Browser)
	{
		try
		{
			if (Browser == "FireFox")
			{
				System.setProperty("webdriver.firefox.marionette", "C:\\Selenium\\geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				profile.setAcceptUntrustedCertificates(false);
				profile.setAssumeUntrustedCertificateIssuer(true);
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.dir", "C:\\Downloads");
				profile.setPreference("browser.download.downloadDir", "C:\\Downloads");
				profile.setPreference("browser.download.defaultFolder", "C:\\Downloads");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/anytext ,text/plain,text/html,application/plain");
				dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, profile);

				WebDriver DriverFireFox = new FirefoxDriver(dc);
				DriverFireFox.manage().window().maximize();
				return DriverFireFox;
			}
			else if (Browser == "Chrome") 
			{
				DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
				handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver.exe");
				WebDriver DriverChrome = new ChromeDriver();
				DriverChrome.manage().window().maximize();
				return DriverChrome;
			}
			else if (Browser == "IE")
			{
				System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				WebDriver DriverExplorer = new InternetExplorerDriver(capabilities);
				DriverExplorer.manage().window().maximize();
				return DriverExplorer;
			}
			else
			{
				return null;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return null;
		}	
	}
	
	private static WebDriver driver = GetDriver("Chrome");
	
	public static WebElement FindElementBySingleTag(String FindElementBy, String ElementVal)
	{
		try
		{
			final WebElement Element;
			if(FindElementBy=="name")
			{
				Element = driver.findElement(By.name(ElementVal));
				return Element;
			}
			if(FindElementBy=="id")
			{
				Element = driver.findElement(By.id(ElementVal));
				return Element;
			}
			if(FindElementBy=="class")
			{
				Element = driver.findElement(By.className(ElementVal));
				return Element;
			}
			if(FindElementBy=="xpath")
			{
				Element = driver.findElement(By.xpath(ElementVal));
				return Element;
			}
			else
			{
				return null;
			}
		}
		
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return null;
		}
	}
	
	public static String EnterTextBoxVal(String FindElementBy, String ElementVal, String Input)
	{
		try
		{
			WebElement Text = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Text==null)
			{
				return "WebElement not Found!";
			}
			else
			{
				Text.sendKeys(Input);
				return "True, Value Set to TextBox= "+ Input;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
		
	}
	
	public static String ClickBtn(String FindElementBy, String ElementVal)
	{
		try
		{
			WebElement Btn = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Btn==null)
			{
				return "WebElement not Found!";
			}
			else
			{
				Btn.click();
				return "True, Clicked on Btn "+ ElementVal;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
		
	}
	
	public static String LauchWebLink(String Link)
	{
		try
		{
			driver.get(Link);
			String Title = driver.getTitle();
			if(Title!=null)
			{
				return "True, WebPage Launched and Verified";
			}
			else
			{
				return "WebElement not Found!";
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
	}
	
	public static String CloseBrowser()
	{
		driver.quit();
		return "True, Closed the browser";
	}
	
	public static String ISElementExist(String FindElementBy,String ElementVal)
	{
		try
		{
			WebElement Element = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Element.isDisplayed())
			{
				return "True, Element is Visible in Web Application";
			}
			else
			{
				return "False, Element is Not Visible in Web Application";
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
		
	}
	
	public static void SendKeys(WebElement Element, String Key)
	{
		Element.sendKeys(Key);
	}
	
	public static String ClickRadioChkBtn(String FindElementBy,String ElementVal)
	{
		try
		{
			WebElement Element = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Element==null)
			{
				return "False, WebElement not found";
			}
			else
			{
				Element.click();
				return "True, Clicked on Btn "+ ElementVal;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
		
	}
	
	public static String SelectDropDownItem(String FindElementBy,String ElementVal, String Value, String SelectType)
	{
		try 
		{
			WebElement Element = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Element==null)
			{
				return "False, WebElement not found";
			}
			else
			{
				Select DropListItem = new Select(Element);
				if(SelectType=="Text")
				{
					DropListItem.selectByVisibleText(Value);
				}
				else if(SelectType=="Index")
				{
					int val = Integer.parseInt(Value);
					DropListItem.selectByIndex(val);
				}
				else if(SelectType=="Value")
				{
					DropListItem.selectByValue(Value);
				}
				
				return "True, Item Selected from list is "+ Value;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
				
	}
	
	public static String DeselectDropDownItem(String FindElementBy,String ElementVal, String Value, String DeselectType)
	{
		try
		{
			WebElement Element = FindElementBySingleTag(FindElementBy,ElementVal);
			if(Element==null)
			{
				return "False, WebElement not found";
			}
			else
			{
				Select DropListItem = new Select(Element);
				if(DeselectType=="Text")
				{
					DropListItem.deselectByVisibleText(Value);
				}
				else if(DeselectType=="Index")
				{
					int val = Integer.parseInt(Value);
					DropListItem.deselectByIndex(val);
				}
				else if(DeselectType=="Value")
				{
					DropListItem.deselectByValue(Value);
				}
				else
				{
					DropListItem.deselectAll();
				}
				
				return "True, Item Deselected from list is "+ Value;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
	}
	
	public static String GetTableVal(String FindElementBy, String ElementVal, String UniqueColumnName, String UniqueVal, String ColumnName)
	{
		try
		{
			int ColIndex = GetColumnIndexByVal(FindElementBy, ElementVal, ColumnName);
			
			int RowIndex = GetRowIndexByVal(FindElementBy, ElementVal, UniqueColumnName, UniqueVal);
			 
			String ActualVal = ElementVal+"/tr["+RowIndex+"]/td["+ColIndex+"]";
			WebElement Cell =  FindElementBySingleTag(FindElementBy,ActualVal);
			String Value = Cell.getText();
			if(Value==null || Value=="")
			{
				System.out.println("Cell Value not Found");
				return null;
			}
			else
			{
				System.out.println("Cell Value Found = "+ Value);
				return Value;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return ee.toString();
		}
	}
	
	public static int GetColumnIndexByVal(String FindElementBy, String ElementVal, String ColumnName)
	{
		try
		{
			String NewElementVal = ElementVal;
			
			List<WebElement> ColCount = driver.findElements(By.xpath(NewElementVal));
			int Ccount = ColCount.size();
			for(int j=0;j<Ccount;j++)
			{
				String NewVal = ElementVal+"["+j+"]";
				WebElement Cell =  FindElementBySingleTag(FindElementBy,NewVal);
				String Value = Cell.getText();
				if(ColumnName.equals(Value))
				{
					System.out.println("Col Index = "+j);
					return j;
				}
				else
				{
					System.out.println("Col Index = -1");
					return -1;
				}
			}
			return -1;
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return -1;
		}
	}
	
	public static int GetRowIndexByVal(String FindElementBy, String ElementVal, String ColumnName, String ColumnVal)
	{
		try
		{
			String NewElementVal = ElementVal+"/tr";
			
			@SuppressWarnings("unchecked")
			List<WebElement> RowCount = (List<WebElement>) FindElementBySingleTag(FindElementBy,NewElementVal);
			int Rcount = RowCount.size();
			
			int Colindedx = GetColumnIndexByVal(FindElementBy, ElementVal, ColumnName);
			for(int j=0;j<Rcount;j++)
			{
				String NewVal = ElementVal+"/tr["+j+"1]/th["+Colindedx+"]";
				WebElement Cell =  FindElementBySingleTag(FindElementBy,NewVal);
				String Value = Cell.getText();
				if(NewElementVal.equals(Value))
				{
					System.out.println("Row Index = "+j);
					return j;
				}
				else
				{
					System.out.println("Row Index = -1");
					return -1;
				}
			}
			return -1;
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
			return -1;
		}
	}

	public static void Wait_In_Sec(String WaitType, int Timeinsec, String XpathVal)
	{
		try
		{
			if(WaitType=="Implicit")
			{
				driver.manage().timeouts().implicitlyWait(Timeinsec, TimeUnit.SECONDS);
			}
			else if(WaitType=="Explicit")
			{
				WebDriverWait wait=new WebDriverWait(driver, Timeinsec);
				WebElement WaitforElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathVal)));
				if(WaitforElement.isDisplayed())
				{
					System.out.println("Web Element Loaded successfully");
				}
				else
				{
					System.out.println("Web Element not Loaded successfully");
				}
			}
			else if(WaitType=="Fluent")
			{
				
			}
			else
			{
				System.out.println("Wait Type not Correct");
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
		}
		
	}
	
	public static void AlertHandle(String AlertType, String Action)
	{
		try
		{
			if(AlertType=="Simple")
			{
				Alert simpleAlert = driver.switchTo().alert();
				String alertText = simpleAlert.getText();
				System.out.println("Alert text is " + alertText);
				simpleAlert.accept();
			}
			else if(AlertType=="Confirmation")
			{
				if(Action=="Dissmiss")
				{
					Alert confirmationAlert = driver.switchTo().alert();
					String alertText = confirmationAlert.getText();
					System.out.println("Alert text is " + alertText);
					confirmationAlert.dismiss();
				}
				else
				{
					Alert confirmationAlert = driver.switchTo().alert();
					String alertText = confirmationAlert.getText();
					System.out.println("Alert text is " + alertText);
					confirmationAlert.accept();
				}
			}
			else if(AlertType=="Prompt") 
			{
				Alert promptAlert  = driver.switchTo().alert();
				String alertText = promptAlert .getText();
				System.out.println("Alert text is " + alertText);
				//Send some text to the alert
				promptAlert .sendKeys("Accepting the alert");
				Thread.sleep(4000); //This sleep is not necessary, just for demonstration
				promptAlert .accept();
			}
			else
			{
				System.out.println("Alert Not Fount");
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
		}
	}
	
	public static void DragandDropWebElement(String FromFindElementBy, String FromElementVal, String ToFindElementBy,String ToElementVal)
	{
		try
		{
			WebElement From = FindElementBySingleTag(FromFindElementBy,FromElementVal);
			 
			WebElement To = FindElementBySingleTag(ToFindElementBy,ToElementVal);
			 
			Actions builder = new Actions(driver);
			 
			Action dragAndDrop = builder.clickAndHold(From)
			 
			.moveToElement(To)
			 
			.release(To)
			 
			.build();
			 
			dragAndDrop.perform();
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
		}
	}
	
	public static void MouseHoverAction(String FindElementBy,String ElementVal)
	{
		try
		{
			WebElement element = FindElementBySingleTag(FindElementBy,ElementVal);
			 
	        Actions action = new Actions(driver);
	 
	        action.moveToElement(element).build().perform();
	        System.out.println("Mouse Hover Action Successfully Performed");
		}
		catch(Exception ee)
		{
			System.out.println("Exception Occured "+ee.toString());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
