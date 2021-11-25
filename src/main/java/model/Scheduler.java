package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Scheduler.
 * sends tasks(clients) to servers(queues) according to the established strategy
 */
public class Scheduler {

    private List<Queue> queues;
    private int maxNoQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;
    /**
     * The Writer.
     */
    public FileWriter writer;

    /**
     * Instantiates a new Scheduler.
     *
     * @param maxNoQueues        the max no queues
     * @param maxClientsPerQueue the max clients per queue
     */
    public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
        this.queues = new ArrayList<>();
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;
        this.strategy = new ConcreteStrategyQueue();
        /** create and start a thread for each queue */
        Thread[] threads = new Thread[maxNoQueues];
        for (int i = 0; i < maxNoQueues; i++) {
            Queue queue = new Queue();
            threads[i] = new Thread(queue);
            queues.add(queue);
            threads[i].start();
        }
        try {
            writer = new FileWriter("LogOfEvents.txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change strategy.
     *
     * @param policy the policy
     */
    public void changeStrategy (SelectionPolicy policy) {

        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }

        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    /**
     * Dispatch client.
     *
     * @param c the client to be added
     */
    public void dispatchClient (Client c) {
        strategy.addClient(queues, c);
    }

    /**
     * Gets queues.
     *
     * @return the queues
     */
    public List<Queue> getQueues() {
        return queues;
    }
}
