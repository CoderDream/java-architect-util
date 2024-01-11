package org.wangqing.notebookk8s.notebook;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.wangqing.notebookk8s.notebook.controller.NotebookController;
import org.wangqing.notebookk8s.notebook.entity.Notebook;
import org.wangqing.notebookk8s.notebook.repository.NotebookRepository;

import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	public static final String ADD_NOTE_BTN_VALUE = "Add Note";
	public static final String SIGNUP_BTN_ID = "signup";
	public static final String ADD_NOTE_BTN_ID = "#addNoteBtn";
	public static final String HTTP_LOCALHOST = "http://localhost:";
	public static final String PATH_LIST = "/list";
	public static final long TEST_NOTEBOOK_ID = 1;
	public static final String VALUE_BTN_KEY = "value";
	public static final String TEST_NAME = "Test";
	public static final String TEST_EMAIL = "test@test.com";
	@Autowired
	private NotebookController controller;

	@Autowired
	private NotebookRepository notebookRepository;

	@LocalServerPort
	private int port;

	@After public void destory(){
		notebookRepository.deleteAll();
	}

	@Test
	public void noteBookRepositoryGetTest() throws  InterruptedException{
		//初始化数据
		Notebook notebook = new Notebook();
		notebook.setId(TEST_NOTEBOOK_ID);
		notebook.setName(TEST_NAME);
		notebook.setEmail(TEST_EMAIL);
		notebookRepository.save(notebook);

		Optional<Notebook> nbList = notebookRepository.findById( TEST_NOTEBOOK_ID);
		Notebook newNotebook = nbList.get();
		Assert.assertEquals(TEST_EMAIL, newNotebook.getEmail());
	}

	@Test
	public void listPageChromeUItest() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			String serverUrl = HTTP_LOCALHOST +port+ PATH_LIST;
			driver.get(serverUrl);
			driver.findElement(By.id(SIGNUP_BTN_ID)).sendKeys(Keys.ENTER);
			WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector(ADD_NOTE_BTN_ID)));
			String actualBtnValue = firstResult.getAttribute(VALUE_BTN_KEY);
			System.out.println(actualBtnValue);
			Assert.assertEquals(ADD_NOTE_BTN_VALUE, actualBtnValue);
		} finally {
			driver.quit();
		}
	}

}
