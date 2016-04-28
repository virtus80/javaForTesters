package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createContact(new ContactData("Stepan", "Grigopyev", "69071, Tambov, Pushkinskaya st., 2", "0(480)271-37-52", "grysha@gmail.com", "test1"));
  }


}