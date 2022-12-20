package com.spring.selenium.page.pair;

import com.spring.selenium.page.Base;
import com.spring.selenium.selenium.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class LoginPage extends Base {

    @FindBy(xpath = "//a[normalize-space()='Login With O365 Account']")
    private WebElement loginWith0365AccountButton;

    public void getLoginPage(){
        driver.get("");
    }

    public void login(){
        clickElement(loginWith0365AccountButton);
    }

    @Override
    public boolean isAt() {
        return true;
    }
}
