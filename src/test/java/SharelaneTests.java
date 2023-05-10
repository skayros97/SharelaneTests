import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SharelaneTests {
    private static final String URL ="https://sharelane.com/cgi-bin/main.py";

    private ChromeDriver driver;
    @BeforeClass
    public void setUp(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void navigate(){
        driver.get(URL);
    }
    @Test
    public void positiveZipRegTest() throws InterruptedException{
        WebElement singUpButton=driver.findElement(By.xpath("//a[@href='./register.py']"));
        singUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertFalse(zipCodeInput.isDisplayed());
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());
    }
    @Test
    public void negativeZipRegTest() throws InterruptedException{
        WebElement singUpButton=driver.findElement(By.xpath("//a[@href='./register.py']"));
        singUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("1234");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertTrue(zipCodeInput.isDisplayed());
        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String exeptedErrorMessage = "Oops, error on page. ZIP code should have 5 digits";
        Assert.assertEquals(errorMessage.getText(),exeptedErrorMessage);
    }
    @Test
    public void positiveSingUpTest(){
        WebElement singUpButton=driver.findElement(By.xpath("//a[@href='./register.py']"));
        singUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertFalse(zipCodeInput.isDisplayed());
        //new
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());
        firstNameInput.clear();
        firstNameInput.sendKeys("Ivan");
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name=last_name]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("Targonsky");
        WebElement emailInput = driver.findElement(By.cssSelector("input[name=email]"));
        emailInput.clear();
        emailInput.sendKeys("example@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password1]"));
        passwordInput.clear();
        passwordInput.sendKeys("1111");
        WebElement confirmPasswordInput=driver.findElement(By.cssSelector("input[name=password2]"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("1111");
        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        registerButton.click();
        String exeptedSuccesMessage= "Account is created!";
        WebElement succesMessage= driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertTrue(succesMessage.isDisplayed());
        Assert.assertEquals(succesMessage.getText(),exeptedSuccesMessage);
    }
    @Test
    public void negativeSingUpTest(){
        WebElement singUpButton=driver.findElement(By.xpath("//a[@href='./register.py']"));
        singUpButton.click();
        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();
        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertFalse(zipCodeInput.isDisplayed());
        //new
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());
        firstNameInput.clear();
        firstNameInput.sendKeys("Ivan1997");
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name=last_name]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("Targonsky");
        WebElement emailInput = driver.findElement(By.cssSelector("input[name=email]"));
        emailInput.clear();
        emailInput.sendKeys("example@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password1]"));
        passwordInput.clear();
        passwordInput.sendKeys("1111");
        WebElement confirmPasswordInput=driver.findElement(By.cssSelector("input[name=password2]"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("1111");
        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        registerButton.click();
        String exeptedSuccesMessage= "Oops, error on page. Some of your fields have invalid data or email was previously used";
        WebElement errorMessage= driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(),exeptedSuccesMessage);
    }
}

