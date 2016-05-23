package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "address")
  @Type(type="text")
  private String address;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "mobile")
  @Type(type="text")
  private String mobilePhone;

  @Column(name = "home")
  @Type(type="text")
  private String homePhone;

  @Column(name = "work")
  @Type(type="text")
  private String workPhone;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "email")
  @Type(type="text")
  private String email;

  @Column(name = "email2")
  @Type(type="text")
  private String email2;

  @Column(name = "email3")
  @Type(type="text")
  private String email3;

  @Expose
  @Transient
  private String group;

  @Column(name = "photo")
  @Type(type="text")
  private String photo = "";

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;


  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getAllPhones() { return allPhones; }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() { return homePhone; }

  public String getWorkPhone() { return workPhone; }

  public String getAllEmails() { return allEmails; }

  public String getEmail() {
    return email;
  }

  public String getEmail2() { return email2; }

  public String getEmail3() { return email3; }

  public String getGroup() {
    return group;
  }

  public File getPhoto() {
    if(photo == null) {
      photo = "";
    }
    return new File(photo); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + id;
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", photo='" + photo + '\'' +
            ", id=" + id +
            '}';
  }

}