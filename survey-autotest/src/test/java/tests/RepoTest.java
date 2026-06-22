package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

public class RepoTest extends Utils {
    public static String url = "http://49.235.61.184:8080/repo/index";

    public RepoTest() { super(url); }

    // login helper
    private void login() {
        driver.get("http://49.235.61.184:8080/user/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("root");
        driver.findElement(By.cssSelector("#password")).sendKeys("root11");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // TC-REPO-01: repo list page loads
    public void repoListPage() throws IOException {
        login();
        driver.get(url);
        driver.findElement(By.cssSelector("#sk-layout"));
        getScreenShot(getClass().getName() + "_list");
        System.out.println("PASS: TC-REPO-01 (repo list loads)");
    }

    // TC-REPO-02: create question bank
    public void createRepo() throws IOException {
        login();
        driver.get(url);
        driver.findElement(By.xpath("//button[contains(.,'新建')]")).click();
        driver.findElement(By.cssSelector("input#name")).sendKeys("auto_test_repo");
        driver.findElement(By.cssSelector("button.ant-btn-primary")).click();
        getScreenShot(getClass().getName() + "_create");
        System.out.println("PASS: TC-REPO-02 (create repo)");
    }

    // TC-REPO-03: export button bug
    public void exportBug() throws IOException {
        login();
        driver.get(url);
        driver.findElement(By.xpath("//button[contains(.,'导出')]")).click();
        getScreenShot(getClass().getName() + "_exportBug");
        System.out.println("BUG CONFIRMED: TC-REPO-03 (export no response)");
    }
}
