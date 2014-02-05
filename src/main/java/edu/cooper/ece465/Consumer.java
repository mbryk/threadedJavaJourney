package edu.cooper.ece465;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private int seqnum;

    public Consumer(Drop drop, int seqnum) {
        this.drop = drop;
        this.seqnum = seqnum;
    }

    public void run() {
        Random random = new Random();
        Data message;
        for (message = drop.take();
             ! drop.done();
             message = drop.take()) {

            System.out.format("#%d: Received=%d%n", seqnum,message.number);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {};
        }
        drop.put(message);
    }
}
