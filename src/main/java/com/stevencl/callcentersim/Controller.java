package com.stevencl.callcentersim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Initializes Simulation using user input provided by the View
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Controller implements Observer {
    /** The active simulation */
    private Simulation      simulation;
    /** The view for the simulation */
    private View            view;
    
    
    /**
     * Creates a Controller for the given View
     * 
     * @param       view         the view for this controller
     * @param       simulation   the simulation for this controller
     */
    public Controller(Simulation simulation, View view) {
        this.simulation = simulation;
        this.simulation.addObserver(this);
        this.view = view;
        this.view.addStartListener(new StartEndListener());
    }
    
    
    /**
     * Starts a new Simulation with data from View
     */
    private void beginSim() {
        int custCount       = view.getCustCount();
        int callCount       = view.getCallCount();
        int callInterval    = view.getCallInterval();
        int callLength      = view.getCallLength();
        Day day             = view.getDay();
        int shift           = view.getShift();
        
        view.clearDisplay();
        String header = "Initial customers = " + custCount + 
                        ", Call count = " + callCount + 
                        ", Call interval = " + callInterval + 
                        ", Avg call length = " + callLength + 
                        ", Day = " + day + 
                        ", Shift = " + shift + "\n" + 
                        "Starting simulation...\n";
        view.addDisplayText(header);
        
        simulation.start(custCount, callCount, callInterval, callLength, day, shift);
    }
    
    /**
     * Ends the active Simulation, if one exists
     */
    public void endSim() {
        simulation.end();
    }
    
    
    /**
     * Updates the display with the given status values
     * 
     * @param       callsRemaining      number of calls remaining
     * @param       queueSize           number of customers in queue
     * @param       openCalls           number of open calls
     * @param       availableTechs      number of techs in queue
     */
    public void update(int callsRemaining, int queueSize, int openCalls, int availableTechs) {
        view.updateStatus(callsRemaining, queueSize, openCalls, availableTechs);
    }
    
    /**
     * Appends the given console text to the display
     * 
     * @param       consoleText         text to be displayed on the console
     */
    public void update(String consoleText) {
        view.addDisplayText(consoleText);
    }
    
    /**
     * Toggles the start/stop button on the display
     * 
     * @param       isRunning           whether or not a simulation is running
     */
    public void update(boolean isRunning) {
        view.toggleStartStop();
    }
    
    
    /**
     * Listener for start/end button action
     */
    public class StartEndListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!simulation.getIsRunning()) {
                beginSim();
            } else {
                view.addDisplayText("Simulation cancelled");
                endSim();
            }
        }
    }
}
