package com.stevencl.callcentersim;

/**
 * Launches the application
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Main {
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        View view = new View();
        Controller controller = new Controller(sim, view);
    }
}
