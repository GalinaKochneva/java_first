package ru.stqa.pft.sandbox;

/**
 * Created by checkbox on 1/22/17.
 */
public class TriangleMain {
  public static void main(String[] args) {
    Triangle s = new Triangle(5, 10);
    System.out.println("Площадь треугольника с высотой " + s.h + " и половиной основания " + s.a + " = " + s.area());
  }
}
