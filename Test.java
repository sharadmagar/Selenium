//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ForAppTesting.Common_Functions;
public class Test 
{
	public static void main(String[] args) 
	{
        try 
        {  	
        	//Common_Functions.LauchWebLink("https://www.facebook.com","Facebook – log in or sign up");
        	Common_Functions.LauchWebLink("https://money.rediff.com/gainers/bsc/daily/groupa");
        	int i = Common_Functions.GetColumnIndexByVal("xpath","//*[@class=’dataTable’]/thead/tr/th","Company");
        	System.out.println("Test Case Passed="+i);
        	Common_Functions.EnterTextBoxVal("name", "email","sharadmagar450@gmail.com");
            WebElement Element = Common_Functions.FindElementBySingleTag("name", "email");
            Common_Functions.SendKeys(Element, "Keys.TAB");
            Common_Functions.EnterTextBoxVal("name", "pass","abcd");
            Common_Functions.ClickBtn("id","loginbutton");
            
            Thread.sleep(5000);
            String Title = Common_Functions.ISElementExist("id","login_link");
            
        	if(Title.contains("True"))
            {
            	System.out.println("Test Case Passed");
            }
            else
            {
            	System.out.println("Test Case Failed");
            }
		} 
        catch (InterruptedException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
        	Common_Functions.CloseBrowser();
        }
	}
}