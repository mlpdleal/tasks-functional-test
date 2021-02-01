package br.com.mlpdleal.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	
	public WebDriver acessarDriver() throws MalformedURLException {
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		//WebDriver driver = new ChromeDriver();

		DesiredCapabilities cap = DesiredCapabilities.chrome();

		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.32:4444/wd/hub"), cap);
		driver.get("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
	
		WebDriver driver = acessarDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.name("task")).sendKeys("Teste Automatizado");
			
			driver.findElement(By.name("dueDate")).sendKeys("20/10/2078");
			
			driver.findElement(By.id("saveButton")).click();
					
			Assert.assertEquals("Success!", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
			
	}
	
	@Test
	public void naoDeveSalvarSemTask() throws MalformedURLException {
	
		WebDriver driver = acessarDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.name("dueDate")).sendKeys("20/10/2078");
			
			driver.findElement(By.id("saveButton")).click();
					
			Assert.assertEquals("Fill the task description", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
			
	}
	
	@Test
	public void naoDeveSalvarSemData() throws MalformedURLException {
	
		WebDriver driver = acessarDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.name("task")).sendKeys("Teste Automatizado");
			
			driver.findElement(By.id("saveButton")).click();
					
			Assert.assertEquals("Fill the due date", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
			
	}
	
	@Test
	public void naoDeveSalvarComDataPassada() throws MalformedURLException {
	
		WebDriver driver = acessarDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
		driver.findElement(By.name("task")).sendKeys("Teste Automatizado");
			
			driver.findElement(By.name("dueDate")).sendKeys("20/10/2008");
			
			driver.findElement(By.id("saveButton")).click();
					
			Assert.assertEquals("Due date must not be in past", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
			
	}

}
