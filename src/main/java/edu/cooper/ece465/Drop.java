package edu.cooper.ece465;

public class Drop {
    // Message sent from producer
    // to consumer.
    private Data data;
    private int producers = 0;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public synchronized Data take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                if(done()) return null;
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return data;
    }

    public synchronized void put(Data data) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.data = data;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }

    public synchronized void newProducer(){
        producers++;
    }
    public synchronized void doneProducer(){
        producers--;
    }

    public boolean done(){
        return (producers==0) && empty;
    }
}
