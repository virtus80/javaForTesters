package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sasha on 24.05.2016.
 */
public class ContactInGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test-contacts1"));
    }

    if (app.db().contacts().size() == 0) {
      app.contact().gotoHomePage();
      app.contact().create(new ContactData().withFirstName("Stepan").withLastName("Grigopyev").
              withAddress("69071, Tambov, Pushkinskaya st., 2").withMobilePhone("0(480)271-37-52").withEmail("grysha@gmail.com")
              .inGroup(app.db().groups().iterator().next()));
    }
  }

  @Test
  public void addContactToGroupTest() {
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
    Groups allGroups = app.db().groups();
    Groups contactGroups = selectedContact.getGroups();
    Groups selectedGroups = new Groups(allGroups);
    selectedGroups.removeAll(contactGroups); // choose groups in which contact isn't added
    if (selectedGroups.size() > 0) {
      GroupData selectedGroup = selectedGroups.iterator().next();
      app.contact().gotoHomePage();
      app.contact().addToGroup(selectedContact, selectedGroup);
      assertThat(contactGroups, equalTo(contactGroups.withAdded(selectedGroup)));
    } else {
      System.out.println("Contact added to all possible groups");
    }
  }

  @Test
  public void removeContactFromGroupTest() {
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
    Groups contactGroups = selectedContact.getGroups();
    if (contactGroups.size() == 0) {
      app.contact().gotoHomePage();
      app.contact().addToGroup(selectedContact, app.db().groups().iterator().next());
    }
    GroupData selectedGroup = contactGroups.iterator().next();
    app.contact().gotoHomePage();
    app.contact().removeFromGroup(selectedContact, selectedGroup);
    assertThat(contactGroups, equalTo(contactGroups.without(selectedGroup)));
  }
}
