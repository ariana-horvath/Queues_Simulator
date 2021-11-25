package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Queue - implements Runnable to define a thread.
 * -has the role of server
 */
public class Queue implements Runnable{

    /** the data structure for the queue of clients is BlockingQueue (concurrent collection) and it is volatile, to ensure thread safety */
    private volatile BlockingQueue<Client> queue;
    /** the waiting time is an atomic integer to ensure thread safety, avoiding compound actions */
    private AtomicInteger waitingTime;
    private Client currentClient = null;
    private boolean stop = false;

    /**
     * Instantiates a new Queue.
     */
    public Queue() {
        this.waitingTime = new AtomicInteger(0);
        this.queue = new ArrayBlockingQueue<>(1001);
    }

    /**
     * Add client.
     *
     * @param client the client
     */
    public void addClient(Client client) {
        /** adds a client to the queue */
        queue.add(client);
        /** increments the waiting time with the service
           time of the added client */
        waitingTime.getAndAdd(client.getServiceTime());
        client.setWaitingTime(waitingTime.get());
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                currentClient = queue.take();  /** save separately the current client (first from the queue) */
                int serviceTime = currentClient.getServiceTime();
                Thread.sleep(currentClient.getServiceTime()*1000);
                SimulationManager.processedClients++;  /** count the processed clients (they got out of the queue)*/
                SimulationManager.sumServiceTime += serviceTime;
                SimulationManager.sumWaitingTime += currentClient.getWaitingTime();
                currentClient = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets queue.
     *
     * @return the queue
     */
    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    /**
     * Gets waiting time.
     *
     * @return the waiting time
     */
    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    /**
     * Gets current client.
     *
     * @return the current client
     */
    public Client getCurrentClient() {
        return currentClient;
    }

    /**
     * Sets stop.
     *
     * @param stop the stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
