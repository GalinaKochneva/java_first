package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {

        app.goToHomePage();
        app.contact().selectContact();
        app.contact().deleteSelectedContact();
        app.goToHomePage();

    }

}
