package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;

public class ContactDetailsTests extends TestBase {


  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = before.iterator().next();
    ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
    String infoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

    assertThat(infoFromDetailsPage,
            oneOf(mergeContactDetails(contactFromEditForm, true),
                    mergeContactDetails(contactFromEditForm, false)));
  }

  private String mergeContactDetails(ContactData contact, boolean withPhoto) {
    return String.format("%s %s\n%s%s\n\nH: %s\nM: %s\nW: %s\n\n%s",
            contact.getFirstname(), contact.getLastname(), withPhoto ? "\n" : "", contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail());
  }
}
