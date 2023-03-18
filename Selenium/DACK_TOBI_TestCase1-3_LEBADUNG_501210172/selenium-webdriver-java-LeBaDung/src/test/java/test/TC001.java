package test;
//Test Step:
//Step 1. Go to https://tobi.vn/
//Step 2. Verify Title of the page
import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

@Test
public class TC001 {
    public static void testTC001() {
        //Init web-driver session
        WebDriver driver = driverFactory.getEdgeDriver();
        try {
            //Step 1. Go to https://tobi.vn/
            driver.get("https://tobi.vn/");
            //Step 2. Verify Title of the page
            String pageURL = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();

            System.out.println(pageURL);
            System.out.println(pageTitle);
            //debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //7. Quit browser session
        driver.quit();
    }
}
