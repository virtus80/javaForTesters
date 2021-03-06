package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

/**
 * Created by Sasha on 24.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoContactPage() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void toHomePage() {
    click(By.linkText("home page"));
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public int count() {
    return wd.findElements(By.name("entry")).size();
  }

  private void selectContactById(int id) {

    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  private void editSelectedContact(int id) {
    wd.findElement(By.xpath(String.format
            ("//input[@id='%s']/../..//img[@title='Edit']", id))).click();
  }

  private void viewDetailsSelectedContact(int id) {
    wd.findElement(By.xpath(String.format
            ("//input[@id='%s']/../..//img[@title='Details']", id))).click();
  }

  private void selectGroupByName(String name) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
  }

  private void selectGroupForRemoving(String name) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
  }

  private void addSelectedContactToGroup() {
    wd.findElement(By.name("add")).click();
  }

  private void removeSelectedContactFromGroup() { wd.findElement(By.name("remove")).click(); }



  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    toHomePage();
  }

  public void modify(ContactData contact) {
    editSelectedContact(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    toHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptAlert();
    contactCache = null;
    gotoHomePage();
  }

  private Contacts contactCache = null;

  public Contacts getContacts() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    Contacts contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement element : rows) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).
              withAllEmails(allEmails).withAllPhones(allPhones).inGroup(app.db().groups().iterator().next()));
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editSelectedContact(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).
            withLastName(lastname).withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).
            withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public String infoFromDetailsPage(ContactData contact) {
    viewDetailsSelectedContact(contact.getId());
    String details = wd.findElement(By.id("content")).getText();
    wd.navigate().back();
    return details;
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupByName(group.getName());
    addSelectedContactToGroup();
    gotoHomePage();
  }

  public void removeFromGroup(ContactData contact, GroupData group) {
    selectGroupForRemoving(group.getName());
    selectContactById(contact.getId());
    removeSelectedContactFromGroup();
    gotoHomePage();
  }



}
