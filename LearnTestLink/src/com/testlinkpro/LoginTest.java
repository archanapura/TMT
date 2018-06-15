package com.testlinkpro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class LoginTest
{
	public static WebDriver dr;
	public static String DEV_KEY = "aa58f0e00301ab64f890172bf7ac74fc"; //Your API 
	public static String SERVER_URL = "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php"; //your testlink server url
	public static String PROJECT_NAME = "Demo"; 
	public static String PLAN_NAME = "Demoplan";
	public static String BUILD_NAME = "DemoBuild";
	public static void main(String[] args) throws TestLinkAPIException {
	String result = "";
	String exception = null;
	try{
	System.setProperty("webdriver.gecko.driver","C:\\Users\\TYSS\\Downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");
	dr = new FirefoxDriver();
	dr.get("https://gmail.com");
	System.out.println("HI");
	dr.manage().window().maximize();
	result = TestLinkAPIResults.TEST_PASSED;
	updateTestLinkResult("TC1-1", null, result);
	} 
	catch (Exception e){
	result = TestLinkAPIResults.TEST_FAILED;
	exception = e.getMessage();
	updateTestLinkResult("Test-1", exception, result);
	}
	try {
	dr.findElement(By.id("Email")).sendKeys("your gmail login id");
	Thread.sleep(2000);
	dr.findElement(By.id("next")).click();
	Thread.sleep(1000);
	dr.findElement(By.id("Passwd")).sendKeys("*********");
	Thread.sleep(1000);
	dr.findElement(By.id("signIn")).click();
	result = TestLinkAPIResults.TEST_PASSED;
	updateTestLinkResult("TC1-1", null, result);
	} 
	catch (Exception e) {
	result = TestLinkAPIResults.TEST_FAILED;
	exception = e.getMessage();
	updateTestLinkResult("TC1-1", exception, result);
	}
	}
	private static void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException{
	TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
	/*TestLinkAPIResults proj =  testlinkAPIClient.getProjects();
	proj.repo
	System.out.println(proj.toString());
	System.out.println(proj.size());
*/	testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME, testCase, BUILD_NAME, exception, result);
	}

}
