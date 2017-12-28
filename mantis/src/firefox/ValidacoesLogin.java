package firefox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class ValidacoesLogin {
	
	WebDriver driver;

	@BeforeEach
	public void acessarAplicacao() {
		//Geckdriver usado dentro do projeto
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Thiago\\eclipse-workspace\\mantis\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://mantis-prova.base2.com.br");
	}
	
	@Test
	public void validarObrigatoriedade() {
		
		//Capturando elementos do login
		WebElement botao = driver.findElement(By.className("button"));
		
		botao.click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//body/div[2]")).getText(), "Your account may be disabled or blocked or the username/password you entered is incorrect.");
	}
	
	@Test
	public void acessarResetPassword() {
		
		//Capturando elementos link para resetar password
		WebElement link = driver.findElement(By.xpath("//body/div[4]/span/a"));
		link.click();
		
		//Capturando elementos da página de reset password
		WebElement titulo = driver.findElement(By.className("form-title"));
		
		//Validar acesso a página
		Assert.assertTrue(titulo.isDisplayed());
	
	}
	
	@AfterEach
	public void fechar() {
		driver.quit();
	}
}
