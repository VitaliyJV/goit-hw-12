package org.example;

public class Message {
    volatile static int count = 0;

    synchronized static void sendMessage(String s){
        System.out.println(s);
    }
}
