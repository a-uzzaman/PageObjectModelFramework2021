package PageObjects;

import Base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPageObject extends Config{


    public LoginPageObject(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //Username textbox
    @FindBy(how= How.ID, using="txtUsername")
    public WebElement userName;

    @FindBy(how=How.ID, using="txtPassword")
    public WebElement passWord;

    @FindBy(how=How.ID, using="btnLogin")
    public WebElement loginButton;

    @FindBy(how=How.ID, using= "spanMessage" )
    public WebElement errorMessage;

    @FindBy(how=How.PARTIAL_LINK_TEXT, using="Welcome")
    public WebElement WelcomeLink;

    public void validateNoUsernameError(){
        loginButton.click();
        String expError= "Username cannot be empty";
        String actError= errorMessage.getText();
        Assert.assertEquals(actError,expError);
    }

    public void validateNoPasswordError(){
        userName.sendKeys("Admin");
        loginButton.click();
        String expError= "Password cannot be empty";
        String actError= errorMessage.getText();
        Assert.assertEquals(actError,expError);
    }

    public void loginWithValidCredential(){
        userName.clear();
        userName.sendKeys("Admin");
        passWord.clear();
        passWord.sendKeys("Admin123");
        loginButton.click();

        if(driver.findElements(By.partialLinkText("Welcome")).size() >0) {
            Assert.assertFalse(errorMessage.isDisplayed(), "User unable to login validation message displayed as " + errorMessage.getText());
        }
        Assert.assertTrue(WelcomeLink.getText().contains("Welcome"));

    }

}
