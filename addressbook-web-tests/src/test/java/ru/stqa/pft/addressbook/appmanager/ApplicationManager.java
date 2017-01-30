package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  protected FirefoxDriver wd;

  private ContactHelper contactHelper;
  private GroupHelper groupHelper;

  public ApplicationManager() {
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
      try {
          wd.switchTo().alert();
          return true;
      } catch (NoAlertPresentException e) {
          return false;
      }
  }

  public void init() {
    wd = new FirefoxDriver(new FirefoxBinary(new File("/Users/checkbox/Applications/Firefox.app/Contents/MacOS/firefox")), new FirefoxProfile());
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    login("admin", "secret");
  }

  private void login(String username, String password) {
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
  }

  public void goToAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
  }

  public void stop() {
    wd.quit();
  }

  public void goToHomePage() {
      wd.findElement(By.linkText("home")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
