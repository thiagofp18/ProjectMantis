package firefox;

import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class LoginMantis {
	
	WebDriver driver;
	
	@BeforeEach
	public void acessarAplicacao() {
		//Geckdriver usado dentro do projeto
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Thiago\\eclipse-workspace\\mantis\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://mantis-prova.base2.com.br");
	}
	
	@Test
    public void realizaLogin() throws InterruptedException {
		
		
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
		
		//Validar apresentação da tela inicial
		WebElement loginTexto = driver.findElement(By.className("login-info-left"));
		Assert.assertTrue(loginTexto.isDisplayed());
		
	}
	
	
	
	@AfterEach
	public void fechar() {
		driver.quit();
	}
}
