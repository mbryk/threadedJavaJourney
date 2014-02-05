package edu.cooper.ece465;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        for(int i=0; i<5; (new Thread(new Producer(drop,i++))).start());
        for(int i=0; i<5; (new Thread(new Consumer(drop,i++))).start());
    }
}
