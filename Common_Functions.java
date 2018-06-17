package ForAppTesting;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common_Functions {

	public static WebDriver driver = new FirefoxDriver();
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
