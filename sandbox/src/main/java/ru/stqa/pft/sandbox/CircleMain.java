package ru.stqa.pft.sandbox;

/**
 * Created by checkbox on 1/20/17.
 */
public class CircleMain {
  public static void main(String[] args) {
    Circle s = new Circle(5);
    System.out.println("Площадь круга с радиусом " + s.r + " = " + s.area());
    System.out.println("Длина окружности круга с радиусом " + s.r + " = " + s.length());
  }
}
