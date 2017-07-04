package org.PageObjectTest;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by aderter66 on 04.07.17.
 */
public class General {
    private final WebDriver driver;

    public General (WebDriver driver){
        this.driver = driver;
    }
//проверка верности ссылки открытой страницы
    public General CheckURL(String URL){
        //System.out.println(driver.getCurrentUrl());
        Assert.assertTrue("Открыта не та страница", URL.equals(driver.getCurrentUrl()));
        return this;
    }
}
