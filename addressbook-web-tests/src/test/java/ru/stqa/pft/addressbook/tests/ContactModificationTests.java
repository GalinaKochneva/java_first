package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by checkbox on 1/30/17.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Fuffa", "Namratova", null, null, "[none]"));
      app.goToHomePage();

    }

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().openContactForEdit(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Fuffa", "Namratova", "1111111111", "Naamratova@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
