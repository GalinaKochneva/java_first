package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsers() {
    click(By.xpath("//span[text()=' Manage ']"));
    click(By.linkText("Manage Users"));
  }
}
