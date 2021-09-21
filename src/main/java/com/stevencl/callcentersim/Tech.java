package com.stevencl.callcentersim;

import java.util.HashMap;

/**
 * Represents a support tech
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Tech implements Comparable<Tech> {
    /** Support tech id */
    private String      id;
    /** First name */
    private String      firstName;
    /** Last name */
    private String      lastName;
    /** User name */
    private String      userName;
    /** Schedule */
    private HashMap<Day, Boolean>   schedule;
    /** Shift */
    private int         shift;
    
    
    /**
     * Creates a Tech with the specified information
     * 
     * @param       id              Support tech id
     * @param       firstName       First name
     * @param       lastName        Last name
     * @param       userName        User name
     * @param       schedule        Schedule string, to be parsed
     */
    public Tech(String id, String firstName, String lastName, String userName, String schedule) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        
        // parse schedule
        this.schedule = new HashMap<>();
        this.schedule.put(Day.TUE, true);
        this.schedule.put(Day.WED, true);
        this.schedule.put(Day.THU, true);
        switch (schedule) {
            case "SMTWT1":  this.schedule.put(Day.SUN, true);
                            this.schedule.put(Day.MON, true);
                            this.schedule.put(Day.FRI, false);
                            this.schedule.put(Day.SAT, false);
                            this.shift = 1;
                            break;
            case "SMTWT2":  this.schedule.put(Day.SUN, true);
                            this.schedule.put(Day.MON, true);
                            this.schedule.put(Day.FRI, false);
                            this.schedule.put(Day.SAT, false);
                            this.shift = 2;
                            break;
            case "TWTFS1":  this.schedule.put(Day.SUN, false);
                            this.schedule.put(Day.MON, false);
                            this.schedule.put(Day.FRI, true);
                            this.schedule.put(Day.SAT, true);
                            this.shift = 1;
                            break;
            case "TWTFS2":  this.schedule.put(Day.SUN, false);
                            this.schedule.put(Day.MON, false);
                            this.schedule.put(Day.FRI, true);
                            this.schedule.put(Day.SAT, true);
                            this.shift = 2;
                            break;
            default:        throw new IllegalArgumentException("Invalid schedule");
        }
    }
    
    
    /**
     * Returns support tech id
     * 
     * @return      support tech id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns first name
     * 
     * @return      first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Returns last name
     * 
     * @return      last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Returns user name
     * 
     * @return      userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Checks whether or not this tech is scheduled to work for given day and shift
     * 
     * @param       day         day to be checked
     * @param       shift       shift to be checked
     * @return      true if the tech is scheduled, otherwise false
     */
    public boolean isWorking(Day day, int shift) {
        return schedule.get(day) && shift == this.shift;
    }
    
    /**
     * Returns a string representation of this Tech
     * 
     * @return      a string representation of this Tech
     */
    @Override
    public String toString() {
        return String.format("id=%s, name=%s.%s, username=%s", id, firstName, lastName, 
                             userName);
    }
    
    
    /**
     * Compares this tech with the specified tech for order. Returns a negative integer, 
     * zero, or a positive integer as this tech is less than, equal to, or greater than the 
     * specified tech.
     * 
     * @param       other       the tech to be compared with this one
     * @return      a negative integer, zero, or a positive integer as this tech is less 
     *              than, equal to, or greater than the specified tech
     */
    @Override
    public int compareTo(Tech other) {
        return this.id.compareTo(other.id);
    }
    
    /**
     * Checks whether or not this object is the same as another object
     * 
     * @param       other       the object to compare to this one
     * @return      true if the objects are the same, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        } else if (this == other) {
            return true;
        } else {
            Tech otherTech = (Tech)other;
            return this.id.equals(otherTech.id);
        }
    }
    
    /**
     * Generates the hash code for this Customer
     * 
     * @return      the hash code for this Customer
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
