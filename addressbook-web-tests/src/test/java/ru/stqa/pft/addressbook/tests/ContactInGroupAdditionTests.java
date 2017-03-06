package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactInGroupAdditionTests extends TestBase {

  private int contactId;
  private final String groupName = "group for " + getClass().getSimpleName();

  @BeforeClass
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
  public void testContactInGroupAddition() {
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

  @Test(dependsOnMethods = "testContactInGroupAddition")
  public void testContactInGroupDeletion() {
    app.goTo().homePage();
    //выбрать нужную группу в group
    app.contact().filterContactsByGroup(groupName);
    //выделить нужный контакт
    app.contact().selectContactById(contactId);
    //нажать remove
    app.contact().removeSelectedContactsFromGroup();
    app.goTo().homePage();
    //выбрать контакт с нужным id
    @SuppressWarnings("OptionalGetWithoutIsPresent") ContactData modifiedContact =
            app.db().contacts().stream().filter(contactData -> contactData.getId() == contactId).findAny().get();
    //проверить, что никаких групп у этого контакта не указано
    assertTrue(modifiedContact.getGroups().isEmpty());
  }
}
