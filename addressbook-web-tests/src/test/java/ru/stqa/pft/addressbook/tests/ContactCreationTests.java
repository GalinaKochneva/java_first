package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        app.goToAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData("Fuffa", "Namratova", "1111111111", "Naamratova@gmail.com"));
        app.getContactHelper().inputContactCreation();
    }

}
