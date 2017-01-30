package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by checkbox on 1/29/17.
 */
public class SessionHelper extends BaseHelper {

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }
  public void login(String username, String password) {
    type(By.name("pass"), username);
    type(By.name("pass"), password);

    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
