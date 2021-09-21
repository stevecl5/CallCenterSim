package com.stevencl.callcentersim;

/**
 * Observer interface for observer design pattern
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public interface Observer {
    /**
     * Performs an update based on the given status values
     * 
     * @param       callsRemaining      number of calls remaining
     * @param       queueSize           number of customers in queue
     * @param       openCalls           number of open calls
     * @param       availableTechs      number of techs in queue
     */
    public void update(int callsRemaining, int queueSize, int openCalls, int availableTechs);
    
    /**
     * Performs an update based on the given console text
     * 
     * @param       consoleText         text to be displayed on the console
     */
    public void update(String consoleText);
    
    /**
     * Performs an update based on the given value
     * 
     * @param       isRunning           whether or not a simulation is running
     */
    public void update(boolean isRunning);
}
