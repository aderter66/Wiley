package org.PageObjectTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by aderter66 on 28.06.17.
 */

public class TestWiley {
    @Test
    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.wiley.com/WileyCDA/");
        Header Header = new Header(driver);
        Content Content = new Content(driver);
        General General = new General(driver);
        SignUpContainer SignUpContainer = new SignUpContainer(driver);

        try {
        Header.CheckHeaderMenuElements();
        }catch (AssertionError a){System.out.println("Error in test 1");}

        try {
            Content.CheckResources();
        }catch (AssertionError a){System.out.println("Error in test 2");}

        try {
        Content.ClickResources("Students");
        Content.CheckHeader();
        General.CheckURL("http://www.wiley.com/WileyCDA/Section/id-404702.html ");
        }catch (AssertionError a){System.out.println("Error in test 3");}

        try {
            Content.CheckResourceFor();
        }catch (AssertionError a){System.out.println("Error in test 4");}

        try {
        Content.CheckSelectedItemResourseFor("Students");
        }catch (AssertionError a){System.out.println("Error in test 5");}

        Header.ClickLinks("Home");

        try {
        SignUpContainer.ClickButton();
        }catch (AssertionError a){System.out.println("Error in test 7");}

        try {
        SignUpContainer.TextInput("aderter66asdasd");
        }catch (AssertionError a){System.out.println("Error in test 8");}

        try {
        Header.TextInput("for dummies");
        Content.CheckSearch();
        }catch (AssertionError a){System.out.println("Error in test 9");}

        try {
        Content.SearchResultClick(4);
        }catch (AssertionError a){System.out.println("Error in test 10");}

        Header.ClickLinks("Home");

        try {
        Content.ClickResources("Institutions");
        Header.NewTab();
        }catch (AssertionError a){System.out.println("Error in test 11");}
        driver.close();

    }
}
