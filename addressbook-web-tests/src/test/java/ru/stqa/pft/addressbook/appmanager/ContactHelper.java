package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void inputContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmail());
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void openContactForEdit() {
    click(By.xpath("//tr[@class='']/td[8]/a/img"));
  }
}