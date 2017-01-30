package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests2 extends TestBase {

    @Test
    public void testContactDeletion() {
        openContactForEdit();
        deleteContact();
    }

}
