package com.stevencl.callcentersim;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a support call
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Call {
    /** The customer assigned to this call */
    private Customer    customer;
    /** The support tech assigned to this call */
    private Tech        tech;
    /** Call length, in milliseconds */
    private int         length;
    
    
    /**
     * Creates a Call with the given customer, tech, and call length
     * 
     * @param       customer        the customer assigned to this call
     * @param       tech            the tech assigned to this call
     * @param       length          call length, in milliseconds
     */
    public Call(Customer customer, Tech tech, int length) {
        this.customer = customer;
        this.tech = tech;
        this.length = length;
    }
    
    
    /**
     * Returns customer
     * 
     * @return      customer
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Returns support tech
     * 
     * @return      support tech
     */
    public Tech getTech() {
        return tech;
    }
    
    /**
     * Returns call length, in milliseconds
     * 
     * @return      call length, in milliseconds
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns a string representation of this Call
     */
    @Override
    public String toString() {
        return "Cust: " + customer.toString() + "\nTech: " + tech.toString();
    }
}
