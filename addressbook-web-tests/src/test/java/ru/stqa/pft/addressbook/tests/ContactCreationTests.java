package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().getContacts();
    ContactData contact = new ContactData().withFirstName("Stepan").withLastName("Nazarov").withAddress("69071, Tambov, Pushkinskaya st., 2").withMobilePhone("0(480)271-37-52").withEmail("grysha@gmail.com").withGroup("test-ed1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().getContacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().
            mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}