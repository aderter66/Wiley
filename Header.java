package org.PageObjectTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aderter66 on 03.07.17.
 */
public class Header {
    private final WebDriver driver;
    private static final By header = By.id("topnav");
    private static final By headerMenu = By.xpath(".//div[@id='links-site']/ul/li");
    private final List<String> startPageTopMenuElements = Arrays.asList(new String[]
                                {"Home","Subjects","About Wiley","Contact Us","Help"});

    public Header (WebDriver driver){
        this.driver = driver;
        if(!"Wiley: Journals, books, and online products and services".equals(driver.getTitle())){
            throw new IllegalStateException ("Открыта не та страница");
        }
    }

    //Получение коллекции элемнетов Header
    public List<WebElement> GetElementsOfHeaderMenu(){
        return driver.findElement(header).findElements(headerMenu);
    }
    //Проверка наличия и юзабельности элементов в Header
    public Header CheckHeaderMenuElements(){
        List<WebElement> WebElements = GetElementsOfHeaderMenu();
        List<String> TextElements = new ArrayList<String>();


        for (WebElement element:WebElements){
            Assert.assertTrue("Элементы не доступны", element.isEnabled() && element.isDisplayed());
        }

        for (int i=0;i<WebElements.size();i++){
            TextElements.add(WebElements.get(i).getText());
        }
        Assert.assertTrue("Не все элементы", TextElements.size() ==  startPageTopMenuElements.size());
        TextElements.removeAll(startPageTopMenuElements);
        Assert.assertTrue("Неверные элементы в топ меню", TextElements.size() == 0);

        return this;
    }

    //ввод текста в поисковую строку
    public Header TextInput(String text){
        WebElement element = driver.findElement(header).findElement(By.id("query"));
        element.sendKeys(text);
        element = driver.findElement(header).findElement(By.xpath(".//input[@value='submit']"));
        element.click();
        return this;
    }
    //клик по пункту меню header
    public Header ClickLinks(String Link){
        List<WebElement> WebElements = GetElementsOfHeaderMenu();

        for (WebElement element:WebElements){
            if(element.getText().equals(Link)){
                element.click();
                break;
            }
        }
        return this;
    }

    public Header NewTab(){
        String[] i={};
        i=driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
        driver.switchTo().window(i[1]);
        Assert.assertTrue("Ссылка не верна",driver.getCurrentUrl().equals("http://wileyedsolutions.com/"));
        System.out.println(driver.getCurrentUrl());
        return this;
    }

}
