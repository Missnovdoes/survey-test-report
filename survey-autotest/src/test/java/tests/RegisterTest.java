package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class RegisterTest extends Utils {
    public static String url = "http://49.235.61.184:8080/user/register";

    public RegisterTest() { super(url); }

    // TC-REG-01: register success
    public void registerSuccess() throws IOException {
        driver.findElement(By.cssSelector("#name")).sendKeys("autotest");
        driver.findElement(By.cssSelector("#username")).sendKeys("autotest");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.top___1C1Zi > div.header___5xZ3f > span"));
        String title = driver.getTitle();
        getScreenShot(getClass().getName() + "_success");
        assert title.equals("问卷考试系统");
        System.out.println("PASS: TC-REG-01");
    }

    // TC-REG-02: empty name
    public void registerFailEmptyName() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys("autotest2");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#name_help > div")).getText();
        getScreenShot(getClass().getName() + "_emptyName");
        assert msg.equals("用户名是必填项");
        System.out.println("PASS: TC-REG-02");
    }

    // TC-REG-03: empty login account
    public void registerFailEmptyAccount() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#name")).sendKeys("autotest3");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#username_help > div")).getText();
        getScreenShot(getClass().getName() + "_emptyAccount");
        assert msg.equals("登录账号不能为空");
        System.out.println("PASS: TC-REG-03");
    }

    // TC-REG-04: existing account
    public void registerFailExist() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#name")).sendKeys("dup");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#root > div > div.content___1k5Ro > div.main___19HXK > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        getScreenShot(getClass().getName() + "_exist");
        assert msg.equals("账号已存在");
        System.out.println("PASS: TC-REG-04");
    }

    // TC-REG-05: password mismatch
    public void registerFailPwdMismatch() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#name")).sendKeys("pwdtest");
        driver.findElement(By.cssSelector("#username")).sendKeys("pwdtest");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1234");
        driver.findElement(By.cssSelector("#rePassword")).sendKeys("Mismatch");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#rePassword_help > div")).getText();
        getScreenShot(getClass().getName() + "_pwdMismatch");
        assert msg.equals("两次密码不一致");
        System.out.println("PASS: TC-REG-05");
    }
}
