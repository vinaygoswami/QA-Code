import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class automationcode {

	
	public static int timeout=30; //Waiting time to load page
	
	public static void main(String[] args) throws InterruptedException {
		
	System.setProperty("webdriver.chrome.driver","D:\\Auto\\Chromedriver\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	login(driver);
		
		 			
    //Verify all pages are accessable or not
		
	driver.findElement(By.id("menu")).click();        
	String[] link={"link1","link2","link3","link4","link5"};
	String[] Message={"Link1 loaded successfully",
			          "Link2 loaded successfully",
			          "Link3 loaded successfullu",
			          "Link4 loaded successfully",
			          "Link5 loaded successfully",
			          };
		
		
	for(int i=0;i<link.length;i++)
	 {
	new WebDriverWait(driver,timeout).until(ExpectedConditions. presenceOfElementLocated(By.id("menu")));
	driver.findElement(By.id("menu")).click();
	Thread.sleep(2000);
	new WebDriverWait(driver,timeout).until(ExpectedConditions. presenceOfElementLocated(By.linkText(link[i])));
	driver.findElement(By.linkText(link[i])).click();
	System.out.println(Message[i]);
	  		
	 }
	   
	// To Handle Alert Pop-up
	String alertMessage = "";
	driver.findElement(By.id("id-one")).click();
	alertMessage = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	System.out.println(alertMessage);
	     
	// To handle drop-down
	Select drpCountry = new Select(driver.findElement(By.name("country")));
	drpCountry.selectByVisibleText("INDIA");
	Select drpCountry1 = new Select(driver.findElement(By.name("country")));
	drpCountry1.selectByValue("123");
	Select drpCountry2 = new Select(driver.findElement(By.name("country")));
	drpCountry2.selectByIndex(1);
	     
	// To upload File
	driver.findElement(By.id("id-test")).sendKeys("C:\\Users\\vinay\\Desktop\\test.jpg");  
	   
	//Call Drag and Drop function
	String a="//*[@id='test-id1']/li[3]";         //a use for FROM drag&drop
    String b="//*[@id='test-id2']";               //b use for To drag&drop
    DandD(a,b,driver);
	   
    //To Enter data in iframe like CKEditior
  	driver.findElement(By.xpath("Test-xpath")).click();  //Click on Edit button
	WebElement ipframe = driver.findElement(By.className("cke_wysiwyg_frame"));
   	driver.switchTo().frame(ipframe);
	WebElement pdescription = driver.findElement(By.cssSelector("body"));
	((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = 'Test Content'", pdescription);
	driver.switchTo().defaultContent();
	driver.findElement(By.id("btnSubmit")).click(); //Click on Submit button
	   
	 }
	
	
	
public static void login(WebDriver driver) throws InterruptedException {
		
	
	driver.get("URL");                //Enter URL of webapplication
	driver.manage().window().maximize();
	
	//Type valid credential
	int timeout=30; //Waiting time to load page
	driver.findElement(By.name("email")).sendKeys("test@email.com"); 
    driver.findElement(By.name("password")).sendKeys("Testpassword");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	
	//Verify Successfully Login
	
	String expectedTitle = "Testtitle";
	String actualTitle = "";
	actualTitle = driver.getTitle();
 	
	if (actualTitle.contentEquals(expectedTitle)){
    System.out.println("Test Passed! Successfully Sign In");
     } else {
        System.out.println("Test Failed");
     }
	
    Thread.sleep(3000);
    String myurl= driver.getCurrentUrl();
	System.out.println("URL:" +myurl);
	Thread.sleep(2000);
		
	}

    //Drag&Drop function
public static void DandD(String a, String b,WebDriver driver) {
		
    driver.findElement(By.xpath(a)).click(); 
    WebElement from=driver.findElement(By.xpath(a));
    WebElement to=driver.findElement(By.xpath(b));
       
    Actions builder=new Actions(driver);
    builder.dragAndDrop(from, to).perform();
    }

}
