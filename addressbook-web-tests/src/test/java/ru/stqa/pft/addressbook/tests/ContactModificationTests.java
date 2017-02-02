package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by checkbox on 1/30/17.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().openContactForEdit();
    app.getContactHelper().fillContactForm(new ContactData("Fuffa", "Namratova", "1111111111", "Naamratova@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.goToHomePage();
  }
}
