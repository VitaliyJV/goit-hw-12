package org.example;

public class Waiter implements Runnable{
    private final int period;
    private final String mes;

    public Waiter(int period){
        this.period = period;
        this.mes = "Прошло " + period + " сек.";
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true)
                synchronized (Message.class) {
                    Message.class.wait();
                    if (Message.count % period == 0)
                        Message.sendMessage(mes);
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
