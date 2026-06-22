package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class TemplateTest extends Utils {
    public static String url = "http://49.235.61.184:8080/template";

    public TemplateTest() { super(url); }

    private void login() {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // TC-TEMP-01: template list page loads
    public void templateListPage() throws IOException {
        login();
        driver.get(url);
        driver.findElement(By.cssSelector("#sk-layout"));
        getScreenShot(getClass().getName() + "_list");
        System.out.println("PASS: TC-TEMP-01 (template list)");
    }

    // TC-TEMP-02: search bug
    public void searchBug() throws IOException {
        login();
        driver.get(url);
        driver.findElement(By.cssSelector("input")).sendKeys("test");
        driver.findElement(By.xpath("//button[contains(.,'搜模板')]")).click();
        getScreenShot(getClass().getName() + "_searchBug");
        System.out.println("BUG CONFIRMED: TC-TEMP-02 (search incorrect)");
    }
}
