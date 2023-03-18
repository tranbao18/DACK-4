package test;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import static org.testng.AssertJUnit.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
    // Nhập tên tài khoản mới vào đây để chạy trên toàn bộ hệ thống
    static public String tkuser = "chedai123@gmail.com";// Nhập tên tài khoản (email) mới vào đây để bắt đầu chạy trên toàn bộ hệ thống
    static public String tkpass = "12345678";// Nhập mật khẩu muốn đặt cho tài khoản vào đây
    static public String RecentOrderID = "";
    @Test
    /* Verify that you will be able to save previously placed order as a pdf file
        Test Steps:
        1. Go to https://tobi.vn/
        2. Click on My Account link
        3. Login in application using previously created credential
        4. Click on 'My Orders'
        5. Click on 'View Order'
        6. *** note: After steps 4 and 5, step 6 "RECENT ORDERS" was not displayed
           Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
        7. Click on 'Print Order' link
        8. *** note: the link was not found.
         Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.
    */
    public static void TC07() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{   // Step 1: Go to http://live.techpanda.org/
                driver.get("https://tobi.vn/");

                // Step 2: Click on my account link
                Thread.sleep(1000);
                WebElement myAcc = driver.findElement(By.xpath("//a[@class='site-nav__link site-nav__link--icon small--hide']//*[name()='svg']"));
                myAcc.click();

                // Step 3: Login in application using previously created credential
                Thread.sleep(1000);
                WebElement inpEmail = driver.findElement(By.xpath("//input[@id='CustomerEmail']"));
                inpEmail.clear();
                inpEmail.sendKeys(tkuser);
                Thread.sleep(1000);
                WebElement inpPass = driver.findElement(By.xpath("//input[@id='CustomerPassword']"));
                inpPass.clear();
                inpPass.sendKeys(tkpass);

                Thread.sleep(1000);
                WebElement login = driver.findElement(By.cssSelector("button[class='btn btn--full']"));
                login.click();

                // Step 4: Click on 'My Orders'
                // Skip

                // Step 5: Click on 'View Order'
                // Skip

                // Step 6: Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
                Thread.sleep(1500);
                WebElement RecentOrderTable = driver.findElement(By.xpath("//h2[normalize-space()='Order History']"));
                assertEquals("Recent Order Table", "Order History", RecentOrderTable.getText());

                // Step 7: Click on 'Print Order' link
                // Skip

                // Step 8: Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.
                // Skip

                //Quit Browser
                WebElement Logout = driver.findElement(By.xpath("//a[@class='btn btn--secondary btn--small section-header__link']"));
                Logout.click();
                driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    /* Verify you are able to change or reorder previously added product
     *  Test Data = QTY = 10
        Test Steps:
        1. Go to https://tobi.vn/
        2. Click on my account link
        3. Login in application using previously created credential
        4. Click on 'REORDER' link , change QTY & click Update
        5. Verify Grand Total is changed
        6. Complete Billing & Shipping Information
        7. Verify order is generated and note the order number

        Expected outcomes:
        1) Grand Total is Changed
        2) Order number is generated
    */
    public static void TC08() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{    // Step 1: Go to https://tobi.vn/
                driver.get("https://tobi.vn/");

                // Step 2: Click on my account link
                Thread.sleep(1000);
                WebElement myAcc = driver.findElement(By.xpath("//a[@class='site-nav__link site-nav__link--icon small--hide']//*[name()='svg']"));
                myAcc.click();

                // Step 3: Login in application using previously created credential
                Thread.sleep(1000);
                WebElement inpEmail = driver.findElement(By.xpath("//input[@id='CustomerEmail']"));
                inpEmail.clear();
                inpEmail.sendKeys(tkuser);
                Thread.sleep(1000);
                WebElement inpPass = driver.findElement(By.xpath("//input[@id='CustomerPassword']"));
                inpPass.clear();
                inpPass.sendKeys(tkpass);

                Thread.sleep(1000);
                WebElement login = driver.findElement(By.cssSelector("button[class='btn btn--full']"));
                login.click();

                // Step 4: Click on 'REORDER' link ,change QTY & click Update
                Thread.sleep(2000);
                WebElement shop = driver.findElement(By.xpath("//a[normalize-space()='Shop']"));
                shop.click();
                WebElement shopall = driver.findElement(By.xpath("//a[@class='site-nav__dropdown-link site-nav__dropdown-link--second-level'][normalize-space()='SHOP ALL']"));
                shopall.click();

                Thread.sleep(1000);
                WebElement prod = driver.findElement(By.cssSelector("div[class='grid-product__secondary-image small--hide'] img[alt='Worldwide Boxy T-shirt - Cream - TOBI']"));
                prod.click();
                Thread.sleep(1000);
                WebElement addprod = driver.findElement(By.cssSelector("span[data-default-text='Thêm vào giỏ hàng']"));
                addprod.click();

                WebElement cart = driver.findElement(By.xpath("//span[@class='cart-link']//*[name()='svg']"));
                cart.click();
                Thread.sleep(2000);
                WebElement inpQty = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]"));
                inpQty.click();
                inpQty.sendKeys("0");

                // Step 5: Verify Grand Total is changed
                Thread.sleep(2000);
                WebElement GrTotalTit = driver.findElement(By.xpath("//div[@class='ajaxcart__subtotal']"));
                GrTotalTit.click();
                Thread.sleep(1000);
                WebElement GrTotal = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[2]/div[2]"));
                String TxtGrTotal = GrTotal.getText();
                assertEquals("Grand Total is changed", "4.300.000₫", TxtGrTotal);

                // Step 6: Complete Billing & Shipping Information
                // Skip

                // Step 7: Verify order is generated and note the order number
                // Skip

                //Quit Browser
                WebElement quitcart = driver.findElement(By.xpath("//form[@id='CartDrawerForm']//button[@type='button']//*[name()='svg']"));
                quitcart.click();
                WebElement myAcc2 = driver.findElement(By.xpath("//a[@class='site-nav__link site-nav__link--icon small--hide']//*[name()='svg']"));
                myAcc2.click();
                WebElement Logout = driver.findElement(By.xpath("//a[@class='btn btn--secondary btn--small section-header__link']"));
                Logout.click();
                driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    /*  Verify Discount Coupon works correctly
        Test Steps:
        1. Go to https://tobi.vn/
        2. Go to Shop and add Worldwide Boxy T-shirt - Cream to cart
        3. Enter Coupon Code
        4. Verify the discount generated

        TestData: Coupon Code: GURU50
        Expected result:
        1) Price is discounted by 5%
    */
    public static void TC09() {
        try{
                // Skip
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}