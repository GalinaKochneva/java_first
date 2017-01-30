package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  private FirefoxDriver wd;

  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private NavigationHelper navigationHelper;

  public void init() {
    wd = new FirefoxDriver(new FirefoxBinary(new File("/Users/checkbox/Applications/Firefox.app/Contents/MacOS/firefox")), new FirefoxProfile());
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void gotoGroupPage() {
    navigationHelper.gotoGroupPage();
  }

  public void goToAddNewPage() {
    navigationHelper.goToAddNewPage();
  }

  public void stop() {
    wd.quit();
  }

  public void goToHomePage() {
    navigationHelper.goToHomePage();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
