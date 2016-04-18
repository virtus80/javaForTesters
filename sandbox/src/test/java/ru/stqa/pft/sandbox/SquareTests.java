package ru.stqa.pft.sandbox;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sasha on 18.04.2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }
}
