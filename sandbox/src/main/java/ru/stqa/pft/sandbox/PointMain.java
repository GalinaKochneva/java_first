package ru.stqa.pft.sandbox;

public class PointMain {
  public static void main(String[] args) {
    Point p1 = new Point(5, 15);
    Point p2 = new Point(5, 10);
    System.out.println("Расстояние между двумя точками = " + Point.distance(p1, p2));
  }
}
