package br.com.mlpdleal.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	
	public WebDriver acessarDriver() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
	
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
	public void naoDeveSalvarSemTask() {
	
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
	public void naoDeveSalvarSemData() {
	
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
	public void naoDeveSalvarComDataPassada() {
	
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
