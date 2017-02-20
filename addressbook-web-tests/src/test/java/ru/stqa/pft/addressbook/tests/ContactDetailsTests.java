package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {


  @Test
  public void testContactDetails() {
    app.goToHomePage();
    Contacts before = app.contact().all();
    ContactData contact = before.iterator().next();
    ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
    String infoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

    assertThat(mergeContactDetails(contactFromEditForm), equalTo(infoFromDetailsPage));
  }

  private String mergeContactDetails(ContactData contact) {
    return String.format("%s %s\n%s\n\nH: %s\nM: %s\nW: %s\n\n%s",
            contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getHomePhone(),
            contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail());
  }
}
