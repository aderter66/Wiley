package org.PageObjectTest;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by aderter66 on 02.07.17.
 */
public class SignUpContainer {
    private final WebDriver driver;

    private static By SignUpContainer = By.xpath("//div[@class='left homepage-sign-up-container']");

    public SignUpContainer(WebDriver driver){
        this.driver=driver;
    }
//нажатие кнопки в поле входа
    public SignUpContainer ClickButton() {
        WebElement element = driver.findElement(SignUpContainer).findElement(By.xpath(".//button[@name='submitButton']"));
        element.click();
        Alert popPage = driver.switchTo().alert();
        if (popPage != null) {
            Assert.assertTrue("Несоответсвие сообщения", popPage.getText().equals("Please enter email address"));
            driver.switchTo().alert().accept();
        }else {
            Assert.assertTrue("Отсутсвует окно оповещения", popPage != null);
        }

        return this;
    }
//воод текста в поле входа
    public SignUpContainer TextInput(String email){
        WebElement element = driver.findElement(SignUpContainer).findElement(By.className("text"));
        element.sendKeys(email);
        element = driver.findElement(SignUpContainer).findElement(By.xpath(".//button[@name='submitButton']"));
        element.click();
        Alert popPage = driver.switchTo().alert();
        if (popPage != null) {
            Assert.assertTrue("Несоответсвие сообщения", popPage.getText().equals("Invalid email address."));
            driver.switchTo().alert().accept();
        }else {
            Assert.assertTrue("Отсутсвует окно оповещения", popPage != null);
        }
        return this;
    }

}
