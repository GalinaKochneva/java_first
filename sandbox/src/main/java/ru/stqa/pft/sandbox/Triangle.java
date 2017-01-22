package ru.stqa.pft.sandbox;

/**
 * Created by checkbox on 1/22/17.
 */
public class Triangle {
  public double h;
  public double a;

  public Triangle(double h, double a) {
    this.h = h;
    this.a = a;
  }
  public double area() {
    return this.h * this.a;
  }
}
