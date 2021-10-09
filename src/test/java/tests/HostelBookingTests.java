package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PropertyViewPage;
import pages.SearchResultPage;

public class HostelBookingTests extends TestBase{
	
	HomePage home;
	SearchResultPage search;
	PropertyViewPage property;
	
	@BeforeClass
    public void initPages() {
		home = new HomePage(driver());
		search = new SearchResultPage(driver());
		property = new PropertyViewPage(driver());
    }

	@Test(dataProvider="getData")
	public void HostelCheckOutTest(Map<String,String> data) {
		try {
			home.launchApp();
			home.setDestination(data.get("city"));
			home.setTravelDate(data.get("checkindate"), data.get("checkoutdate"));
			home.performHostelSeach();
			search.clickFirstProperty();
			//property.chooseRoomToBook();
			//System.out.println();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
