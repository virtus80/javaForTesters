package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Sasha on 24.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomePage();
    if (app.contact().getContacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Stepan").withLastName("Grigopyev").
              withAddress("69071, Tambov, Pushkinskaya st., 2").withMobilePhone("0(480)271-37-52").withEmail("grysha@gmail.com")
              .withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().getContacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().getContacts();
    Assert.assertEquals(before.size() -1, after.size());

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
