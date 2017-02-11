package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by checkbox on 1/30/17.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Fuffa", "Namratova", null, null, "[none]"));
      app.goToHomePage();
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, 1);
    }

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().openContactForEdit(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("Fuffa", "Namratova", "1111111111", "Naamratova@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
  }
}
