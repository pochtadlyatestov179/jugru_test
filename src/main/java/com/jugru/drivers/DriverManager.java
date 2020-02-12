package com.jugru.drivers;

import org.openqa.selenium.WebDriver;


/**
 *  Абстрактный класс драйвер мэнеджера для упрощения работы с разными драйверами
 */
public abstract class DriverManager {


    public static WebDriver driver;

    protected abstract void startService();

    protected abstract void stopService();

    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}