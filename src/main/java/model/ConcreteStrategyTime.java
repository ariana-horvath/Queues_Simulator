package model;

import java.util.List;

/**
 * The type Concrete strategy time.
 */
public class ConcreteStrategyTime implements Strategy{
    /** the strategy for adding clients is by the waiting time, a new client goes in the queue where he has the least
     * time to wait to be served */
    @Override
    public void addClient(List<Queue> queues, Client client) {
        int queueID = 0;
        int minTime = Integer.MAX_VALUE;

        for (Queue q : queues) {
            if (q.getWaitingTime().get() < minTime) {
                minTime = q.getWaitingTime().get();
                /** save the index of the queue with minimum waiting time */
                queueID = queues.indexOf(q);
            }
        }
        /** add the client to the queue with saved ID */
        queues.get(queueID).addClient(client);
    }
}
