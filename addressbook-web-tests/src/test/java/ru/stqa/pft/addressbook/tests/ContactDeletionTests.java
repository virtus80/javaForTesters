package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().getContacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() -1));
    Contacts after = app.contact().getContacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
