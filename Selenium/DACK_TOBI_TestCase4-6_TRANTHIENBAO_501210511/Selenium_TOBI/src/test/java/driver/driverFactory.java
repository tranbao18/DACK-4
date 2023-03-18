package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class driverFactory {
    public static WebDriver getEdgeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String msedgeDriverRelativePath = "/src/test/resources/drivers/msedgedriver.exe";
        String msedgeDriverLocation = currentProjectLocation.concat(msedgeDriverRelativePath);
        System.setProperty("webdriver.edge  .driver", msedgeDriverLocation);
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        //return new ChromeDriver();
        return driver;
    }
}

