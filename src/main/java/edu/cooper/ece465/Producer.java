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
        for(int i = 0; i<listLength; list.add(new Data(i++)));

        Random random = new Random();

        for (int j = 0;
             j < listLength;
             j++) {
            drop.put(list.get(j));
            System.out.format("Producer #%d: Sent=%d%n", seqnum,list.get(j).number);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.doneProducer();
    }
}
