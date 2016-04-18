package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
/*
    System.out.println("Hello, world!");

    ru.stqa.pft.sandbox.Square s = new ru.stqa.pft.sandbox.Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    ru.stqa.pft.sandbox.Rectangle r = new ru.stqa.pft.sandbox.Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  */
    Point p1 = new Point(4, 3);
    Point p2 = new Point(8, 5);
    System.out.println("Растояние между точками = " + p1.distance(p2));
  }
}
