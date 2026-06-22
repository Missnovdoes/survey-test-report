package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class LoginTest extends Utils {
    public static String url = "http://49.235.61.184:8080/user/login";

    public LoginTest() { super(url); }

    // TC-LOGIN-01: login success
    public void loginSuccess() {
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Check home page loaded
        driver.findElement(By.cssSelector("#sk-layout"));
        String title = driver.getTitle();
        assert title.equals("首页");
        System.out.println("PASS: TC-LOGIN-01");
    }

    // TC-LOGIN-02: empty username
    public void loginFailEmptyUsername() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#username_help > div")).getText();
        getScreenShot(getClass().getName() + "_emptyUsername");
        assert msg.equals("用户名是必填项");
        System.out.println("PASS: TC-LOGIN-02");
    }

    // TC-LOGIN-03: empty password
    public void loginFailEmptyPassword() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#password_help > div")).getText();
        getScreenShot(getClass().getName() + "_emptyPassword");
        assert msg.equals("密码是必填项");
        System.out.println("PASS: TC-LOGIN-03");
    }

    // TC-LOGIN-04: wrong credentials
    public void loginFailWrong() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys("wronguser");
        driver.findElement(By.cssSelector("#password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        getScreenShot(getClass().getName() + "_wrong");
        assert msg.equals("错误的用户名和密码");
        System.out.println("PASS: TC-LOGIN-04");
    }

    // TC-LOGIN-05: correct username + wrong password
    public void loginFailWrongPwd() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        getScreenShot(getClass().getName() + "_wrongPwd");
        assert msg.equals("错误的用户名和密码");
        System.out.println("PASS: TC-LOGIN-05");
    }

    // TC-LOGIN-06: wrong username + correct password
    public void loginFailWrongUser() throws IOException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys("wrong");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String msg = driver.findElement(By.cssSelector("#root > div > div.content___2zk1- > div.main___x4OjT > div > form > div.ant-alert.ant-alert-error > div > div")).getText();
        getScreenShot(getClass().getName() + "_wrongUser");
        assert msg.equals("错误的用户名和密码");
        System.out.println("PASS: TC-LOGIN-06");
    }
}
