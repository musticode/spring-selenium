package com.spring.selenium.springselenium.pair;

import com.spring.selenium.page.pair.LoginPage;
import com.spring.selenium.springselenium.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class PairLoginTest extends SpringBaseTestNGTest {

    @Autowired
    LoginPage loginPage;

    @Test
    public void loginTest(){
        this.loginPage.getLoginPage();
        this.loginPage.login();
    }




}
