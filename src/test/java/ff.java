import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ff {
@Test
    public void test() throws InterruptedException {
        WebDriver wb = new ChromeDriver();
        wb.manage().window().maximize();
        wb.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wb.get("https://www.dice.com/");
        wb.findElement(By.id("typeaheadInput")).sendKeys("SDET");
        wb.findElement(By.id("submitSearch-button")).click();
    List<WebElement> a = wb.findElements(By.xpath("//a[@class='card-title-link bold'][@target='_self']"));

    for(WebElement element:a) {
        Assert.assertTrue(element.getText().contains("SDET"));
        System.out.println(element.getText());
    }
    }
}
