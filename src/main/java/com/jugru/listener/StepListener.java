package com.jugru.listener;

import com.jugru.drivers.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Listener аллюра. Подключается через переопределение класса в ресурсах папки Test META-INF/services
 *  Используется для прикрепление скриншота перед каждым шагом, чтобы было легче воспроизвести ситуацию падения теста
 */


public class StepListener implements StepLifecycleListener {
    @Override
    public void beforeStepStop(StepResult result) {
        byte[] srcFile =  ((TakesScreenshot) DriverManager.driver).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", srcFile);
    }
}
