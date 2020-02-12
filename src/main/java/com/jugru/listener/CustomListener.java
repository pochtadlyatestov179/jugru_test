package com.jugru.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Listener. Требуется для того чтобы перехватывать состояния тестов.
 *  Рефлексия используется посколько при связке dataprovider+cucumber фичи после прогона тестов будет написано, что успешно прошел один тест
 *  Данная проблема очень сильно напрягает при прогоне тестов из TeamCity или в другой системе
 */

public class CustomListener implements ITestListener {

    private static final Method method;
    private AtomicInteger count = new AtomicInteger();

    static {
        try {
            method = TestResult.class.getDeclaredMethod("setTestName", String.class);
            method.setAccessible(true);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot get \"setTestName\" method from TestResult class", e);
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
        try {
            method.invoke(result, count.addAndGet(1)+"");
        }
        catch (InvocationTargetException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) { }

    @Override
    public void onTestFailure(ITestResult result) { }

    @Override
    public void onTestSkipped(ITestResult result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }


}
