package controller;

import exception.InputValidationFailedException;
import model.SimulationManager;
import view.MainScreen;

/**
 * The type Main controller.
 * controls the relationship between the model classes and the view
 * it validates input strings, parse the input into integer numbers, starts the GUI and the thread for the simulation manager
 */
public class MainController {

    private SimulationManager simulationManager;
    private MainScreen mainScreen; /**the graphical user interface*/
    /**
     * Instantiates a new Main controller.
     */
    public MainController() {
        mainScreen = new MainScreen();
        start();
    }

    /**
     * will display a message if the user tries to leave input text field empty
     *
     * @param string - text field
     */
    public void validateTextField(String string) {
        if(string.compareTo("") == 0)
            throw new InputValidationFailedException("Input cannot be empty.");
    }

    /**
     * Validate input int.
     *
     * @param number the number from a text field from GUI
     * @return the int number
     */
    public int validateInput(String number) {
        int nb;
        try {
            nb = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InputValidationFailedException("Input is not a valid number.");
        }
        if (nb >= 0)
            return nb;
        else throw new InputValidationFailedException("Input cannot be negative.");
    }

    /**
     * start - initializes listeners, validates the input data, creates the simulation manager with the data and starts the application
     */
    public void start() {
        mainScreen.getStartSimulationButton().addActionListener(e -> {
            try {
                validateTextField(mainScreen.getNbOfClientsTextField().getText());
                int nbOfClients = validateInput(mainScreen.getNbOfClientsTextField().getText());
                validateTextField(mainScreen.getNbOfQueuesTextField().getText());
                int nbOfQueues = validateInput(mainScreen.getNbOfQueuesTextField().getText());
                validateTextField(mainScreen.getMaxArrivalTimeTextField().getText());
                int maxArrivalTime = validateInput(mainScreen.getMaxArrivalTimeTextField().getText());
                validateTextField(mainScreen.getMinArrivalTimeTextField().getText());
                int minArrivalTime = validateInput(mainScreen.getMinArrivalTimeTextField().getText());
                validateTextField(mainScreen.getMaxServiceTimeTextField().getText());
                int maxServiceTime = validateInput(mainScreen.getMaxServiceTimeTextField().getText());
                validateTextField(mainScreen.getMinServiceTimeTextField().getText());
                int minServiceTime = validateInput(mainScreen.getMinServiceTimeTextField().getText());
                validateTextField(mainScreen.getSimulationTimeTextField().getText());
                int simulationTime = validateInput(mainScreen.getSimulationTimeTextField().getText());

                simulationManager = new SimulationManager(simulationTime, maxArrivalTime, minArrivalTime,
                                 maxServiceTime, minServiceTime, nbOfQueues, nbOfClients, this);
                Thread thread = new Thread(simulationManager);
                thread.start();
            } catch (InputValidationFailedException exception) {
                mainScreen.displayErrorMessage(exception);
            }
        });

        mainScreen.getClearButton().addActionListener(e -> {
            mainScreen.getOutput().setText("");
        });
    }

    /**
     * Update gui.
     *
     * @param output the output string to be displayed on GUI
     */
    public void updateGUI(String output) {
        mainScreen.setTextArea(output);
    }
}
