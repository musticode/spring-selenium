package com.spring.selenium.springselenium.test;

import com.spring.selenium.page.google.GooglePage;
import com.spring.selenium.springselenium.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class Google2Test extends SpringBaseTestNGTest {

    @Autowired
    private GooglePage googlePage;


    @Test
    public void searchWithFunction2() throws InterruptedException {
        this.googlePage.goTo();
        //Assert.assertTrue(this.googlePage.isAt());
        this.googlePage.getSearchComponent().searchFunction("springaaaa boot");
        Thread.sleep(1000);
        this.googlePage.quit();

    }
}
