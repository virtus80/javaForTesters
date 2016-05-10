package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Sasha on 09.05.2016.
 */
public class ContactFieldTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.contact().gotoHomePage();
    ContactData contact = app.contact().getContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  @Test
  public void testContactAddresses() {
    app.contact().gotoHomePage();
    ContactData contact = app.contact().getContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleanedAddresses(contactInfoFromEditForm.getAddress())));
  }

  @Test
  public void testContactEmails() {
    app.contact().gotoHomePage();
    ContactData contact = app.contact().getContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).
            stream().filter((s) -> ! s.equals("")).map(ContactFieldTests::cleanedPhones).collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedAddresses(String address) { return address.replaceAll("\\s{2,}", " ");
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).
            stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
