package aulas_selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DragAndDrop {

    WebDriver driver;

    @Before
    public void iniciaDriver() throws IOException {

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Espera 5 segundos
        driver.get("http://demo.automationtesting.in/Static.html");
    }

    @Test
    public void testeDragAndDrop() {

        WebElement elementoClick = driver.findElement(By.xpath("//img[@id='node']"));
        WebElement elementoDestino = driver.findElement(By.id("droparea"));

        Actions actionProvider = new Actions(driver);

        actionProvider.dragAndDrop (elementoClick,elementoDestino).build().perform();
    }

    @After
    public void fimTeste() throws InterruptedException {

        Thread.sleep(5000);
        driver.close();

    }

}
