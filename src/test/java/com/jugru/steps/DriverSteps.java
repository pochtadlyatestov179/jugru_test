package com.jugru.steps;

import com.jugru.drivers.DriverManagerFactory;
import com.jugru.drivers.DriverType;
import com.jugru.drivers.DriverWork;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 *  Класс с описанием шагов выполнения для cucumber
 */


public class DriverSteps extends DriverWork {

    private static WebDriver driver = null;

    @Given("Инициализируем {string} driver и переходим к сайту")
    public void initDriver(String browserName){
        DriverManagerFactory.getManager(DriverType.valueOf(browserName)).quitDriver();
        driver = DriverManagerFactory.getManager(DriverType.valueOf(browserName)).getDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://my.jugru.org//");
    }

    @And("Нажимаем на элемент {string} с aria-label = {string}")
    public void clickOnElementWithAriaLabel(String elementName, String elementLabel){
        clickOnElementWithAriaLabel(driver, elementLabel);
    }

    @And("Нажимаем на элемент {string} с текстом = {string}")
    public void clickOnElementWithText(String elementName, String elementText){
        clickOnElementWithText(driver, elementText);
    }

    @And("Нажимаем на элемент {string} с id = {string}")
    public void clickOnElementId(String elementName, String elementId){
        clickOnElementWithId(driver, elementId);
    }

    @And("Вводим текст = {string} в элемент {string} с id = {string}")
    public void sendTextOnElementId(String text, String elementName, String elementId){
        sendTextToElementWithId(driver, elementId, text);
    }

    @Then("Проверяем, что ссылка = {string} присутствует на странице")
    public void checkHrefOnPage(String href){
        assertTrue(findElementWithHref(driver, href), "Элемента с указанной ссылкой на странице нет");
    }

    @Then("Проверяем, что элемент с id = {string} присутствует на странице")
    public void checkElementWithIdOnPage(String id){
        assertTrue(checkElementWIthIdOnPage(driver, id), "Элемента с указанным id на странице нет");
    }

    @Then("Проверяем, что элемент = {string} с aria-label = {string} присутствует на странице")
    public void checkElementWithAriaLabelOnPage(String elementName, String label){
        assertTrue(checkElementWithAriaLabel(driver, label), "Элемента с указанным aria-label на странице нет");
    }


    @Then("Проверяем, что текст = {string} присутствует на странице")
    public void checkTextOnPage(String text) {
        assertTrue(findElementWithText(driver, text), "Элемента с указанным текстом на странице нет");
    }

    @Then("Закрываем driver")
    public void closeDriver(){
        driver.close();
    }

    public static WebDriver getDriver(){
        return driver;
    }


    @And("Шаг для теста")
    public void stepForTest() {
        System.out.println("Yo-ho-ho!");
    }

}
