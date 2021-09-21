package com.stevencl.callcentersim;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Simulates call activity for a call center
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Simulation implements Subject {
    /** Initial customers in queue */
    private int                         custCount;
    /** Remaining calls to simulate */
    private int                         callCount;
    /** Interval between incoming calls, in seconds */
    private int                         interval;
    /** Average call length, in seconds */
    private int                         length;
    /** Day to simulate */
    private Day                         day;
    /** Shift to simulate */
    private int                         shift;

    
    /** Observer list */
    private ArrayList<Observer>         observers;
    /** All customers */
    private Customer[]                  customers;
    /** All techs */
    private Tech[]                      techs;
    /** Customer queue */
    private HashSetQueue<Customer>      custQueue;
    /** Tech queue */
    private HashSetQueue<Tech>          techQueue;
    /** Open calls */
    private ArrayList<Call>             openCalls;
    /** Timer for queue tasks */
    private Timer                       timer1;
    /** Timer for start call tasks */
    private Timer                       timer2;
    /** Timer for end call tasks */
    private Timer                       timer3;
    /** Whether or not a simulation is running */
    private boolean                     isRunning;
    
    
    /**
     * Creates a Simulation
     */
    public Simulation() {
        try {
            readTechFromFile();
            readCustFromFile();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Could not open file");
        }
        this.observers = new ArrayList<>();
        this.isRunning = false;
    }
    
    
    /**
     * Fills customer array with data from file
     * 
     * @throws      FileNotFoundException   if target file cannot be found
     */
    private void readCustFromFile() throws FileNotFoundException {
        File inFile = new File("customers.csv");
        Scanner scan = new Scanner(inFile);

        int count = Integer.parseInt(scan.nextLine());
        customers = new Customer[count];

        scan.nextLine();
        int index = 0;
        while (scan.hasNextLine()) {
            String[] field = scan.nextLine().split(",");
            customers[index] = new Customer(field[0], field[1], field[2], field[3], field[4]);
            index++;
        }
    }
    
    /**
     * Fills tech array with data from file
     * 
     * @throws      FileNotFoundException   if target file cannot be found
     */
    private void readTechFromFile() throws FileNotFoundException {
        File inFile = new File("techs.csv");
        Scanner scan = new Scanner(inFile);

        int count = Integer.parseInt(scan.nextLine());
        techs = new Tech[count];

        scan.nextLine();
        int index = 0;
        while (scan.hasNextLine()) {
            String[] field = scan.nextLine().split(",");
            techs[index] = new Tech(field[0], field[1], field[2], field[3], field[4]);
            index++;
        }
    }
    
    
    /**
     * Checks whether or not the simulation is running
     * 
     * @return      true if the simulation is running, otherwise false
     */
    public boolean getIsRunning() {
        return isRunning;
    }
    
    /**
     * Starts the simulation with specified values
     * 
     * @param       custCount       initial customers in queue
     * @param       callCount       number of calls to simulate
     * @param       interval        interval between incoming calls, in seconds
     * @param       length          average call length, in seconds
     * @param       day             day to simulate
     * @param       shift           shift to simulate
     * @throws      IllegalStateException   if a simulation is already running
     */
    public void start(int custCount, int callCount, int interval, int length, Day day, 
                         int shift) {
        if (isRunning) {
            throw new IllegalStateException("You must end the current simulation before" + 
                                            "starting a new one");
        }
        
        this.custCount = custCount;
        this.callCount = callCount;
        this.interval = interval;
        this.length = length;
        this.day = day;
        this.shift = shift;
        this.isRunning = true;
        notifyObservers(isRunning);
        
        openCalls = new ArrayList<>();
        initialTechQueue();
        initialCustQueue();
        
        this.timer1 = new Timer();
        timer1.schedule(new QueueTask(), 0, interval * 1000);
        this.timer2 = new Timer();
        timer2.schedule(new StartCallTask(), 0, 50);
        this.timer3 = new Timer();
    }
    
    /**
     * Ends the simulation
     */
    public void end() {
        timer1.cancel();
        timer2.cancel();
        timer3.cancel();
        
        isRunning = false;
        notifyObservers(isRunning);
        
    }
    
    
    /**
     * Fills tech queue with scheduled techs
     */
    private void initialTechQueue() {
        techQueue = new HashSetQueue<>();
        for (Tech tech : techs) {
            if (tech.isWorking(day, shift)) {
                techQueue.add(tech);
            }
        }
    }
    
    /**
     * Fills initial customer queue
     */
    private void initialCustQueue() {
        custQueue = new HashSetQueue<>();
        addRandCust(custCount);
    }
    
    /**
     * Adds specified number of random customers to customer queue
     * 
     * @param       quantity        number of random customers to be added
     */
    private void addRandCust(int quantity) {
        int targetSize = custQueue.size() + quantity;
        if (targetSize > customers.length) {
            throw new IllegalArgumentException("There are not enough customers in the array");
        }
        
        while (custQueue.size() < targetSize) {
            custQueue.add(customers[randInRangeInt(0, customers.length - 1)]);
        }
        notifyObservers(callCount, custQueue.size(), openCalls.size(), techQueue.size());
    }
    
    /**
     * Returns a random integer within specified range
     * 
     * @param       min         minimum value, inclusive
     * @param       max         maximum value, inclusive
     * @return      a random integer within specified range
     */
    private int randInRangeInt(int min, int max) {
        Random random = new Random();
        int x = random.nextInt(max - min) + min;
        return x;
    }
    
    
    /**
     * Adds a new call if both a tech and customer are available
     */
    private void addCall() {
        if (callCount > 0 && !techQueue.isEmpty() && !custQueue.isEmpty()) {
            Tech nextTech = techQueue.remove();
            Customer nextCust = custQueue.remove();
            
            int randomLength = randInRangeInt(100, length * 2000);
            Call newCall = new Call(nextCust, nextTech, randomLength);
            notifyObservers(newCall + "\n");
            openCalls.add(newCall);
            timer3.schedule(new EndCallTask(newCall), randomLength);
            callCount--;
            notifyObservers(callCount, custQueue.size(), openCalls.size(), techQueue.size());
        }
        if (callCount <= 0) {
            timer1.cancel();
            timer2.cancel();
        }
    }
    
    /**
     * Removes specified call from open calls
     * 
     * @param       call        call to be removed
     */
    public void removeCall(Call call) {
        Tech availableTech = call.getTech();
        openCalls.remove(call);
        techQueue.add(availableTech);
        
        notifyObservers(callCount, custQueue.size(), openCalls.size(), techQueue.size());
        
        if (callCount <= 0 && openCalls.isEmpty()) {
            notifyObservers("Simulation complete");
            end();
        }
    }
    
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(int callsRemaining, int queueSize, int openCalls, 
                                int availableTechs) {
        for (Observer observer : observers) {
            observer.update(callsRemaining, queueSize, openCalls, availableTechs);
        }
    }
    
    public void notifyObservers(String consoleText) {
        for (Observer observer : observers) {
            observer.update(consoleText);
        }
    }
    
    public void notifyObservers(boolean isRunning) {
        for (Observer observer : observers) {
            observer.update(isRunning);
        }
    }
    
    
    /**
     * A TimerTask which adds a random customer to the queue
     */
    public class QueueTask extends TimerTask {
        public void run() {
            if (callCount > 0) {
                addRandCust(1);
            } else {
                this.cancel();
            }
        }
    }
    
    /**
     * A TimerTask which attempts to add a new call
     */
    public class StartCallTask extends TimerTask {
        public void run() {
            addCall();
            
            if (callCount <= 0) {
                this.cancel();
            }
        }
    }
    
    /**
     * A TimerTask which removes a call
     */
    public class EndCallTask extends TimerTask {
        /** The call to be removed */
        private Call    call;
        
        /**
         * Creates a task to remove a specified call
         * 
         * @param       call        the call to be removed
         */
        public EndCallTask(Call call) {
            super();
            this.call = call;
        }
        
        public void run() {
            removeCall(call);
        }
    }
}
