package com.automation.core.components;

import com.automation.core.logging.Logger;
import com.automation.core.logging.TraceLogger;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.WebDriver;
import sun.rmi.runtime.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class FluentUi implements Fluent {

    private final WebDriver driver;
    private final Logger logger;

    protected FluentUi(WebDriver driver) {
        this(driver, new TraceLogger());
    }

    protected FluentUi(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    public <T> T changeContext() throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Type t = new TypeToken<T>() {
        }.getType();
        Class c = Class.forName(t.getTypeName());
        Constructor<?> ctr = c.getDeclaredConstructor(WebDriver.class);

        return (T) ctr.newInstance(new Object[]{driver});
    }

    public <T> T changeContext(Logger logger) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Type t = new TypeToken<T>() {
        }.getType();
        Class c = Class.forName(t.getTypeName());
        Constructor<?> ctr = c.getDeclaredConstructor(WebDriver.class, Logger.class);

        return (T) ctr.newInstance(new Object[]{driver, logger});
    }

    public <T> T changeContext(String application) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        driver.navigate().to(application);
        driver.manage().window().maximize();
        Type t = new TypeToken<T>() {
        }.getType();
        Class c = Class.forName(t.getTypeName());
        Constructor<?> ctr = c.getDeclaredConstructor(WebDriver.class);

        return (T) ctr.newInstance(new Object[]{driver});
    }

    public <T> T changeContext(String application, Logger logger) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        driver.navigate().to(application);
        driver.manage().window().maximize();
        Type t = new TypeToken<T>() {
        }.getType();
        Class c = Class.forName(t.getTypeName());
        Constructor<?> ctr = c.getDeclaredConstructor(WebDriver.class, Logger.class);

        return (T) ctr.newInstance(new Object[]{driver, logger});
    }
}