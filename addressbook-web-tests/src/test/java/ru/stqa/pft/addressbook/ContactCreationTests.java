package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        goToAddNewPage();
        fillContactForm(new ContactData("Galina", "Kochneva", "9178731135", "checkbox88@gmail.com"));
        inputContactCreation();
    }

}
