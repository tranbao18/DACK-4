package test;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class Test_TOBI {
    static String fname = "thien";
    static String lname = "khue";
    static String email = "thahhkhue273@gmail.com";
    static String password = "12345678";

    /*
    The next scenario is “Verify that you are able to compare two product
    This will need you to work with pop-ups.
    Test Steps:
    1. Go to https://tobi.vn/
    2. Click on the store �T-shirts�
    3. In the T-SHIRTS product list, click on the product to view detailed information
    4. Then click Add to Cart.
    */
    @Test
    public static void test04() {
        WebDriver driver = driverFactory.getEdgeDriver();
        try{
            //1. Go to https://tobi.vn/
            driver.get("https://tobi.vn/");
            //2 .Click on SHOP �T-shirts�
            WebElement btnShop = driver.findElement(By.xpath("//a[normalize-space()='Shop']"));
            btnShop.click();
            Thread.sleep(2000);
            WebElement btnTee = driver.findElement(By.xpath("//a[@class='site-nav__dropdown-link site-nav__dropdown-link--second-level'][normalize-space()='TEE']"));
            btnTee.click();
            Thread.sleep(2000);
            //3. In the T-SHIRTS product list, click on the product to view detailed information
            WebElement btnDetail = driver.findElement(By.cssSelector("div[class='grid-product__secondary-image small--hide']"));
            btnDetail.click();
            Thread.sleep(2000);
            //4. Then click Add to Cart.
            WebElement btnCard = driver.findElement(By.cssSelector("button[name='add']"));
            btnCard.click();
            Thread.sleep(3000);
            TakesScreenshot screenshot2 =((TakesScreenshot)driver);
            File srcFile= screenshot2.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\img\\TestCase04.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /* The next scenario is
    “Verify you can create account in E-commerce site and can share wishlist to other people using email”
    Test steps:
    1. Go to  https://tobi.vn/
    2. Click on the user in the right corner
    3. Click the Create Account link and fill in the New User information (Email ID cannot be duplicated)
    4. Click Sign Up
    5. Verify Registration is complete. Expected account registration completed.
    6. Click on SHOP �T-shirts�
    7. Choose any product and add to cart
    */
    @Test
    public static void test05(){
        WebDriver driver = driverFactory.getEdgeDriver();
        try{
            //1. Go to https://tobi.vn/
            driver.get("https://tobi.vn/");
            Thread.sleep(5000);
            //2. Click on the user in the right corner
            WebElement btnUser = driver.findElement(By.cssSelector("a[class='site-nav__link site-nav__link--icon small--hide']"));
            btnUser.click();
            Thread.sleep(5000);
            //3. Click the Create Account link and fill in the New User information (Email ID cannot be duplicated)
            WebElement btnCreateAccount = driver.findElement(By.cssSelector("a[id='customer_register_link']"));
            btnCreateAccount.click();
            Thread.sleep(3000);
            WebElement inputFname = driver.findElement(By.cssSelector("input[id='FirstName']"));
            inputFname.click();
            inputFname.sendKeys(fname);
            Thread.sleep(3000);
            WebElement inputLname = driver.findElement(By.cssSelector("input[id='LastName']"));
            inputLname.click();
            inputLname.sendKeys(lname);
            Thread.sleep(3000);
            WebElement inputemail = driver.findElement(By.cssSelector("input[id='Email']"));
            inputemail.click();
            inputemail.sendKeys(email);
            Thread.sleep(5000);
            WebElement inputpassword = driver.findElement(By.cssSelector("input[id='CreatePassword']"));
            inputpassword.click();
            inputpassword.sendKeys(password);
            Thread.sleep(3000);
            //4. Click Sign Up
            WebElement btnCreate = driver.findElement(By.cssSelector("input[value='Create']"));
            btnCreate.click();
            Thread.sleep(3000);
//            WebElement btnCaptcha = driver.findElement(By.cssSelector("div[class='recaptcha-checkbox-spinner']"));
//            btnCaptcha.click();
//            Thread.sleep(2000);
//            WebElement btnSubmit = driver.findElement(By.cssSelector("input[value='Submit (Gửi)']"));
//            btnSubmit.click();
//            Thread.sleep(2000);
            // 5. Verify Registration is complete. Expected account registration completed.
            WebElement btnUser2 = driver.findElement(By.cssSelector("a[class='site-nav__link site-nav__link--icon small--hide']"));
            btnUser2.click();
            WebElement compare = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/header[1]/h1[1]"));
            String compare1=compare.getText();
            assertEquals("Error","My account",compare1);
            Thread.sleep(3000);
            TakesScreenshot screenshot2 =((TakesScreenshot)driver);
            File srcFile= screenshot2.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\img\\TestCase05.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /* Verify user can purchase product with registered email id(USING CHROME BROWSER)
    Test steps:
    1. Go to http://live.techpanda.org/
    2. Click on my account link
    3. Sign in to the app using previously created credentials
    4. Click on SHOP �T-shirts�
    5. Choose any product and add to cart
    6. In the next page, click Checkout
    7. Enter the general shipping country, first/last name and middle name/ address/ city/ zip code/ phone number
    8. Click Go to Shipping
    9. Verify shipping costs generated
    10. Select Go to payment
    11. Verify shipping costs are added to the total
    */
    @Test
    public static void test06(){
        WebDriver driver = driverFactory.getEdgeDriver();
        try{
            //1. Go to https://tobi.vn/
            driver.get("https://tobi.vn/");
            Thread.sleep(2000);
            //2. click on user
            WebElement btnUser = driver.findElement(By.cssSelector("a[class='site-nav__link site-nav__link--icon small--hide']"));
            btnUser.click();
            Thread.sleep(2000);
            //3. Sign in to the app using previously created credentials
            WebElement inputemail = driver.findElement(By.cssSelector("input[id='CustomerEmail']"));
            inputemail.click();
            inputemail.sendKeys(email);
            Thread.sleep(3000);
            WebElement inputpassword = driver.findElement(By.cssSelector("input[id='CustomerPassword']"));
            inputpassword.click();
            inputpassword.sendKeys(password);
            Thread.sleep(3000);
            WebElement btnSignIn  = driver.findElement(By.xpath("//button[normalize-space()='Sign In']"));
            btnSignIn.click();
            Thread.sleep(3000);
            //4. Click on SHOP �T-shirts�
            WebElement btnShop = driver.findElement(By.xpath("//a[normalize-space()='Shop']"));
            btnShop.click();
            Thread.sleep(2000);
            WebElement btnTee = driver.findElement(By.xpath("//a[@class='site-nav__dropdown-link site-nav__dropdown-link--second-level'][normalize-space()='TEE']"));
            btnTee.click();
            Thread.sleep(2000);
            //5. Choose any product and add to cart
            WebElement btnDetail = driver.findElement(By.cssSelector("div[class='grid-product__secondary-image small--hide']"));
            btnDetail.click();
            Thread.sleep(2000);
            WebElement btnCard = driver.findElement(By.cssSelector("button[name='add']"));
            btnCard.click();
            Thread.sleep(2000);
            //6. In the next page, click Checkout
            WebElement btnCheckOut = driver.findElement(By.cssSelector("button[name='checkout']"));
            btnCheckOut.click();
            Thread.sleep(2000);
            //7. Enter the general shipping country, first/last name and middle name/ address/ city/ zip code/ phone number
            WebElement Country_select = driver.findElement(By.cssSelector("select[name='countryCode']"));
            Country_select.click();
            Thread.sleep(2000);
            WebElement Country_option = driver.findElement(By.cssSelector("option[value='US']"));
            Country_option.click();
            Thread.sleep(2000);
            WebElement name = driver.findElement(By.cssSelector("input[name='firstName']"));
            name.click();
            name.sendKeys(fname);
            Thread.sleep(2000);
            WebElement middleName = driver.findElement(By.cssSelector("input[name='lastName']"));
            middleName.click();
            middleName.sendKeys(lname);
            Thread.sleep(2000);
            WebElement address = driver.findElement(By.cssSelector("input[name='address1']"));
            address.click();
            address.sendKeys("to hien thanh q10");
            Thread.sleep(2000);
            WebElement city = driver.findElement(By.cssSelector("input[name='city']"));
            city.click();
            city.sendKeys("Pensacola ");
            Thread.sleep(2000);
            WebElement state = driver.findElement(By.cssSelector("select[name='zone']"));
            state.click();
            Thread.sleep(2000);
            WebElement state2 = driver.findElement(By.cssSelector("option[value='FL']"));
            state2.click();
            Thread.sleep(2000);
            WebElement zipcode = driver.findElement(By.cssSelector("input[name='postalCode']"));
            zipcode.click();
            zipcode.sendKeys("32501");
            Thread.sleep(2000);
            WebElement phoneNumber = driver.findElement(By.cssSelector("input[id='phone_field']"));
            phoneNumber.click();
            phoneNumber.sendKeys("8502243245");
            Thread.sleep(2000);
            //8. Click Go to Shipping
            WebElement btnShipping = driver.findElement(By.cssSelector("button[class='QT4by rqC98 aXDZu enNp9 VDIfJ j6D1f janiy adBMs']"));
            btnShipping.click();
            Thread.sleep(2000);
            //9. Verify shipping costs generated
            WebElement priceship = driver.findElement(By.cssSelector("span[class='_19gi7yt0 _19gi7ytf _1fragem1i _19gi7yt1 _19gi7yt8 notranslate']"));
            String price = priceship.getText();
            assertEquals("Error","500.000 ₫",price);
            System.out.println("Vận chuyển "+price);
            Thread.sleep(2000);
            //10. Select Go to payment
            WebElement btnPayMent = driver.findElement(By.cssSelector("button[class='QT4by rqC98 aXDZu enNp9 VDIfJ j6D1f janiy adBMs']"));
            btnPayMent.click();
            Thread.sleep(2000);
            //11. Verify shipping costs are added to the total
            WebElement OldTotal = driver.findElement(By.cssSelector("span[class='_19gi7yt0 _19gi7ytf _1fragem1i _19gi7yt1 _19gi7yt8 notranslate']"));
            String OldTotalCompare=OldTotal.getText();
            System.out.println("Tổng phụ "+OldTotalCompare);
            WebElement NewTotal = driver.findElement(By.cssSelector("strong[class='_19gi7yt0 _19gi7ytl _1fragem1l _19gi7yt1 _19gi7yt8 notranslate']"));
            String NewTotalCompare = NewTotal.getText();
            System.out.println("Tổng phụ + Vận chuyển ="+NewTotalCompare);
            Thread.sleep(3000);
            TakesScreenshot screenshot2 =((TakesScreenshot)driver);
            File srcFile= screenshot2.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\img\\TestCase06.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
