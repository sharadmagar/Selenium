package ForAppTesting;
import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Common_Functions {

	private static WebDriver GetDriver(String Browser)
	{
		if(Browser=="FireFox")
		{
			WebDriver DriverFireFox = new FirefoxDriver();
			return DriverFireFox;
		}
		else if(Browser=="Chrome")
		{
			WebDriver DriverChrome = new ChromeDriver();
			return DriverChrome;
		}
		else
		{
			WebDriver DriverExplorer = new InternetExplorerDriver();
			return DriverExplorer;
		}
		
	}
	
	private static WebDriver driver = GetDriver("FireFox");
	
	public static WebElement FindElementBySingleTag(String FindElementBy, String ElementVal)
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
	
	public static String EnterTextBoxVal(String FindElementBy, String ElementVal, String Input)
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
	
	public static String ClickBtn(String FindElementBy, String ElementVal)
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
	
	public static String LauchWebLink(String Link, String ExpectedTitle)
	{
		driver.get(Link);
		String Title = driver.getTitle();
		if(ExpectedTitle.equals(Title))
		{
			return "True, WebPage Launched and Verified";
		}
		else if(Title==null)
		{
			return "WebElement not Found!";
		}
		else
		{
			return "Title not matched";
		}
	}
	
	public static String CloseBrowser()
	{
		driver.quit();
		return "True, Closed the browser";
	}
	
	public static String ISElementExist(String FindElementBy,String ElementVal)
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
	
	public static void SendKeys(WebElement Element, String Key)
	{
		Element.sendKeys(Key);
	}
	
	public static String ClickRadioChkBtn(String FindElementBy,String ElementVal)
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
	
	public static String SelectDropDownItem(String FindElementBy,String ElementVal, String Value, String SelectType)
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
	
	public static String DeselectDropDownItem(String FindElementBy,String ElementVal, String Value, String DeselectType)
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
	
	public static String GetTableVal(String FindElementBy, String ElementVal, String UniqueColumnName, String UniqueVal, String ColumnName)
	{
		int ColIndex = GetColumnIndexByVal(FindElementBy, ElementVal, ColumnName);
		
		int RowIndex = GetRowIndexByVal(FindElementBy, ElementVal, UniqueColumnName, UniqueVal);
		 
		String ActualVal = ElementVal+"/tr["+RowIndex+"]/td["+ColIndex+"]";
		WebElement Cell =  FindElementBySingleTag(FindElementBy,ActualVal);
		String Value = Cell.getText();
		if(Value==null || Value=="")
		{
			return null;
		}
		else
		{
			return Value;
		}
	}
	
	public static int GetColumnIndexByVal(String FindElementBy, String ElementVal, String ColumnName)
	{
		String NewElementVal = ElementVal+"/tr[1]/th";
		
		@SuppressWarnings("unchecked")
		List<WebElement> ColCount = (List<WebElement>) FindElementBySingleTag(FindElementBy,NewElementVal);
		int Ccount = ColCount.size();
		for(int j=0;j<Ccount;j++)
		{
			String NewVal = ElementVal+"/tr[1]/th["+j+"]";
			WebElement Cell =  FindElementBySingleTag(FindElementBy,NewVal);
			String Value = Cell.getText();
			if(ColumnName.equals(Value))
				return j;
			else
				return -1;
		}
		return -1;
	}
	
	
	public static int GetRowIndexByVal(String FindElementBy, String ElementVal, String ColumnName, String ColumnVal)
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
				return j;
			else
				return -1;
		}
		return -1;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
