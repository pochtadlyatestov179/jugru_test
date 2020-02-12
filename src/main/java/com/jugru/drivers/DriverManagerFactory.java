package com.jugru.drivers;

/**
 *  Factory driver'ов
 */

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager = null;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
                //TODO реализовать оставшиеся драйверы
//            case FIREFOX:
//                driverManager = new FirefoxDriverManager();
//                break;
//            default:
//                driverManager = new SafariDriverManager();
//                break;
        }
        return driverManager;

    }
}
