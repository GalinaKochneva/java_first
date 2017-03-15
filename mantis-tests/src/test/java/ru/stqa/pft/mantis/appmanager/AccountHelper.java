package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends HelperBase {

  public AccountHelper(ApplicationManager app) {
    super(app);
  }

  public void openUserByName(String username) {
    click(By.linkText(username));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public String findAnyUser() {
    return wd.findElements(By.xpath("//a[starts-with(@href, 'manage_user_edit_page.php?user_id=')]"))
            .stream().map(WebElement::getText).filter(user -> !"administrator".equals(user)).findFirst().get();
  }
}
