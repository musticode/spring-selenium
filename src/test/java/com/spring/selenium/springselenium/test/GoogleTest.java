package com.spring.selenium.springselenium.test;

import com.spring.selenium.page.google.GooglePage;
import com.spring.selenium.springselenium.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GoogleTest extends SpringBaseTestNGTest {

    @Autowired
    private GooglePage googlePage;


    //@Test
    public void googleTest() throws IOException {
        this.googlePage.goTo();  //www google -> go
        Assert.assertTrue(this.googlePage.isAt());

        this.googlePage.getSearchComponent().search("spring boot");

        //Assert.assertTrue(this.googlePage.getSearchResult().isAt());
        //Assert.assertTrue(this.googlePage.getSearchResult().getCount() > 2 );

    }



    @Test
    public void searchWithFunction() throws InterruptedException {
        this.googlePage.goTo();
        //Assert.assertTrue(this.googlePage.isAt());
        this.googlePage.getSearchComponent().searchFunction("springaaaa boot");
        Thread.sleep(1000);

    }





}
