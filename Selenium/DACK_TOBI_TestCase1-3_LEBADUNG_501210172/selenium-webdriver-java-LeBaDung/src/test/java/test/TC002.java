package test;
//Test Step:
//Step 1. Goto https://tobi.vn/
//Step 2. Click on SHOP menu and select SHOP ALL
//Step 3. In the list of SHOP ALL , read the cost of Cream T-Shirt (which is 430.000₫)
//Step 4. Click on Cream T-Shirt
//Step 5. Read the Cream T-Shirt from detail page.
//Step 6. Compare Product value in list and details page should be equal ($100).
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class TC002 {
    public static void testTC002() {
        //Init web-driver session
        WebDriver driver = driverFactory.getEdgeDriver();
        try {
            //Step 1. Goto https://tobi.vn/
            driver.get("https://tobi.vn/");
            //Step 2. Click on SHOP menu and select SHOP ALL
            WebElement dropdownElement = driver.findElement(By.xpath("//a[normalize-space()='Shop']"));
            dropdownElement.click();

            WebElement dropdownselect = driver.findElement(By.xpath("//a[@class='site-nav__dropdown-link site-nav__dropdown-link--second-level'][normalize-space()='SHOP ALL']"));
            dropdownselect.click();
            //Step 3. In the list of SHOP ALL , read the cost of Cream T-Shirt (which is 430.000₫)
            WebElement CreamPrice = driver.findElement(By.xpath("//a[@href='/collections/clothing/products/worldwide-boxy-t-shirt-cream']//div[@class='grid-product__meta']//div[@class='grid-product__price'][contains(text(),'430.000₫')]"));
            String CreamPriceText = CreamPrice.getText();
            System.out.println("The cost of the Cream is: " + CreamPriceText);
            //Step 4. Click on Cream T-Shirt
            WebElement Cream = driver.findElement(By.xpath("//div[@class='grid-product__secondary-image small--hide']//img[@alt='Worldwide Boxy T-shirt - Cream - TOBI']"));
            Cream.click();
            //Step 5. Read the Cream T-Shirt from detail page.
            WebElement CreamDetail = driver.findElement(By.cssSelector(".h2.product-single__title"));
            String CreamDetailText = CreamDetail.getText();
            System.out.println("The name of this T-Shirt is: "+CreamDetailText);
            //Step 6. Compare Product value in list and details page should be equal ($100).
            WebElement CreamDetailPrice = driver.findElement(By.cssSelector(".product__price"));
            String CreamPriceText2 = CreamDetailPrice.getText();
            if(CreamPriceText2.equals(CreamPriceText)){
                System.out.println("The product value in the list and detail pages is equal.");
            }else {
                System.out.println("The product value in the list and detail pages is not equal.");
            }
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\java\\test\\TC002.png"));
            //debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //7. Quit browser session
        driver.quit();
    }
}
