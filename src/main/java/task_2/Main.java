package task_2;

import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class Main {

    protected static final int MAX_NUM = 25;        // число n в условии задачи

    public static void main(String[] args) throws InterruptedException {    //Press "Start" to start the code
        Semaphore th1canTest = new Semaphore(0);
        Semaphore th2canTest = new Semaphore(0);
        Semaphore th3canTest = new Semaphore(0);
        Semaphore th4canTest = new Semaphore(0);

        Thread thC = new Thread(new Number(th1canTest, th4canTest) {
            @Override
            public void testAndPrint(int number) {
                if (number % 3 == 0 && number % 5 == 0) {
                    System.out.print("fizzbuzz, ");
                }
            }
        }, "C");
        Thread thA = new Thread(new Number(th2canTest, th4canTest) {
            @Override
            public void testAndPrint(int number) {
                if (number % 3 == 0 && number % 5 != 0) {
                    System.out.print("fizz, ");
                }
            }
        }, "A");
        Thread thB = new Thread(new Number(th3canTest, th4canTest) {
            @Override
            public void testAndPrint(int number) {
                if (number % 5 == 0 && number % 3 != 0) {
                    System.out.print("buzz, ");
                }
            }
        }, "B");

        Thread thD = new Thread(() -> {

            for (int i = 0; i < MAX_NUM; i++) {
                // System.err.println("thread " + Thread.currentThread().getName() + " before increment");

                Number.number++;
                th1canTest.release();
                th2canTest.release();
                th3canTest.release();

                // System.err.println("thread " + Thread.currentThread().getName() + " before th4canTest.acquire");

                try {
                    th4canTest.acquire(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // System.err.println("thread " + Thread.currentThread().getName() + " after th4canTest.acquire");

                int number = Number.number;
                if (number % 3 != 0 && number % 5 != 0) {
                    System.out.print(number + ", ");
                }
            }
        }, "D");

        thA.start();
        thB.start();
        thC.start();
        thD.start();

        while (Stream.of(thA, thB, thC, thC, thD).anyMatch(Thread::isAlive)) {
            Thread.sleep(1000);
        }
    }
}

/*
Задание 2
Напишите программу, которая выводит в консоль строку, состоящую из чисел от 1 до n, но с заменой некоторых значений:

если число делится на 3 - вывести "fizz"
если число делится на 5 - вывести "buzz"
если число делится на 3 и на 5 - вывести "fizzbuzz"
Например, для n = 15, ожидаемый результат:
1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.

Программа должна быть многопоточной, работать с 4 потоками:
Поток A вызывает fizz() чтобы проверить делимость на 3 и вывести fizz.
Поток B вызывает buzz() чтобы проверить делимость на 5 и вывести buzz.
Поток C вызывает fizzbuzz() чтобы проверить делимость на 3 и 5 и вывести fizzbuzz.
Поток D вызывает number() чтобы вывести число.
 */
