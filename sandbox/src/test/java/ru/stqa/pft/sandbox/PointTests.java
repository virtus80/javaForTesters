package ru.stqa.pft.sandbox;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sasha on 18.04.2016.
 */
public class PointTests {

  @Test
  public void testDistance(){
    Point p1 = new Point(4, 3);
    Point p2 = new Point(8, 0);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testLineDistance(){
    Point p1 = new Point(4, 3);
    Point p3 = new Point(7, 3);
    Assert.assertEquals(p1.distance(p3), 3.0);
  }

  @Test
  public void testNegativeDistance(){
    Point p1 = new Point(4, 3);
    Point p4 = new Point(-4, -3);
    Assert.assertEquals(p1.distance(p4), 10.0);
  }
}
