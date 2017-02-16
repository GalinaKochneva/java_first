package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests2 extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Fuffa", "Namratova", null, null, "[none]"));
            app.goToHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().openContactForEdit(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
