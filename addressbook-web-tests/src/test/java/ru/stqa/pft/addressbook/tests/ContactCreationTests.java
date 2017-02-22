package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/rose.jpg");
    ContactData contact = new ContactData().withFirstname("Fuffochka").withLastname("Namratova")
            .withAddress("1202 Emerald st., Mountain View, USA")
            .withEmail("Naamratova@gmail.com").withHomePhone("+7(927)5673353").withMobilePhone("22-22-22")
            .withWorkPhone("33 33 33").withGroup("[none]").withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())
                    .withAllPhones(mergePhones(contact)).withWorkPhone(null).withMobilePhone(null).withHomePhone(null))));
  }

  private String mergePhones(ContactData contact) {
    return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }


  @Test
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Fuffochka'").withLastname("Namratova").withGroup("[none]");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
