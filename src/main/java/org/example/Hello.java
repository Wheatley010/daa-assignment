package org.example;

public class Hello {
    // метод, который можно потом протестировать
    public int sum(int a, int b) {
        return a + b;
    }

    // простой main — для запуска и проверки
    public static void main(String[] args) {
        Hello h = new Hello();
        System.out.println("2 + 2 = " + h.sum(2, 2));
    }
}
