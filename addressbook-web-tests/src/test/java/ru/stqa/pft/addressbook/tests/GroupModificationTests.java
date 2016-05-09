package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Sasha on 24.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("zest-ed1").withHeader("edit2").withFooter("testedit3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
