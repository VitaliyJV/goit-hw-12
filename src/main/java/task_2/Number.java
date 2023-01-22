package task_2;

public class Number implements Runnable {

    static int number=0;
    private static Object MONITOR = new Object();

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            synchronized (MONITOR) {
                increment();
            }
        }
    }

    public void increment() {
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number++;
        if(number%3==0 && number%5==0) System.out.println("fizzbuzz");
        else if(number%3==0) System.out.println("fizz");
        else if(number%5==0) System.out.println("buzz");
        else System.out.println(number);
    }
}


