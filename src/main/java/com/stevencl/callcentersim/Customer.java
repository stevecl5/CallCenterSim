package com.stevencl.callcentersim;

/**
 * Represents a customer
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class Customer implements Comparable<Customer> {
    /** Customer id */
    private String      id;
    /** First name */
    private String      firstName;
    /** Last name */
    private String      lastName;
    /** Email address */
    private String      email;
    /** Phone number */
    private String      phone;
    
    
    /**
     * Creates a customer with the specified information
     * 
     * @param       id              Customer id
     * @param       firstName       First name
     * @param       lastName        Last name
     * @param       email           Email address
     * @param       phone           Phone number
     */
    public Customer(String id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    
    /**
     * Returns customer id
     * 
     * @return      customer id
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
     * @return      lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Returns email address
     * 
     * @return      email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Returns phone number
     * 
     * @return      phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Returns a string representation of this Customer
     * 
     * @return      a string representation of this Customer
     */
    @Override
    public String toString() {
        return String.format("id=%s, name=%s.%s, email=%s, phone=%s", id, firstName, 
                             lastName, email, phone);
    }
    
    
    /**
     * Compares this customer with the specified customer for order. Returns a negative 
     * integer, zero, or a positive integer as this customer is less than, equal to, or 
     * greater than the specified customer.
     * 
     * @param       other       the customer to be compared with this one
     * @return      a negative integer, zero, or a positive integer as this customer is less 
     *              than, equal to, or greater than the specified customer
     */
    @Override
    public int compareTo(Customer other) {
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
            Customer otherCust = (Customer)other;
            return this.id.equals(otherCust.id);
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
