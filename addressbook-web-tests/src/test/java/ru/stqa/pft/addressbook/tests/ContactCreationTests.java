package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/rose.jpg");
    list.add(new Object[] {new ContactData().withFirstname("Ryan").withLastname("Tedder").withAddress("Colorado, USA")
            .withEmail("Ryan@gmail.com").withHomePhone("+7(927)5673353").withMobilePhone("22-22-22")
            .withWorkPhone("33 33 33").withPhoto(photo).withGroup("[none]")});
    list.add(new Object[] {new ContactData().withFirstname("Ryan 2").withLastname("Tedder 2").withAddress("Colorado, USA 2")
            .withEmail("Ryan@gmail.com 2").withHomePhone("+7(927)5673353 2").withMobilePhone("22-22-22 2")
            .withWorkPhone("33 33 33 3").withPhoto(photo)
            .withGroup("[none]")});
    list.add(new Object[] {new ContactData().withFirstname("Ryan 3").withLastname("Tedder 3")
            .withAddress("Colorado, USA 3").withEmail("Ryan@gmail.com 3").withHomePhone("+7(927)5673353 3")
            .withMobilePhone("22-22-22 3").withWorkPhone("33 33 33 3").withPhoto(photo).withGroup("[none]")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.contact().all();
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
