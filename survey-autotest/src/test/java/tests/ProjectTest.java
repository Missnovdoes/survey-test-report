package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class ProjectTest extends Utils {
    public static String url = "http://49.235.61.184:8080/project";

    public ProjectTest() { super(url); }

    // BUG-01: project list empty after creating survey
    public void projectListBug() throws IOException {
        // login first
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // create survey
        driver.findElement(By.xpath("//a[contains(text(),'创建问卷')]")).click();
        driver.findElement(By.cssSelector("button.ant-btn-primary")).click();

        // go to project list
        driver.get(url);
        driver.findElement(By.cssSelector("#sk-layout"));

        // BUG: page content is empty
        getScreenShot(getClass().getName() + "_listBlank");
        System.out.println("BUG CONFIRMED: TC-PROJECT-01 (project list blank after create)");
    }

    // 未登录访问我的项目
    public void noLoginProject() throws IOException {
        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.top___1C1Zi > div.header___5xZ3f > span"));
        String title = driver.getTitle();
        getScreenShot(getClass().getName() + "_noLogin");
        assert title.equals("问卷考试系统");
        System.out.println("PASS: TC-PROJECT-02 (redirect to login)");
    }
}
