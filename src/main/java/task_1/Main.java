package task_1;

public class Main {         //Press "Start" to start the code
    public static void main(String[] args) {
        new Timer(1);
        new Waiter(5);
    }
}

/*
Задание 1
Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии
(запуска программы).

Другой ее поток выводит каждые 5 секунд сообщение "Прошло 5 секунд". Предусмотрите возможность ежесекундного оповещения
потока, воспроизводящего сообщение, потоком, отсчитывающим время.
 */