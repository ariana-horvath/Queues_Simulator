package view;

import javax.swing.*;
import java.awt.*;

/**
 * The type Main screen.
 */
public class MainScreen extends JFrame{

    private JTextField nbOfClientsTextField;
    private JTextField nbOfQueuesTextField;
    private JTextField simulationTimeTextField;
    private JTextField minServiceTimeTextField;
    private JTextField maxServiceTimeTextField;
    private JTextField minArrivalTimeTextField;
    private JTextField maxArrivalTimeTextField;
    private JTextArea output = new JTextArea();
    private JScrollPane scroll;
    private JButton startSimulationButton;
    private JButton clearButton;

    /**
     * Instantiates a new Main screen.
     */
    public MainScreen() {
        this.setTitle("Queues Simulator");
        this.setSize(500, 650);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setLocationRelativeTo(null);

        initializeForm(panel);

        this.setContentPane(panel);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(182, 175, 246));
    }

    private void initializeForm(JPanel panel) {
        JLabel messageLabel = new JLabel("INTRODUCE INPUT:");
        messageLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(25, 8, 108));
        messageLabel.setBounds(20, 15, 300, 30);
        panel.add(messageLabel);

        JLabel messageLabel2 = new JLabel("(only positive integers)");
        messageLabel2.setBounds(20, 33, 300, 30);
        messageLabel2.setFont(new Font("Calibri", Font.PLAIN, 14));
        messageLabel2.setForeground(new Color(15, 7, 88));
        panel.add(messageLabel2);

        JLabel simulationTimeLabel = new JLabel("Simulation time:");
        simulationTimeLabel.setBounds(250, 20, 150, 30);
        simulationTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        simulationTimeLabel.setForeground(new Color(15, 7, 88));
        panel.add(simulationTimeLabel);

        simulationTimeTextField = new JTextField();
        simulationTimeTextField.setBounds(390, 20, 70, 30);
        simulationTimeTextField.setBackground(new Color(198, 192, 250));
        panel.add(simulationTimeTextField);

        JLabel nbOfClientsLabel = new JLabel("Number of clients:");
        nbOfClientsLabel.setBounds(20, 70, 150, 30);
        nbOfClientsLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        nbOfClientsLabel.setForeground(new Color(15, 7, 88));
        panel.add(nbOfClientsLabel);

        nbOfClientsTextField = new JTextField();
        nbOfClientsTextField.setBounds(160, 70, 70, 30);
        nbOfClientsTextField.setBackground(new Color(198, 192, 250));
        panel.add(nbOfClientsTextField);

        JLabel nbOfQueuesLabel = new JLabel("Number of queues:");
        nbOfQueuesLabel.setBounds(250, 70, 150, 30);
        nbOfQueuesLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        nbOfQueuesLabel.setForeground(new Color(15, 7, 88));
        panel.add(nbOfQueuesLabel);

        nbOfQueuesTextField = new JTextField();
        nbOfQueuesTextField.setBounds(390, 70, 70, 30);
        nbOfQueuesTextField.setBackground(new Color(198, 192, 250));
        panel.add(nbOfQueuesTextField);

        JLabel minArrivalTimeLabel = new JLabel("Minimum arrival time:");
        minArrivalTimeLabel.setBounds(20, 120, 150, 30);
        minArrivalTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        minArrivalTimeLabel.setForeground(new Color(15, 7, 88));
        panel.add(minArrivalTimeLabel);

        minArrivalTimeTextField = new JTextField();
        minArrivalTimeTextField.setBounds(160, 120, 70, 30);
        minArrivalTimeTextField.setBackground(new Color(198, 192, 250));
        panel.add(minArrivalTimeTextField);

        JLabel maxArrivalTimeLabel = new JLabel("Maximum arrival time:");
        maxArrivalTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        maxArrivalTimeLabel.setForeground(new Color(15, 7, 88));
        maxArrivalTimeLabel.setBounds(250, 120, 150, 30);
        panel.add(maxArrivalTimeLabel);

        maxArrivalTimeTextField = new JTextField();
        maxArrivalTimeTextField.setBounds(390, 120, 70, 30);
        maxArrivalTimeTextField.setBackground(new Color(198, 192, 250));
        panel.add(maxArrivalTimeTextField);

        JLabel minServiceTimeLabel = new JLabel("Minimum service time:");
        minServiceTimeLabel.setBounds(20, 170, 150, 30);
        minServiceTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        minServiceTimeLabel.setForeground(new Color(15, 7, 88));
        panel.add(minServiceTimeLabel);

        minServiceTimeTextField = new JTextField();
        minServiceTimeTextField.setBounds(160, 170, 70, 30);
        minServiceTimeTextField.setBackground(new Color(198, 192, 250));
        panel.add(minServiceTimeTextField);

        JLabel maxServiceTimeLabel = new JLabel("Maximum service time:");
        maxServiceTimeLabel.setBounds(250, 170, 150, 30);
        maxServiceTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        maxServiceTimeLabel.setForeground(new Color(15, 7, 88));
        panel.add(maxServiceTimeLabel);

        maxServiceTimeTextField = new JTextField();
        maxServiceTimeTextField.setBounds(390, 170, 70, 30);
        maxServiceTimeTextField.setBackground(new Color(198, 192, 250));
        panel.add(maxServiceTimeTextField);

        startSimulationButton = new JButton("Start simulation");
        startSimulationButton.setBounds(140, 240, 210, 30);
        startSimulationButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        startSimulationButton.setForeground(new Color(15, 7, 88));
        panel.add(startSimulationButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(140, 570, 210, 30);
        clearButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        clearButton.setForeground(new Color(15, 7, 88));
        panel.add(clearButton);

        JLabel outputLabel = new JLabel("OUTPUT");
        outputLabel.setBounds(210, 300, 100, 30);
        outputLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        outputLabel.setForeground(new Color(15, 7, 88));
        panel.add(outputLabel);

        output.setEditable(false);
        output.setBackground(new Color(198, 192, 250));
        output.setFont(new Font("Calibri", Font.PLAIN, 14));
        output.setForeground(new Color(15, 7, 88));
        scroll = new JScrollPane(output);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20, 330, 450, 225);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll);
    }

    /**
     * Display error message.
     *
     * @param exception the exception
     */
    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(182, 175, 246));
            UI.put("Panel.background", new Color(182, 175, 246));
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gets nb of clients text field.
     *
     * @return the nb of clients text field
     */
    public JTextField getNbOfClientsTextField() {
        return nbOfClientsTextField;
    }

    /**
     * Gets nb of queues text field.
     *
     * @return the nb of queues text field
     */
    public JTextField getNbOfQueuesTextField() {
        return nbOfQueuesTextField;
    }

    /**
     * Gets simulation time text field.
     *
     * @return the simulation time text field
     */
    public JTextField getSimulationTimeTextField() {
        return simulationTimeTextField;
    }

    /**
     * Gets min service time text field.
     *
     * @return the min service time text field
     */
    public JTextField getMinServiceTimeTextField() {
        return minServiceTimeTextField;
    }

    /**
     * Gets max service time text field.
     *
     * @return the max service time text field
     */
    public JTextField getMaxServiceTimeTextField() {
        return maxServiceTimeTextField;
    }

    /**
     * Gets min arrival time text field.
     *
     * @return the min arrival time text field
     */
    public JTextField getMinArrivalTimeTextField() {
        return minArrivalTimeTextField;
    }

    /**
     * Gets max arrival time text field.
     *
     * @return the max arrival time text field
     */
    public JTextField getMaxArrivalTimeTextField() {
        return maxArrivalTimeTextField;
    }

    /**
     * Gets start simulation button.
     *
     * @return the start simulation button
     */
    public JButton getStartSimulationButton() {
        return startSimulationButton;
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    public JTextArea getOutput() {
        return output;
    }

    /**
     * Sets text area.
     *
     * @param outputString the output string
     */
    public void setTextArea(String outputString) {
        this.output.setText(outputString);
    }

    /**
     * Gets clear button.
     *
     * @return the clear button
     */
    public JButton getClearButton() {
        return clearButton;
    }

}
