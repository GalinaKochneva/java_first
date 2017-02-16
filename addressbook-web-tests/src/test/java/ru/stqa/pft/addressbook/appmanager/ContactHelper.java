package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  private final NavigationHelper navigationHelper;

  public ContactHelper(WebDriver wd, NavigationHelper navigationHelper) {
    super(wd);
    this.wd = wd;
    this.navigationHelper = navigationHelper;
  }

  public void inputContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void openContactForEdit(int index) {
    wd.findElements(By.xpath("//tr/td[8]/a/img")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    navigationHelper.goToAddNewPage();
    fillContactForm(contact, true);
    inputContactCreation();
    navigationHelper.homePage();
  }

  public void modify(int index, ContactData contact) {
    openContactForEdit(index);
    fillContactForm(contact, false);
    submitContactModification();
    goToHomePage();
  }
  public void delete(int index) {
    openContactForEdit(index);
    deleteContact();
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firsttName = element.findElement(By.xpath("td[3]")).getText();
      String email = element.findElement(By.xpath("td[5]")).getText();
      String mobile = element.findElement(By.xpath("td[6]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firsttName, lastName, mobile, email, "[none]");
      contacts.add(contact);
    }
    return contacts;
  }
}