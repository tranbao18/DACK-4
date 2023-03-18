package test;
//Test Step:
//Step 1. Goto https://tobi.vn/
//Step 2. Click on SHOP menu and select SHOP ALL
//Step 3. In the list of SHOP ALL , click on Cream T-Shirt and click THÊM VÀO GIỎ HÀNG for Cream T-Shirt
//Step 4. Change QTY value to 1000 and click THANH TOÁN button. Expected that an error is displayed
//Step 5. Verify the error message
//Step 6. Then click on Xoa link. Check cart to see item clear
//Step 7. Verify cart is empty
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class TC003 {
    public static void testTC003() {
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
            //Step 3. In the list of SHOP ALL , click on Cream T-Shirt and click THÊM VÀO GIỎ HÀNG for Cream T-Shirt
            WebElement Cream = driver.findElement(By.xpath("//div[@class='grid-product__secondary-image small--hide']//img[@alt='Worldwide Boxy T-shirt - Cream - TOBI']"));
            Cream.click();

            WebElement CreamAddToCart = driver.findElement(By.xpath("//span[@data-default-text='Thêm vào giỏ hàng']"));
            CreamAddToCart.click();
            //Step 4. Change QTY value to 1000 and click THANH TOÁN button. Expected that an error is displayed
            //The requested quantity for "Cream T-Shirt" is not available.
            Thread.sleep(1500);
            WebElement inputQTY = driver.findElement(By.xpath("//input[@id='cart_updates_43728802906357:fb4e2a54a712f89be6e1eb1b483a6e93']"));
            inputQTY.sendKeys("000");
            WebElement btnTT = driver.findElement(By.cssSelector("button[name='checkout']"));
            btnTT.click();
            //Step 5. Verify the error message
            Thread.sleep(1500);
            WebElement errormsg = driver.findElement(By.cssSelector("._1x52f9s1._1fragema3._1x52f9sm._1fragem1i._1x52f9sf"));
            Assert.assertEquals(errormsg.getText(),"Số lượng sẵn hàng của những mặt hàng này đã thay đổi và được cập nhật trong giỏ hàng của bạn.");
            //Step 6. Then click on Xoa link. Check cart to see item clear
            WebElement emptycart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/section[1]/div[2]/div[2]/div[3]/div[1]/button[1]"));
            emptycart.click();
            Thread.sleep(2500);
            WebElement cart = driver.findElement(By.xpath("//span[@class='cart-link']//*[name()='svg']"));
            cart.click();
            Thread.sleep(1500);
            //Step 7. Verify cart is empty
            WebElement cartEmptyMsg = driver.findElement(By.xpath("//div[@class='drawer__cart-empty appear-animation appear-delay-2']//div[1]"));
            Assert.assertEquals(cartEmptyMsg.getText(), "Giỏ hàng hiện đang trống.");

            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\java\\test\\TC003.png"));
            //debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //7. Quit browser session
        driver.quit();
    }
}
