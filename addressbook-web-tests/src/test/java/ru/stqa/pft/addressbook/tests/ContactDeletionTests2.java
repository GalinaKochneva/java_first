package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests2 extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Fuffa", "Namratova", null, null, "[none]"));
            app.goToHomePage();
            int after = app.getContactHelper().getContactCount();
            Assert.assertEquals(after, 1);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().openContactForEdit(before.size() - 1);
        app.getContactHelper().deleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
    }

}
