package ru.stqa.pft.sandbox;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Galina");

      Square s = new Square(5);
      s.l = 5;
      System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

      Rectangle r = new Rectangle(4, 6);
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
      System.out.println("Hello, " + somebody + "!");
    }

    public static double area(Rectangle r) {
      return r.a * r.b;
    }

}
