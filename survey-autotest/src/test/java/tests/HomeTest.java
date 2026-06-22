package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomeTest extends Utils {
    public static String url = "http://49.235.61.184:8080/home";

    public HomeTest() { super(url); }

    // 未登录访问首页 → 重定向
    public void noLoginHome() throws IOException {
        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.top___1C1Zi > div.header___5xZ3f > span"));
        String title = driver.getTitle();
        getScreenShot(getClass().getName() + "_noLogin");
        assert title.equals("问卷考试系统");
        System.out.println("PASS: TC-HOME-01 (no login redirect)");
    }

    // 登录后首页元素检查
    public void homeElements() throws IOException {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 检查首页核心元素
        driver.findElement(By.cssSelector("#sk-layout"));
        driver.findElement(By.cssSelector("#rc-tabs-0-tab-survey"));
        driver.findElement(By.cssSelector("#rc-tabs-0-tab-exam"));
        getScreenShot(getClass().getName() + "_elements");
        System.out.println("PASS: TC-HOME-02 (home elements)");
    }

    // 点击创建问卷
    public void createSurvey() throws IOException {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//a[contains(text(),'创建问卷')]")).click();
        driver.findElement(By.cssSelector("#editor"));
        getScreenShot(getClass().getName() + "_surveyEditor");
        System.out.println("PASS: TC-HOME-03 (create survey)");
    }

    // 点击创建考试
    public void createExam() throws IOException {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//a[contains(text(),'创建考试')]")).click();
        driver.findElement(By.cssSelector("#editor"));
        getScreenShot(getClass().getName() + "_examEditor");
        System.out.println("PASS: TC-HOME-04 (create exam)");
    }
}
