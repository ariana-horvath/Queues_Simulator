package model;

import java.util.List;

/**
 * The interface Strategy.
 */
public interface Strategy {

    /**
     * Add client.
     *
     * @param queues the queues
     * @param client the client
     */
     void addClient(List<Queue> queues, Client client);
}
