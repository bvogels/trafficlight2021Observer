package trafficlight.gui;

import trafficlight.ctrl.TrafficLightCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLightGui extends JFrame implements ActionListener {

    public static final String ACTION_COMMAND_STOP = "stop";

    public static final String NAME_OF_THE_GAME = "Traffic Light";

    private JButton buttonStop;

    private TrafficLight green = null;
    private TrafficLight yellow = null;
    private TrafficLight red = null;

    private TrafficLightCtrl trafficLightCtrl = null;


    public TrafficLightGui(TrafficLightCtrl ctrl){
        super(NAME_OF_THE_GAME);
        trafficLightCtrl = ctrl;
        initLights(ctrl);
        init();
    }

    private void initLights(TrafficLightCtrl ctrl) {

        // This part determines the color of the light - my mistake was to think that the
        // traffic light is a single instance, where it is a collection of three distinct
        // lights.

        green = new TrafficLight(Color.green);
        red = new TrafficLight(Color.red);
        yellow = new TrafficLight(Color.yellow);


        /* The following methods are implemented in TrafficLightCtrl. They add observers
        of the respective color to the list of observers, maintained by the subject class via
        the state class, which extends state class.
         */

        ctrl.getGreenState().addObservers(green);
        ctrl.getRedState().addObservers(red);
        ctrl.getYellowState().addObservers(yellow);

    }


    private void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        buttonStop = new JButton("Stop");
        buttonStop.setActionCommand(ACTION_COMMAND_STOP);
        buttonStop.addActionListener(this);

        JPanel p1 = new JPanel(new GridLayout(3,1));
        p1.add(red);
        p1.add(yellow);
        p1.add(green);


        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(buttonStop);

        getContentPane().add(p1);
        getContentPane().add(p2);
        pack();
    }


    public void showErrorMessage(Exception e) {
        JOptionPane pane = new JOptionPane();
        JDialog dialog = pane.createDialog(this, "Traffic Light Problem");
        JOptionPane.showMessageDialog(dialog, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void actionPerformed(ActionEvent e){
        if (ACTION_COMMAND_STOP.equals(e.getActionCommand())){
           trafficLightCtrl.stop();
        }
    }
}
