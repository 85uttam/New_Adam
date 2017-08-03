package base;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import keywords.Keywords;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jna.platform.unix.X11.Screen;

public class Base extends Keywords
{
	
	public static WebDriver driver;
	@BeforeTest
	public void getBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/Browser_Driver/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/Browser_Driver/chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.get("http://se3-rnd.adam.com/content.aspx?productId=127");
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}
//====================================================
	//Window maximise
	public static void maximizeWindow()
	{
		driver.manage().window().maximize();
	}
	
	
	
	
//=================================================
	//Resize window
		public static void getResizeWindow(int a, int b)
		{
			System.out.println(driver.manage().window().getSize());
			Dimension d = new Dimension(a, b);
			//Resize the current window to the given dimension
			driver.manage().window().setSize(d);
		}
	//=================================================
	//emplicit wait
	public static void  emplicitWait(long time)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public static WebElement waitForElement(WebDriver driver, long time,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
		return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	//==================================================================
	
	@Test
	public void searchBox() throws IOException, InterruptedException
	{
		driver.findElement(By.xpath(".//*[@id='hm-AdamSearchBox']/a[1]")).click();
		Alert alert = driver.switchTo().alert();
		String text=alert.getText();
		//Base.emplicitWait(100);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		System.out.println("Text present in alert box is="  + text);
		Assert.assertEquals("Please be more specific with your search.", text);
		alert.accept();
		driver.findElement(By.xpath(".//*[@id='txtsearch']")).sendKeys("Heart");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='hm-AdamSearchBox']/a[1]")).click();
		WebElement link = driver.findElement(By.xpath(".//*[@id='logoContainer-title']/a/img"));
		String url = link.getAttribute("href");
		System.out.println(url);
		Boolean iselementpresent = driver.findElements(By.xpath(".//*[@id='content']/article/p[2]/a")).size()!= 0;
		   if (iselementpresent == true)
		   {
		    System.out.print("\nRead Full Article is Present On The Page");
		   }
		   else
		   {
		    System.out.print("\nRead Full Article is Not Present On The Page\n");
		   }
		   
		   
		   
		   System.out.println("Resize window for iPad");
			Base.getResizeWindow(768,1024);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Base.getScreenShot("img3");
			
			driver.findElement(By.xpath(".//*[@id='200083']")).click();
			
			
			System.out.println("Resize window for iPhone");
			Base.getResizeWindow(320,568);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Base.getScreenShot("img4");
			Base.maximizeWindow();
			driver.findElement(By.xpath(".//*[@id='hm-AdamSearchBox']/a[1]")).click();
		
	}
	//======================================================================
	//Find CSS
	@Test
		public void readFontProperty(){
			  //Locate text string element to read It's font properties.
			  WebElement text = driver.findElement(By.xpath(".//*[@id='your-care-nav']/li[1]/a"));
			  
			  //Read font-size property and print It In console.
			  String fontSize = text.getCssValue("font-size");
			  System.out.println("Font Size -> "+fontSize);
			  
			  //Read color property and print It In console.
			  String fontColor = text.getCssValue("color");
			  System.out.println("Font Color -> "+fontColor);
			  
			  //Read font-family property and print It In console.
			  String fontFamily = text.getCssValue("font-family");
			  System.out.println("Font Family -> "+fontFamily);
			  
			  //Read text-align property and print It In console.
			  String fonttxtAlign = text.getCssValue("text-align");
			  System.out.println("Font Text Alignment -> "+fonttxtAlign);
			 
	}
	
	
	//===========================================================================
	//Count the number of links in webpage
	@Test
		public void findAllLinks ()  throws InterruptedException
		  {
		  try {
		    List<WebElement> no = driver.findElements(By.tagName("a"));
		    int nooflinks = no.size(); 
		    System.out.println(nooflinks);
		    for (WebElement pagelink : no)
		         {
		          String linktext = pagelink.getText();
		          String link = pagelink.getAttribute("href"); 
		          System.out.println(linktext+" ->");
		          System.out.println(link);
		          }
		   }catch (Exception e){
		             System.out.println("error "+e);
		         }
		           
		  }
		
		//==========================================================================================
		
		//Take the screenshot
	@Test
		public static String getScreenShot(String imageName) throws IOException
		{
			if(imageName.equals(""))
			{
				imageName="blank";
			}
			File image = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String imagelocation=System.getProperty("user.dir")+"\\src\\screenshot";
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat farmater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
			String actualImageName=imagelocation+imageName+"_"+farmater.format(calendar.getTime())+".png";
			File deatFile=new File(actualImageName);
			FileUtils.copyFile(image, deatFile);
			return actualImageName;
		}
		
		//=====================================================================================

		
		//ScreenShots
			public void screenShot() throws IOException, InterruptedException {
		    File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
		    File dest = new File("C:\\Users\\suneel.kumar\\workspace\\Adam\\src\\screenshot" + filename);
		    FileUtils.copyFile(scr, dest);
		}
		
		//=====================================================================================
		
		public void brokenLinks() throws IOException {

			
			  
			  //Find total No of links on page and print In console.
			  List<WebElement> total_links = driver.findElements(By.tagName("a"));
			  System.out.println("Total Number of links found on page = " + total_links.size());
			  
			  //for loop to open all links one by one to check response code.
			  boolean isValid = false;
			  for (int i = 0; i < total_links.size(); i++) {
			   String url = total_links.get(i).getAttribute("href");

			   if (url != null) {
			    
			    //Call getResponseCode function for each URL to check response code.
			    isValid = getResponseCode(url);
			    
			    //Print message based on value of isValid which Is returned by getResponseCode function.
			    if (isValid) {
			     System.out.println("Valid Link:" + url);
			     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
			     System.out.println();
			    } else {
			     System.out.println("Broken Link ------> " + url);
			     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
			     System.out.println();
			    }
			   } else {    
			    //If <a> tag do not contain href attribute and value then print this message
			    System.out.println("String null");
			    System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
			    System.out.println();
			    continue;
			   }
			  }
			  //driver.close();
			 }

			 //Function to get response code of link URL.
			 //Link URL Is valid If found response code = 200.
			 //Link URL Is Invalid If found response code = 404 or 505.
			 public static boolean getResponseCode(String chkurl) {
			  boolean validResponse = false;
			  try {   
			   //Get response code of URL
			   HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
			   int resp_Code = urlresp.getStatusLine().getStatusCode();
			   System.out.println("Response Code Is : "+resp_Code);
			   if ((resp_Code == 404) || (resp_Code == 505)) {
			    validResponse = false;
			   } else {
			    validResponse = true;
			   }
			  } catch (Exception e) {

			  }
			  return validResponse;
			 }

			 
			 
			//image is displyed
			 public void CheckImage() throws Exception 
			 {
					
					WebElement ImageFile = driver.findElement(By.xpath(".//*[@id='bg-img']"));
				        
				        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
				        if (!ImagePresent)
				        {
				             System.out.println("Hero Image not displayed.");
				             
				        }
				        else
				        {
				            System.out.println("Hero Image displayed.");
				            int imageWidth=ImageFile.getSize().getWidth();
				            System.out.println("Hero Image width is:-" +imageWidth+ "Pixels");
				            int imageHeight=ImageFile.getSize().getHeight();
				            System.out.println("Hero image Height is:-" +imageHeight+"Pixels");
				            Point point=ImageFile.getLocation();
				            System.out.println("X and Y Coordinates of Image:"+point);
				            
				        }
			 }
			 
			 
			
			 
			 
	

}
