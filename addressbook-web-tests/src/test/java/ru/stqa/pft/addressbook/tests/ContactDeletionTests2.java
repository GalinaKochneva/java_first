package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests2 extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().openContactForEdit();
        app.getContactHelper().deleteContact();
    }

}
