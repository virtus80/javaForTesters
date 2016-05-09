package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Sasha on 24.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomePage();
    if (app.contact().getContacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Stepan").withLastName("Grigopyev").
              withAddress("69071, Tambov, Pushkinskaya st., 2").withMobilePhone("0(480)271-37-52").
              withEmail("grysha@gmail.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().getContacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).
    withFirstName("Igor2").withLastName("Matveev").withAddress("32061, Pskov, Stalevarov st., 2").
    withMobilePhone("0(482)112-37-52").withEmail("matveev@gmail.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().getContacts();
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
