package com.spring.selenium.page.google;

import com.spring.selenium.selenium.annotation.PageFragment;
import com.spring.selenium.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class SearchComponent extends Base {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtn;

    @FindBy(css = "div[class='FPdoLc lJ9FBc'] input[name='btnK']")
    private WebElement searchButton;

    public void searchFunction(String searchKeyword){
        setText(this.searchBox, searchKeyword);
        this.searchBox.sendKeys(Keys.TAB);
        clickElement(this.searchButton);


    }

    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtn
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }
}
