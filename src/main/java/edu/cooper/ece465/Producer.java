package edu.cooper.ece465;

import java.util.Random;
import java.util.ArrayList;

public class Producer implements Runnable {
    private Drop drop;
    private int listLength = 5;
    private int seqnum;

    public Producer(Drop drop,int seqnum) {
        drop.newProducer();
        this.drop = drop;
        this.seqnum = seqnum;
    }

    public void run() {
        ArrayList<Data> list = new ArrayList<Data>();
        for(int i = 0; i<listLength-1; list.add(new Data(i++)));

        Random random = new Random();

        for (int i = 0;
             i < listLength;
             i++) {
            drop.put(list.get(i));
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.doneProducer();
    }
}
