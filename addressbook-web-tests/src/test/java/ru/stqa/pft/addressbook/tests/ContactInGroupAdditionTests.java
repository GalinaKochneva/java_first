package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupAdditionTests extends TestBase {

  private int contactId;
  private final String groupName = "group for " + getClass().getSimpleName();

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(new ContactData().withFirstname("Fuffa").withLastname("Namratova"));
    //noinspection OptionalGetWithoutIsPresent
    contactId = app.db().contacts().stream().mapToInt(ContactData::getId).max().getAsInt();

    if (app.db().groups().stream().noneMatch(g -> g.getName().equals(groupName))) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(groupName));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactGroupAddition() {
    // взять контакт с максимальным id
    // выделяем этот контакт на странице home
    app.contact().selectContactById(contactId);
    // выбрать группу в списке to_group
    // нажать кнопку с именем add
    app.contact().addSelectedContactsToGroup(groupName);
    // проверить, что в этой группе есть такой контакт
    @SuppressWarnings("OptionalGetWithoutIsPresent") ContactData modifiedContact =
            app.db().contacts().stream().filter(contactData -> contactData.getId() == contactId).findAny().get();
    assertThat(modifiedContact.getGroups().stream().map(GroupData::getName).collect(Collectors.toList()),
            equalTo(Collections.singletonList(groupName)));
  }
}
