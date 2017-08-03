package keywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_001 {

	public static WebDriver driver;
 public static void brokenLinks() throws IOException {

	
  
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
  driver.close();
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
}