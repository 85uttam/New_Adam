package scripts;
import org.openqa.selenium.WebDriver;




import base.Base;

public class TestCases
{


	
	WebDriver driver;
	
	public static void main(String[] args) throws Exception
	{
		
		Base obj=new Base();
		//Keywords keyword=new Keywords();
		obj.getBrowser("Firefox");
	
		obj.CheckImage();
		
	
//		obj.screenShot();
//		obj.findAllLinks();
//		System.out.println("CSS of landing page links");
//		obj.readFontProperty();
//		Base.emplicitWait(1000);
//		
//		System.out.println("Resize window for iPad");
//		Base.getResizeWindow(768,1024);
//		Base.getScreenShot("img1");
//		
//		System.out.println("Resize window for iPhone");
//		Base.getResizeWindow(320,568);
//		Base.getScreenShot("img2");
//		
//		Base.maximizeWindow();
		obj.searchBox();
//		obj.brokenLinks();
		obj.closeBrowser();
	}

}
