package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import core.Config;
import core.DriverFactory;
import core.ExcelDataProvider;
import core.ITestData;
import core.JSONDataProvider;

public class TestBase {

	private WebDriver driver;
	private DriverFactory df;
	
	@BeforeSuite
	public void initSuite() {
		Config.loadConfig(System.getenv("env"));
	}
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void initDriver() {
		df = new DriverFactory();
		driver = df.getDriver(System.getenv("browser"));
	}
	
	@AfterClass(alwaysRun=true)
	public void killDriver() {
		df.quitDriver();
	}
	
	protected WebDriver driver() {
		return driver;
	}
	
	@DataProvider
	public Object[][] getData(Method method) {
		
		ITestData dt = null;
		String envName = System.getenv("env").toUpperCase();
		String testCaseName = method.getName();
		
		try {
			if(System.getenv("ds").toUpperCase().equals("EXCEL")){
				String filePath = System.getProperty("user.dir")+"//src//test//resources//data//TestData.xlsx";	
				dt = new ExcelDataProvider(filePath,envName);
				
			}else if (System.getenv("ds").toUpperCase().equals("JSON")) {
				String filePath = System.getProperty("user.dir")
						+"//src//test//resources//data//data."+envName.toLowerCase()+".json";	
				dt = new JSONDataProvider(filePath);
			}
			
			List<HashMap<String,String>> finalData = dt.getAllData(testCaseName);
			
//			Object[][] dataObj = new Object[finalData.size()][1];
//			for(int i=0;i<finalData.size();i++) {
//				dataObj[i][0] = finalData.get(i);
//			}
			
			return createDataProvider(finalData);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	private Object[][] createDataProvider(Object dataSet){
		int rowNo = ((ArrayList)dataSet).size();
		Object[][] dataArray = new Object[rowNo][1];
		int dim = 0;
		for(int iRow=0;iRow<rowNo;iRow++) {
			dataArray[dim][0] = ((ArrayList)dataSet).get(iRow);
			dim++;
		}
		return dataArray;
	}
}
