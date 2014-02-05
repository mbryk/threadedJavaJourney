package edu.cooper.ece465;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private int seqnum;
    private int consumed = 0;

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

            if(message==null) break;

            System.out.format("Consumer #%d: Received=%d%n", seqnum,message.number);
            consumed++;
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {};
        }
        System.out.format("Consumer #%d: Total Consumed=%d%n", seqnum,consumed);
    }
}
