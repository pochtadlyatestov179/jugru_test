package com.jugru.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 *  Реализация ChromeDriverManager
 *  Вообще по хорошему это все надо делать через Selenium Grid
 */

public class ChromeDriverManager extends DriverManager {
    private ChromeDriverService chService;
    private static String OS = System.getProperty("os.name").toLowerCase();

    @Override
    public void startService() {
        if ((null == chService) && (OS.contains("win"))) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(null == chService){
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/chromedriver"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        driver = new ChromeDriver(chService, options);
    }
}
