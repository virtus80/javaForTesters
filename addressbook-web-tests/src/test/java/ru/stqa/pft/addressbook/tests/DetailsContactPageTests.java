package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sasha on 10.05.2016.
 */
public class DetailsContactPageTests extends TestBase {

  @Test
  public void testDetailContactPage() {
    app.contact().gotoHomePage();
    ContactData contact = app.contact().getContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

    assertThat(contactInfoFromDetailsPage, equalTo(mergeContactData(contactInfoFromEditForm)));
  }

  private String mergeContactData (ContactData contact) {
    String name = contact.getFirstName() + ' ' + contact.getLastName();
    String address = contact.getAddress() + "\n";
    String homePhone = contact.getHomePhone();
    String mobilePhone = contact.getMobilePhone();
    String workPhone = contact.getWorkPhone();
    String email = "\n" + contact.getEmail();
    String email2 = contact.getEmail2();
    String email3 = contact.getEmail3();

    if (homePhone != "") {
      homePhone = "H: " + contact.getHomePhone();
    }

    if (mobilePhone != "") {
      mobilePhone = "M: " + contact.getMobilePhone();
    }

    if (workPhone != "") {
      workPhone = "W: " + contact.getWorkPhone();
    }

    return Arrays.asList(name, address, homePhone, mobilePhone, workPhone, email, email2, email3).stream().
            filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
