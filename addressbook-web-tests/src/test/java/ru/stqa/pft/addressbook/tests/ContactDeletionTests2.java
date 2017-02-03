package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests2 extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Fuffa", "Namratova", null, null, "[none]"));
            app.goToHomePage();
        }
        app.getContactHelper().openContactForEdit();
        app.getContactHelper().deleteContact();
    }

}
