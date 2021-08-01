package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.util.Strings;

import java.util.concurrent.TimeUnit;

public class Config {

    public static WebDriver driver;
    public static String url;
    public static String baseURL = System.getProperty("env");
    public static String browserType = System.getProperty("browser");

    // Setup Browser for multiple
    public static WebDriver initDriver (String driverType){
        if(driverType.equalsIgnoreCase("ch")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (driverType.equalsIgnoreCase("ff")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }else {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        return driver;
    }

    @BeforeTest
    public void openBrowser(){
        // default code
        if (Strings.isNullOrEmpty(browserType)){
            browserType="ch";
        }
        if (Strings.isNullOrEmpty(baseURL)){
            baseURL="qa";
        }
        driver = initDriver(browserType);
        switch (baseURL){
            case "qa":
                url = "http://127.0.0.1/orangehrm/symfony/web/index.php/auth/login";
                break;
            case "stage":
                url = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";
                break;
        }
        driver.get(url);
    }

}
