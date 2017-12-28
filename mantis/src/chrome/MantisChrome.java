package chrome;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class MantisChrome {

	WebDriver driver;
	
	@BeforeEach
	public void acessarAplicacao() {
		//Geckdriver usado dentro do projeto
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Thiago\\\\eclipse-workspace\\\\mantis\\\\drivers\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://mantis-prova.base2.com.br");
	}
	
	
	@Test
	public void realizarTestes() throws InterruptedException {
		//Capturando elementos do login
				WebElement username = driver.findElement(By.name("username"));
				WebElement senha = driver.findElement(By.name("password"));
				WebElement botao = driver.findElement(By.className("button"));
			
				
				//Garantindo que campos estão limpos, e passando dados do login
				username.clear();
				username.sendKeys("thiago.fabio");
				
				senha.clear();
				senha.sendKeys("8877!9530");
				
				//Realizar Login
				botao.click();
				
				//Aguardar carregamento
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login-info-left")));
				
				verBugs();
				reportarBug();
	}
	
	
	
    public void verBugs() throws InterruptedException {
		
		//Acessar Bugs
		WebElement linkViewerIssues = driver.findElement(By.xpath("//table/tbody/tr/td/a[2]"));
		linkViewerIssues.click();
		
		//Validar acesso a bugs
		Assert.assertEquals(driver.findElement(By.id("reporter_id_filter")).getText(), "Reporter:");
	}
	
	
	
	public void reportarBug() {
		
		//Reportar bug
		WebElement linkReportIssues = driver.findElement(By.xpath("//table/tbody/tr/td/a[3]"));
		linkReportIssues.click();
		
		//Selecionando categoria do bug
		Select category = new Select(driver.findElement(By.name("category_id")));
		category.selectByValue("42");
		
		//Criando título do bug
		WebElement summary = driver.findElement(By.name("summary"));
		summary.clear();
		summary.sendKeys("Bug de teste");
		
		//Descrição do bug
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys("Meu primeiro bug aberto");
		
		//Submetendo bug
		WebElement submit = driver.findElement(By.className("button"));
		submit.click();
		
	}
	
	@AfterEach
	public void fechar() {
		driver.quit();
	}
}
//