package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by checkbox on 1/25/17.
 */
public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(5, 15);
    Point p2 = new Point(5, 10);
    Assert.assertEquals(Point.distance(p1, p2), 5.0);
  }
}
