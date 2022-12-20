package com.spring.selenium.page;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Base {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public <T> WebElement findElement(T elementAttr){
        if (elementAttr.getClass().getName().contains("By")){
            return driver.findElement((By) elementAttr);
        }else {
            return (WebElement) elementAttr;
        }
    }


    public <T> void waitElement(T elementAttr){
        if (elementAttr.getClass().getName().contains("By")){
            this.wait.until(ExpectedConditions.elementToBeClickable((By) elementAttr));
        }else {
            this.wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr ));
        }


    }

    public <T> void clickElement(T elementAttr){
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).click();
        }else{
            ((WebElement) elementAttr).click();
        }
    }

    public <T> void setText(T elementAttr, String text){
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).sendKeys(text);
        }else{
            ((WebElement) elementAttr).sendKeys(text);
        }
    }


    public <T> String getText(T elementAttr){
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")){
            return driver.findElement((By) elementAttr).getText();
        }else{
            return ((WebElement) elementAttr).getText();
        }
    }


    public void quit(){
        this.driver.quit();
    }


    public abstract boolean isAt();
}
