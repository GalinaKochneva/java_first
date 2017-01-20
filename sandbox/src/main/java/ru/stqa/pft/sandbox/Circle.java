package ru.stqa.pft.sandbox;

/**
 * Created by checkbox on 1/20/17.
 */
public class Circle {
  public static final double π = 3.14;
  public double r;
  public double l;

  public Circle(double r) {
    this.r = r;
    this.l = l;
  }

  public double area() {
    return this.π * this.r * this.r;
  }
  public double length() {
    return 2 * this.π * this.r;
  }
}
