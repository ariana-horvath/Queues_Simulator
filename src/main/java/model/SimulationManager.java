package model;

import controller.MainController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The type Simulation manager.
 */
public class SimulationManager implements Runnable {
    private int timeLimit;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxServiceTime;
    private int minServiceTime ;
    private int numberOfQueues;
    private int numberOfClients;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private MainController controller;
    private List<Client> generatedClients;
    private boolean stop = false;
    public static int sumServiceTime = 0;
    public static int processedClients = 0;
    public static int sumWaitingTime = 0;
    private int peakHour;
    private int totalClients = 0;

    /**
     * Instantiates a new Simulation manager.
     * data is taken from the GUI
     * @param simulationTime  the simulation time
     * @param maxArrivalTime  the max arrival time
     * @param minArrivalTime  the min arrival time
     * @param maxServiceTime  the max service time
     * @param minServiceTime  the min service time
     * @param numberOfQueues  the number of queues
     * @param numberOfClients the number of clients
     * @param controller      the controller
     */
    public SimulationManager(int simulationTime, int maxArrivalTime, int minArrivalTime, int maxServiceTime, int minServiceTime,
                             int numberOfQueues, int numberOfClients, MainController controller ) {
        this.timeLimit = simulationTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.maxServiceTime = maxServiceTime;
        this.minServiceTime = minServiceTime;
        this.numberOfQueues = numberOfQueues;
        this.numberOfClients = numberOfClients;

        scheduler = new Scheduler(numberOfQueues, numberOfClients);
        generatedClients = new ArrayList<>();
        scheduler.changeStrategy(selectionPolicy); /** the strategy for distributing clients is by time */
        generateNRandomClients();
        this.controller = controller;
    }

    /** generates N clients with arrival and service time between min and max
     *  these will be the waiting clients
     * */
    private void generateNRandomClients() {
        int clientID = 0;
        int arrivalTime;
        int serviceTime;
        for (int i = 0; i < numberOfClients; i++) {
            clientID++;
            arrivalTime = (int)Math.floor(Math.random()*(maxArrivalTime-minArrivalTime+1)+minArrivalTime);
            serviceTime = (int)Math.floor(Math.random()*(maxServiceTime-minServiceTime+1)+minServiceTime);
            generatedClients.add( new Client(clientID, arrivalTime, serviceTime));
        }
        /** sort the clients by arrival time */
        Collections.sort(generatedClients);
    }

   /** the code to run when the thread starts*/
   @Override
   public void run() {
       int currentTime = 0;
       String outStr = "";
       try {    while (currentTime < timeLimit && !this.stop) { /**the simulation ends when the time passes or there are no clients left*/
                int nbClients = 0;
                boolean allEmpty = true;
                outStr = "\n";
                scheduler.writer.write("Time " + currentTime + "\nWaiting clients: ");
                outStr = outStr + "  Time " + currentTime + "\n  Waiting clients: ";
                Iterator<Client> iterator = generatedClients.iterator();
                while (iterator.hasNext()) {
                    Client c = iterator.next();
                    if (c.getArrivalTime() == currentTime) { /** when the arrival time of a client comes, he is distributed to a queue*/
                        scheduler.dispatchClient(c);
                        iterator.remove(); /** the client is removed from the waiting clients list*/
                    }
                }
                for (Client c : generatedClients) { /**display the waiting clients of the current time*/
                    scheduler.writer.write("(" + c.getClientID() + ", " + c.getArrivalTime() + ", " + c.getServiceTime() + "); ");
                    outStr = outStr + "(" + c.getClientID() + ", " + c.getArrivalTime() + ", " + c.getServiceTime() + "); ";
                }
                scheduler.writer.write('\n'); outStr = outStr + '\n';
                for (Queue q : scheduler.getQueues()) {
                    scheduler.writer.write("Queue " + (scheduler.getQueues().indexOf(q) + 1) + ": ");
                    outStr = outStr + "  Queue " + (scheduler.getQueues().indexOf(q) + 1) + ": ";
                    nbClients += q.getQueue().size();
                    if (q.getCurrentClient() == null) { /**the queue is empty*/
                        scheduler.writer.write(" closed\n");
                        outStr = outStr + " closed\n";
                    } else {
                        allEmpty = false;
                        nbClients++;
                        scheduler.writer.write("(" + q.getCurrentClient().getClientID() + ", " + q.getCurrentClient().getArrivalTime() + ", " +
                                                q.getCurrentClient().getServiceTime() + "); ");
                        outStr = outStr + "(" + q.getCurrentClient().getClientID() + ", " + q.getCurrentClient().getArrivalTime() + ", " +
                                q.getCurrentClient().getServiceTime() + "); ";
                        q.getCurrentClient().setServiceTime(q.getCurrentClient().getServiceTime() - 1); /**decrement the service time of the current client*/
                        for (Client c : q.getQueue()) { /**display the other clients in the queue*/
                            scheduler.writer.write("(" + c.getClientID() + " ," + c.getArrivalTime() + " ," + c.getServiceTime() + "); ");
                            outStr = outStr + "(" + c.getClientID() + " ," + c.getArrivalTime() + " ," + c.getServiceTime() + "); ";
                        }
                        scheduler.writer.write('\n'); outStr = outStr + '\n';
                        q.getWaitingTime().getAndDecrement(); /**decrement the waiting time for the current queue*/
                    }
                }
                if (totalClients < nbClients) {
                    totalClients = nbClients;
                    peakHour = currentTime;
                }
                scheduler.writer.write('\n'); outStr = outStr + '\n';
                controller.updateGUI(outStr);  /**update what is displayed in the GUI*/
                Thread.sleep(1000);
                currentTime++;
                if (allEmpty && generatedClients.isEmpty()) /**stop the simulation when there are no clients left (waiting or in the queues)*/
                    this.stop = true;
           }
           this.stop = true;
           for (Queue q : scheduler.getQueues()) { /**stop also the threads for the queues*/
                q.setStop(true);
           }
           double avgST = 0, avgWT = 0; /**calculate average waiting time and service time*/
           if (processedClients != 0) {
                avgST = sumServiceTime / (processedClients*1.0);
                avgST = Math.round(avgST*100)/100.0;
                avgWT = sumWaitingTime / (processedClients*1.0);
                avgWT = Math.round(avgWT*100)/100.0;
           }
           outStr = "\n"; /**display the results*/
           scheduler.writer.write("Average waiting time: "+ avgWT + '\n');
           outStr = outStr + "  Average waiting time: "+ avgWT + '\n';
           scheduler.writer.write("Average service time: "+ avgST + '\n');
           outStr = outStr + "  Average service time: "+ avgST + '\n';
           scheduler.writer.write("Peak hour: "+ peakHour + '\n');
           outStr = outStr +"  Peak hour: "+ peakHour + '\n';
           controller.updateGUI(outStr);
           scheduler.writer.close(); /**close the output file*/
       } catch (Exception e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
   }
}
