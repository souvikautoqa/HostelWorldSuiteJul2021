package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.PropertyViewPage;
import pages.SearchResultPage;

public class HostelSearchTests extends TestBase{
	
	HomePage home;
	
	
	@BeforeClass
    public void initPages() {
		home = new HomePage(driver());
		
    }
	
	@Test
	public void checkHostels() {
		try {
			home.launchApp();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

