package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class UserSettingTest extends Utils {
    public UserSettingTest() { super("http://49.235.61.184:8080/user/login"); }

    private void login() {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // TC-USER-01: user management - create without status (BUG-05)
    public void createUserNoStatusBug() throws IOException {
        login();
        driver.get("http://49.235.61.184:8080/system/user");
        driver.findElement(By.xpath("//button[contains(.,'新建')]")).click();
        driver.findElement(By.cssSelector("input#name")).sendKeys("autotestusr");
        driver.findElement(By.cssSelector("input#username")).sendKeys("autotestusr");
        driver.findElement(By.cssSelector("input#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("input#rePassword")).sendKeys("Test1234");
        driver.findElement(By.xpath("//button[contains(.,'确定')]")).click();
        getScreenShot(getClass().getName() + "_noStatusBug");
        System.out.println("BUG CONFIRMED: TC-USER-07 (create without status)");
    }

    // TC-SET-01: empty name validation
    public void settingEmptyName() throws IOException {
        login();
        driver.get("http://49.235.61.184:8080/system/setting");
        driver.findElement(By.cssSelector("input#name")).clear();
        driver.findElement(By.xpath("//button[contains(.,'提交')]")).click();
        String msg = driver.findElement(By.cssSelector("#name_help > div")).getText();
        getScreenShot(getClass().getName() + "_emptyName");
        assert msg.equals("请输入您的昵称");
        System.out.println("PASS: TC-SET-02 (empty name validation)");
    }

    // TC-SET-02: same password bug (BUG-06)
    public void samePasswordBug() throws IOException {
        login();
        driver.get("http://49.235.61.184:8080/system/setting");
        // Click security tab
        driver.findElement(By.xpath("//div[contains(text(),'安全设置')]")).click();
        // Click modify
        driver.findElement(By.xpath("//span[contains(text(),'修改')]")).click();
        // Same old/new password
        driver.findElement(By.cssSelector("input#oldPassword")).sendKeys("root11");
        driver.findElement(By.cssSelector("input#newPassword")).sendKeys("root11");
        driver.findElement(By.cssSelector("input#confirmPassword")).sendKeys("root11");
        driver.findElement(By.xpath("//button[contains(.,'确认')]")).click();
        getScreenShot(getClass().getName() + "_samePwdBug");
        System.out.println("BUG CONFIRMED: TC-SET-08 (same password changed successfully)");
    }

    // TC-SET-03: password mismatch validation
    public void passwordMismatch() throws IOException {
        login();
        driver.get("http://49.235.61.184:8080/system/setting");
        driver.findElement(By.xpath("//div[contains(text(),'安全设置')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'修改')]")).click();
        driver.findElement(By.cssSelector("input#oldPassword")).sendKeys("root11");
        driver.findElement(By.cssSelector("input#newPassword")).sendKeys("NewPass1234");
        driver.findElement(By.cssSelector("input#confirmPassword")).sendKeys("Mismatch999");
        driver.findElement(By.xpath("//button[contains(.,'确认')]")).click();
        getScreenShot(getClass().getName() + "_pwdMismatch");
        System.out.println("PASS: TC-SET-06 (password mismatch)");
    }
}
