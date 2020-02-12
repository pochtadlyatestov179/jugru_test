package com.jugru.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.List;


/**
 *  Класс для непосредственной работы с драйвером
 */
public class DriverWork {

    private WebElement findElementByAriaLabel(WebDriver driver, String label){
        waitForVisibilityOf(driver, By.xpath(".//*[@aria-label = '" + label + "']"));
        return driver.findElement(By.xpath(".//*[@aria-label = '" + label + "']"));
    }

    protected void clickOnElementWithAriaLabel(WebDriver driver, String label){
        WebElement currentElement = findElementByAriaLabel(driver, label);
        waitForClickableOf(driver, currentElement, 30);
        currentElement.click();
    }

    protected boolean checkElementWithAriaLabel(WebDriver driver, String label){
        return findElementByAriaLabel(driver, label).isDisplayed();
    }

    protected boolean checkElementWIthIdOnPage(WebDriver driver, String id){
        return driver.findElement(By.id(id)).isDisplayed();
    }

    private WebElement findElementByText(WebDriver driver, String elementText){
        waitForVisibilityOf(driver, By.xpath(".//*[text()='" + elementText + "']"));
        return driver.findElement(By.xpath(".//*[text()='" + elementText + "']"));
    }

    protected void clickOnElementWithText(WebDriver driver, String elementText){
        WebElement currentElement = findElementByText(driver, elementText);
        waitForClickableOf(driver, currentElement, 30);
        currentElement.click();
    }

    protected boolean findElementWithText(WebDriver driver, String text){
        return findElementByText(driver, text).isDisplayed();
    }

    protected boolean findElementWithHref(WebDriver driver, String href){
        return findElementByHref(driver, href).isDisplayed();
    }

    private WebElement findElementByHref(WebDriver driver, String href) {
        waitForVisibilityOf(driver, By.xpath(".//*[@href = '" + href + "']"));
        return driver.findElement(By.xpath(".//*[@href = '" + href + "']"));
    }


    protected void clickOnElementWithId(WebDriver driver, String id){
        waitForVisibilityOf(driver, By.id(id));
        WebElement currentElement = driver.findElement(By.id(id));
        waitForClickableOf(driver, currentElement, 30);
        currentElement.click();
    }

    protected void sendTextToElementWithId(WebDriver driver, String id, String text){
        waitForVisibilityOf(driver, By.id(id));
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(text);

    }

    private void waitForVisibilityOf(WebDriver driver, By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while(attempts < 2){
            try {
                waitForWebElementCondition(driver, ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length> 0 ? timeOutInSeconds[0] : null));
                break;
            }
            catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            attempts++;
        }
    }

    private void waitForVisibilityOf(WebDriver driver, WebElement element, Integer... timeOutInSeconds) {
        int attempts = 0;
        while(attempts < 2){
            try {
                waitForWebElementCondition(driver, ExpectedConditions.visibilityOf(element), (timeOutInSeconds.length> 0 ? timeOutInSeconds[0] : null));
                break;
            }
            catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            attempts++;
        }
    }

    private void waitForClickableOf(WebDriver driver, WebElement element, Integer... timeOutInSeconds) {
        int attempts = 0;
        while(attempts < 2){
            try {
                waitForWebElementCondition(driver, ExpectedConditions.elementToBeClickable(element), (timeOutInSeconds.length> 0 ? timeOutInSeconds[0] : null));
                break;
            }
            catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            attempts++;
        }
    }

    private void waitForWebElementCondition(WebDriver driver, ExpectedCondition<WebElement> condition, Integer timeOutInSeconds){
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds:30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

}
