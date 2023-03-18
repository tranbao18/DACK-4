package driver;

//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class driverFactory {
    public static EdgeDriver getEdgeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String edgeDriverRelativePath = "/src/test/resources/drivers/msedgedriver.exe";
        String edgeDriverLocation = currentProjectLocation.concat(edgeDriverRelativePath);
        System.setProperty("webdrive.msedgedriver.driver", edgeDriverLocation);
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
//        return new ChromeDriver();
        return driver;
    }
}
