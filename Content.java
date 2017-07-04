package org.PageObjectTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aderter66 on 29.06.17.
 */
public class Content {
    private final WebDriver driver;
    static final public By content = By.id("content");

    static final public List<String> ResourcesElements = Arrays.asList(new String[]
                   {"Students",
                    "Authors", "Instructors",
                    "Librarians","Corporations",
                    "Conferences","Booksellers",
                    "Societies", "Institutions"});
    static final public List<String> ResourcesForElements = Arrays.asList(new String[]
            {"Students",
                    "Authors", "Instructors",
                    "Librarians",
                    "Corporate Partners","Booksellers",
                    "Societies", "Government Employees"});

    static final public List<By> contentResources  = Arrays.asList(new By[]{
            By.xpath("//div[@id='homepage-links']/ul/li"),
            By.xpath(".//ul[@class='autonavLevel1']/li/a[@href] | .//ul[@class='autonavLevel1']/li/span"),
            By.xpath(".//ul[@class='autonavLevel1']/li/span"),
            By.xpath(".//div[@id='search-results']//div[@class='product-title']/a[@href]"),
            By.xpath(".//h1[@class='productDetail-title']")

    });


    public Content(WebDriver driver){

        this.driver=driver;

        if(!"Wiley: Journals, books, and online products and services".equals(driver.getTitle())){
            throw new IllegalStateException ("Открыта не та страница");
        }
    }

    public List<WebElement> getContentResources(List<By> contentResources, int index){
        return driver.findElement(content).findElements(contentResources.get(index));
    }


    @Test//проверка числа элементов в ресурсах
    public Content CheckResources(){
        List<WebElement> elements = getContentResources(contentResources,0);
//        List<WebElement> elements =driver.findElement(StartPageResources).findElements(By.tagName("a"));

        Assert.assertTrue ("Неверное число элементов",elements.size() == 9);

        List<String> TextElements = new ArrayList<String>();

        for (int i=0;i<elements.size();i++){
            TextElements.add(elements.get(i).getText());
        }

        TextElements.removeAll(ResourcesElements);
        Assert.assertTrue ("Несоответствие элементов",TextElements.size() == 0);

        return this;
    }

    @Test
    public Content ClickResources(String Icon){
        List<WebElement> elements = getContentResources(contentResources,0);
        for (WebElement element:elements){
            if (element.getText().equals(Icon)){
                element.click();
                break;
            }
        }
        return this;
    }

    @Test
    public Content CheckResourceFor(){

        List<WebElement> elements = getContentResources(contentResources,1);

        List<String> TextElements = new ArrayList<String>();

        for (int i=0;i<elements.size();i++){
            TextElements.add(elements.get(i).getText());
        }

        Assert.assertTrue("Не все элементы", TextElements.size() == ResourcesForElements.size());

        TextElements.removeAll(ResourcesForElements);

        Assert.assertTrue("список не верный",TextElements.size() == 0);

        return this;
    }

    @Test
    public Content CheckSelectedItemResourseFor(String Item){
       List<WebElement> element =getContentResources(contentResources,2);
       for (WebElement a:element){
           Assert.assertTrue("Students не выбран",a.getText().equals(Item));
       }
       return this;
    }

    @Test
    public Content CheckSearch(){
        Assert.assertTrue("Отсутвует страница поиска",driver.findElement(By.id("page-title")).getText().equals("Search Results"));
        return this;
    }

    @Test
    public Content SearchResultClick(int item){
        List<WebElement> elements = getContentResources(contentResources,3);

        String header = elements.get(item).getText();

        elements.get(item).click();
        Assert.assertTrue("Открыта не та страница",getContentResources(contentResources,4).get(0).getText().equals(header));
        return this;
    }

    public Content CheckHeader(){
        Assert.assertTrue("Заголовок студент не отображается", driver.findElement(By.xpath("//div[@id='page-title']/h1")).isDisplayed());
       // Assert.assertTrue("Неверная ссылка", driver.getCurrentUrl().equals("http://eu.wiley.com/WileyCDA/Section/id-404702.html"));
        return this;
    }

}
