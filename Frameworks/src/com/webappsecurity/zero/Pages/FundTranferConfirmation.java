package com.webappsecurity.zero.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundTranferConfirmation {

	@FindBy(css="#transfer_funds_content > div > div > div.alert.alert-success")
	private WebElement fundTransferConfirmationBox;

	@FindBy(css="#settingsBox > ul > li:nth-child(3) > a")
	private WebElement username;
	
	@FindBy(id="logout_link")
	private WebElement logout;
	
	public String getConfText(){
		String confMsg=fundTransferConfirmationBox.getText();
		return confMsg ;
	}
	public FundTranferConfirmation(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void LogoutFromApp(){
		username.click();
		logout.click();
		
	}
}
