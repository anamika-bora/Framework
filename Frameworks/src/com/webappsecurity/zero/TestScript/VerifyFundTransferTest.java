package com.webappsecurity.zero.TestScript;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.webappsecurity.zero.Pages.AccountSummaryPage;
import com.webappsecurity.zero.Pages.FundTranferConfirmation;
import com.webappsecurity.zero.Pages.FundTransfer;
import com.webappsecurity.zero.Pages.FundTransferVerify;
import com.webappsecurity.zero.Pages.Login;

import utils.GenericMethods;

public class VerifyFundTransferTest {

	WebDriver driver;
	@BeforeTest
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://zero.webappsecurity.com/login.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void verifyFundTransfer() throws IOException{
		Login lp=new Login(driver);
		AccountSummaryPage acc=new AccountSummaryPage(driver);
		FundTransfer ft=new FundTransfer(driver);
		FundTransferVerify ftv=new FundTransferVerify(driver);
		FundTranferConfirmation ftc=new FundTranferConfirmation(driver);

		String[][] data = GenericMethods.getData("D:\\Development\\learning-files\\workspace_anamika\\Frameworks\\TestData.xlsx","sheet1");
		for(int i=1;i<data.length;i++)
		{
			lp.applicationLogin(data[i][0],data[i][1]);
			acc.clickFundTransfer();
			ft.fundTransfer(data[i][2],data[i][3]);
			ftv.clickSubmit();
			String actualMsg=ftc.getConfText();
			String expectedMsg=data[i][4];
			Assert.assertEquals(actualMsg, expectedMsg);
			ftc.LogoutFromApp();
			driver.navigate().to("http://zero.webappsecurity.com/login.html");
		}
	}

	@AfterTest
	public void closeApplication() {
		driver.close();
	}
}
