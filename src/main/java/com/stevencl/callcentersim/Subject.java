package com.stevencl.callcentersim;

/**
 * Subject interface for observer design pattern
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public interface Subject {
    /**
     * Adds an observer
     * 
     * @param       observer    observer to be added
     */
    public void addObserver(Observer observer);
    
    /**
     * Removes an observer
     * 
     * @param       observer    observer to be removed
     */
    public void removeObserver(Observer observer);
    
    /**
     * Notifies all observers, providing status information
     * 
     * @param       callsRemaining      number of calls remaining
     * @param       queueSize           number of customers in queue
     * @param       openCalls           number of open calls
     * @param       availableTechs      number of techs in queue
     */
    public void notifyObservers(int callsRemaining, int queueSize, int openCalls, 
                                int availableTechs);
                                
    /**
     * Notifies all observers, providing console text
     * 
     * @param       consoleText         text to be displayed on the console
     */
    public void notifyObservers(String consoleText);
    
    /**
     * Notifies all observers, providing simulation run-status
     * 
     * @param       isRunning           whether or not a simulation is running
     */
    public void notifyObservers(boolean isRunning);
}
