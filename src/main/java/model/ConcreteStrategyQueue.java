package model;

import java.util.List;

/**
 * The type Concrete strategy queue.
 */
public class ConcreteStrategyQueue implements Strategy{
    /** the strategy for adding clients is by the queue size, a new client goes in the queue where are the least nb of clients */
    @Override
    public void addClient(List<Queue> queues, Client client) {
        int queueID = 0;
        int minSize = Integer.MAX_VALUE;

        for (Queue q : queues) {
            if (q.getQueue().size() < minSize) {
                minSize = q.getQueue().size();
                /** save the index of the queue with minimum size */
                queueID = queues.indexOf(q);
            }
        }
        /** add the client to the queue with saved ID */
        queues.get(queueID).addClient(client);
    }
}
