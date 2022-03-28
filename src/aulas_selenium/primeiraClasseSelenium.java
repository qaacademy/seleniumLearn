package aulas_selenium;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class primeiraClasseSelenium {
	@Rule
	public TestName testName = new TestName(); // Devolve o nome do meu @Teste


	WebDriver driver;

	@Before
	public void iniciaDriver() throws IOException {
		
		inicializarChromeDriver();
		
//		driver = new ChromeDriver();
//		driver.manage().window().setSize(new Dimension(1200, 765));
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Espera 5 segundos
////		driver.get("http://demo.automationtesting.in/Register.html");
	}

	public void testeSelenium() throws InterruptedException {
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Flávio Dias");
		driver.findElement(By.id("checkbox1")).click();
		System.out.println("Status checkbox: " + driver.findElement(By.id("checkbox1")).isSelected());

		assertTrue(driver.findElement(By.id("checkbox1")).isSelected());
		driver.quit();

	}


	public void testeListas() throws InterruptedException {
		driver.get("http://demo.automationtesting.in/Register.html");
		Thread.sleep(2000);

		List<WebElement> elementsLanguages = driver.findElements(By.xpath("//section[@id='section']//li"));

		List<WebElement> elementsSkills = driver.findElements(By.xpath("//select[@id='Skills']"));
		System.out.println("Skills \n");
		for (WebElement e : elementsLanguages) {
			System.out.println(e.getText());
		}
		System.out.println("Skills \n");
		for (WebElement e : elementsSkills) {
			System.out.println(e.getText());
		}

		// Lista Option
		driver.findElement(By.xpath("//select[@id='Skills']")).click();
		Select optionSkills = new Select(driver.findElement(By.xpath("//select[@id='Skills']")));
		optionSkills.selectByVisibleText("XHTML");

		// Lista tipo LI
		driver.findElement(By.id("msdd")).click();
		// Get all the elements available with tag name 'a'
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Portuguese')]")).click();
		driver.quit();

	}


	public void alertasSimples() throws InterruptedException {

		driver.get("http://demo.automationtesting.in/Alerts.html");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='alertbox()']")).click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(5000);
		System.out.println(alert.getText());
		Assert.assertEquals("I am an alert box!", alert.getText());
		alert.dismiss();

	}

	public void alertasComConfirmação() throws InterruptedException {

		driver.get("http://demo.automationtesting.in/Alerts.html");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Alert with OK & Cancel')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'click the button to display a confirm box')]")).click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(5000);
		alert.dismiss();

	}

	public void treinandoComFrames() throws InterruptedException {

		driver.get("http://demo.automationtesting.in/Frames.html");
		driver.switchTo().frame("singleframe");
		WebElement element = driver.findElement(By.xpath("//*[@type='text']"));
		element.click();
		element.sendKeys("Teste Iframess");
		driver.switchTo().defaultContent();//Retorna para o contexto da pagina
		Thread.sleep(2000);

	}

	public void treinandoComPopups() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		driver.get("http://demo.automationtesting.in/Windows.html");
		driver.findElement(By.xpath("//a[contains(text(),'Open New Seperate Windows')]")).click();
		driver.findElement(By.xpath("//button[@onclick='newwindow()']")).click();
		Thread.sleep(2000);
		screenShot("Print 1");
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'SeleniumHQ Browser Automation')]")));
		WebElement element = driver.findElement(By.xpath("//title[contains(text(),'SeleniumHQ Browser Automation')]"));
		System.out.println(element.getText());
		System.out.println(element.getText());
		screenShot("Print 2");
		Thread.sleep(2000);
		System.out.println(driver.getPageSource().contains("LIVES MATTER"));// valida a pagina que estou

	}
	
	@Test
	public void uploadArquivo() throws Exception {
		long inicio = System.currentTimeMillis();
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.findElement(By.xpath("//input[@id='imagesrc']")).sendKeys("/Users/mac/Documents/Flavio.jpg");
		screenShot("Upload Arquivo");
//		Thread.sleep(10000);
		System.out.println(testName.getMethodName());
		long fim = System.currentTimeMillis();
		
		System.out.println(calculaTempoExecucao(inicio, fim) + "Segundos");

	}
	
	public Long calculaTempoExecucao(long inicio,long fim) {
		return (fim - inicio)/1000;
		
		
	}

	public void testeSetupChrome() throws IOException, InterruptedException {
		inicializarChromeDriver();
		driver.get("http://demo.automationtesting.in/Windows.html");
		driver.findElement(By.xpath("//a[contains(text(),'Open New Seperate Windows')]")).click();
		driver.findElement(By.xpath("//button[@onclick='newwindow()']")).click();
		Thread.sleep(2000);
		screenShot("Print 1");
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);

		WebElement element = driver.findElement(By.xpath("//title[contains(text(),'SeleniumHQ Browser Automation')]"));
		System.out.println(element.getText());
		System.out.println(element.getText());
		screenShot("Print 2");
		Thread.sleep(2000);
		System.out.println(driver.getPageSource().contains("LIVES MATTER"));// valida a pagina que estou
		
	}

	/**
	 * Inicializa ChromeDriver
	 * 
	 * @throws IOException
	 */
	private void inicializarChromeDriver() throws IOException {
//		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//			System.setProperty("webdriver.chrome.driver", 
//					"chromedriver.exe");
//		} else {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.pathSeparator+"chromedriver");
//		}
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments(
				   "--headless",
				   "--disable-web-security",
				   "--ignore-certificate-errors",
				   "--allow-running-insecure-content",
				   "--allow-insecure-localhost",
				   "--disable-gpu",
				   "window-size=1200x600",
				   "disable-popup-blocking",
				   "disable-infobars"
				  );
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		
	}

	public void screenShot(String nomeScrenShot) throws IOException {
		TakesScreenshot screenShot = ((TakesScreenshot) driver); //Convertendo o Webdriver em TakesScreenshot
		File arquivo = screenShot.getScreenshotAs(OutputType.FILE); //Tira o Screenshot e armazena no File Arquivo
		FileUtils.copyFile(arquivo, new File("output" + File.separator + nomeScrenShot + "_screenshot.jpg"));

	}

	@After
	public void finalizaTestes() {
		driver.quit();
	}

}
