package ru.stqa.pft.addressbook.generators;


import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);

  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()
              , contact.getEmail(), contact.getAllPhones()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Ryan %s", i))
              .withLastname(String.format("Tedder %s", i)).withAddress(String.format("Colorado, USA %s", i))
              .withEmail(String.format("Ryan@gmail.com %s", i)).withAllPhones(String.format("777777777 \n 333333 \n 888888 %s", i)));
    }
    return contacts;
  }
}
