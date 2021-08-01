package TestScript;

import Base.Config;
import PageObjects.LoginPageObject;
import org.testng.annotations.Test;

public class loginPage extends Config {



        @Test(enabled = false)
        public void errorMessageTestCase(){
                LoginPageObject lp= new LoginPageObject(driver);
                lp.validateNoPasswordError();
        }
        @Test(priority = 1)
        public void errorMessageTestCase_1(){
                LoginPageObject lp= new LoginPageObject(driver);
                lp.validateNoUsernameError();
                lp.validateNoPasswordError();
        }
        @Test(dependsOnMethods = "errorMessageTestCase_1")
        public void validLogin() throws InterruptedException {
                LoginPageObject lp=new LoginPageObject(driver);

                lp.loginWithValidCredential();
                Thread.sleep(3000);
        }
        }
