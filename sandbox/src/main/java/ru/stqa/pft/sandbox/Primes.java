package ru.stqa.pft.sandbox;

/**
 * Created by Sasha on 03.05.2016.
 */
public class Primes {
  public static boolean isPrime (int n) {
    for (int i = 2; i < n; i++) {
      if (n%i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast (int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      if (n%i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrime (long n) {
    for (long i = 2; i < n; i++) {
      if (n%i == 0) {
        return false;
      }
    }
    return true;
  }
}
