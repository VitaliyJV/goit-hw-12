package org.example;

public class Timer implements Runnable {
    private final int frequency;

    public Timer(int frequency) {
        this.frequency = frequency;
        Thread th1 = new Thread(this);
        th1.start();
    }

    @Override
    public void run() {
        System.out.println("Let's start: ");
        try {
            while (true){
                Thread.sleep(frequency * 1000);
                synchronized (Message.class) {
                    Message.sendMessage(Integer.toString(++Message.count));
                    Message.class.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

