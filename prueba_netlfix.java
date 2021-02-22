package pruebaNetflix;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class prueba_netlfix {
    public WebDriver driver;
    String URL = "https://www.netflix.com/";
    WebDriverWait wait;
    String tagname = "H1";

    @BeforeMethod
    public void BeforeMethod() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 40);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test(priority = 6)
    public void validarTituloTest() {
        String title = driver.findElement(By.xpath("//h1[contains(text(),'Películas y series ilimitadas y mucho más.')]")).getText();
        Assert.assertEquals(title, "Películas y series ilimitadas y mucho más.");

    }

    @Test  (priority = 5)
    public void iniciarSesionPageTest() {
        String title = driver.findElement(By.xpath("//h1[contains(text(),'Películas y series ilimitadas y mucho más.')]")).getText();
        Assert.assertEquals(title, "Películas y series ilimitadas y mucho más.");
        driver.findElement(By.xpath("//a[@class='authLinks redButton']")).click();
        String newTitle = driver.findElement(By.xpath("//h1[contains(text(),'Inicia sesión')]")).getText();
        Assert.assertFalse(title.equals(newTitle));
        Assert.assertEquals(newTitle, "Inicia sesión");
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Iniciar sesión con Facebook')]")).isDisplayed());
    }

    @Test(priority = 4)
    public void loginToNetflixErrorTest() {
        driver.findElement(By.xpath("//a[@class='authLinks redButton']")).click();
        driver.findElement(By.id("id_userLoginId")).sendKeys("test@test.com");
        driver.findElement(By.id("id_password")).sendKeys("holamundo");
        driver.findElement(By.xpath("//span[contains(text(),'Recuérdame')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Iniciar sesión')]")).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Contraseña incorrecta.')]"))); 
              Assert.assertTrue(driver.findElement(By.xpath("//b[contains(text(),'Contraseña incorrecta.')]")).isDisplayed());
                  Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Recuérdame')]/../..//input")).getAttribute("checked").equals("true"));
        }catch (NoSuchElementException e)   {
            System.out.println("Mensaje de error no visible o se modofico ");
        }
        
    }

    @Test(priority = 3)
    public void fakeEmailTest() {
        Faker fakerData = new Faker();
        String email = fakerData.internet().emailAddress();
        driver.findElement(By.xpath("//input[@id='id_email_hero_fuji']")).sendKeys(email);
        driver.findElement(By.xpath("//span[text()='COMIENZA YA']")).click();
        wait.until(ExpectedConditions.urlContains("signup"));
        Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
    }

    @Test(dataProvider = "datosEmails", dataProviderClass = dataProvider.class, priority = 2)
    public void dataProviderEmailTest(Object emails) {
        driver.findElement(By.xpath("//input[@id='id_email_hero_fuji']")).sendKeys(emails.toString());
    }

    @Test(dataProvider = "tagNames", dataProviderClass = dataProvider.class, priority = 1)
    public void printTagsTest(Object tags){
        List<WebElement> tagsList = driver.findElements(By.xpath("//" + tags.toString()));
        if (tagsList.size() > 0) {
            for (int i = 0; i < tagsList.size(); i++) {
                System.out.println(tags.toString() + " = " + tagsList.get(i).getText());
            }
        }else{
             System.out.println("No se encuentran elementos con esta etiqueta ("+tags.toString()+")");
        }

    }
    
}








