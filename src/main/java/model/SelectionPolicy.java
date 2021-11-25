package model;

/**
 * The enum Selection policy - for choosing the strategy for the queues.
 */
public enum SelectionPolicy {
    /**
     * Shortest queue selection policy.
     */
    SHORTEST_QUEUE,
    /**
     * Shortest time selection policy.
     */
    SHORTEST_TIME
}
