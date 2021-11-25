package model;

/**
 * The type Client.
 * - has the role of task
 */
public class Client implements Comparable<Client> {
    private int clientID;
    private int arrivalTime;
    private int serviceTime;
    /** the time the client spends waiting in the queue and in the front of the queue*/
    private int waitingTime = 0;

    /**
     * Instantiates a new Client.
     *
     * @param clientID    the client id
     * @param arrivalTime the arrival time
     * @param serviceTime the service time
     */
    public Client(int clientID, int arrivalTime, int serviceTime) {
        this.clientID = clientID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    /** used to sort the clients by their arrival time */
    @Override
    public int compareTo(Client client) {
        if (client.getArrivalTime() == this.getArrivalTime())
            return 0;
        else
            if (client.getArrivalTime() < this.getArrivalTime())
                return 1;
            else
                return -1;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Gets arrival time.
     *
     * @return the arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Gets service time.
     *
     * @return the service time
     */
    public int getServiceTime() {
        return serviceTime;
    }

    /**
     * Sets service time.
     *
     * @param serviceTime the service time
     */
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * Gets waiting time.
     *
     * @return the waiting time
     */
    public int getWaitingTime() {
        return waitingTime;
    }

    /**
     * Sets waiting time.
     *
     * @param waitingTime the waiting time
     */
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
