package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
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
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void openContactForEdit(int index) {
    wd.findElements(By.xpath("//tr/td[8]/a/img")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void openContactForEditById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
    contactCache = null;
    navigationHelper.homePage();
  }

  public void modify(ContactData contact) {
    openContactForEditById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    goToHomePage();
  }

  public void delete(int index) {
    openContactForEdit(index);
    deleteContact();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCache = null;
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String email = element.findElement(By.xpath("td[5]")).getText();
      String[] phones = (element.findElement(By.xpath("td[6]")).getText() + "\n\n").split("\n", 10);
      contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]).withEmail(email));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    openContactForEditById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}