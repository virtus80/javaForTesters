package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().getContacts();
    ContactData contact = new ContactData().withFirstName("Stepan").withLastName("Nazarov").withAddress("69071, Tambov, Pushkinskaya st., 2").withMobilePhone("0(480)271-37-52").withEmail("grysha@gmail.com").withGroup("test-ed1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().getContacts();
    Assert.assertEquals(before.size() + 1, after.size());

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}