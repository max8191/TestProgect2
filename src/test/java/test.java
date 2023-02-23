import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
@Test
public class test {
Faker faker = new Faker();
 String fullName= faker.name().fullName();
    String stAddress = faker.address().streetAddress();
    String city =faker.address().city();
    String state = faker.address().stateAbbr();
    String expireDate = "10/28";

    public void testMethod() throws InterruptedException {
        WebDriver wb = new ChromeDriver();
        wb.manage().window().maximize();
        wb.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wb.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        wb.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        wb.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        wb.findElement(By.id("ctl00_MainContent_login_button")).click();
        wb.findElement(By.linkText("Order")).click();
        int num = (int)(1+Math.random()*101);
        wb.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE,Integer.toString(num));
        wb.findElement(By.xpath("//input[@value='Calculate']")).click();
        if(num<10)
        Assert.assertEquals(wb.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value"),num*100);
        else
            Assert.assertEquals(wb.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value"),Integer.toString((int)(num*100*0.92)));
    String zip="";
    for (int i =0;i<5;i++){
        zip += ""+(int)(Math.random()*10);
    }
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(fullName);
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(stAddress);
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
    String[] links = new String[]{"ctl00_MainContent_fmwOrder_cardList_0","ctl00_MainContent_fmwOrder_cardList_1","ctl00_MainContent_fmwOrder_cardList_2"};
    int bank = (int)(Math.random()*3);
    wb.findElement(By.id(links[bank])).click();
    String numOfcard ="";
    for (int i =0;i<15;i++){
        numOfcard += ""+(int)(Math.random()*10);
    }
    if(bank+1==1) {
        wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("4"+numOfcard);
    } else if (bank+1==2) {
        wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("5"+numOfcard);
    }else if (bank+1==3){
        wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("3"+numOfcard);
    }
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expireDate);
    wb.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
    Assert.assertTrue(wb.getPageSource().contains("New order has been successfully added."));
    wb.findElement(By.linkText("View all orders")).click();
    wb.findElement(By.xpath("//input[@alt='Edit']")).click();

    Assert.assertTrue(wb.getPageSource().contains(Integer.toString(num)));
    Assert.assertTrue(wb.getPageSource().contains(fullName));
    Assert.assertTrue(wb.getPageSource().contains(stAddress));
    Assert.assertTrue(wb.getPageSource().contains(city));
    Assert.assertTrue(wb.getPageSource().contains(state));
    Assert.assertTrue(wb.getPageSource().contains(numOfcard));
    Assert.assertTrue(wb.getPageSource().contains(expireDate));
    wb.findElement(By.id("ctl00_logout")).click();
    wb.close();
    }
}


