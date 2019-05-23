import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Prueba {

    private static WebDriver driver;

    public enum Driver {
        Chrome, Firefox
    }

    private void loadDriver(Driver driverType) {
        switch (driverType) {
            case Firefox:
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\cris\\IdeaProjects\\ACOES-TEST\\lib\\geckodriver.exe");
                this.driver = new FirefoxDriver();
                break;
            case Chrome:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\cris\\IdeaProjects\\ACOES-TEST\\lib\\chromedriver.exe");
                this.driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp(){
        loadDriver(Driver.Firefox);
    }

    @AfterEach
    public void later(){
        driver.quit();
    }

    @Test
    public void testLogin(){
        driver.get("http://localhost:8080/ACOES/index.xhtml");
        driver.findElement(By.id("btnGroupDrop1")).click();

        driver.findElement(By.linkText("Login")).click();
        //Insertar correo para el login
        driver.findElement(By.id("j_idt7:em")).sendKeys("criscardas@gmail.com");
        //Insertar contraseña para el login
        driver.findElement(By.id("j_idt7:nonFeedback")).sendKeys("jeje");

        driver.findElement(By.name("j_idt7:j_idt11")).click();

        List<WebElement> username = driver.findElements(By.tagName("button"));
        for(WebElement w : username){
            System.out.println(w.getText());
        }
        assertEquals("Welcome criscardas", username.get(1).getText());

    }


    @Test
    public void casoDePrueba(){

        //Esto para todas las pruebas.
        driver.get("https://www.uma.es");
        driver.findElement(By.linkText("Iniciar sesión")).click();

        driver.findElement(By.linkText("Español")).click();
        assertEquals("Inicio - Universidad de Málaga", driver.getTitle());

        //Pulsamos en Contacta.
        driver.findElement(By.linkText("Contacta")).click();
        assertEquals("Inicio - Universidad de Málaga", driver.getTitle());

        //Pulsamos en Buscador de personal
        driver.findElement(By.linkText("Buscador de personal")).click();
        assertEquals("DUMA - Buscador de DUMA", driver.getTitle());

        //Escribimos en las caja de texto.
        driver.findElement(By.id("id_nombre")).sendKeys("Francisco");
        driver.findElement(By.id("id_apellido_1")).sendKeys("Durán");

        //Mandamos el form.
        driver.findElement(By.id("id_apellido_1")).submit();
        assertEquals("DUMA - Buscador de DUMA", driver.getTitle());


        //Pulsamos en el enlace del profesor.
        driver.findElement(By.linkText("Duran Muñoz, Francisco Javier")).click();
        //comprobamos titulo
        assertEquals("DUMA - Datos de Francisco Javier Durán Muñoz en DUMA", driver.getTitle());

        //Comprobamos el número de teléfono.

        List<WebElement> elements = driver.findElements(By.className("col-md-10"));

        assertEquals("952132820", elements.get(0).getText());


    }
}
