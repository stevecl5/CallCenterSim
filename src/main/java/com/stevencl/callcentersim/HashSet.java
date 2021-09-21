package com.stevencl.callcentersim;

/**
 * An unordered Set in which elements are stored in a hash table
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class HashSet<E extends Comparable<E>> {
    /** Maximum load factor of the hash table */
    private static final double MAX_LOAD_FACTOR = 0.75;
    /** Initial size of the hash table */
    private static final int INITIAL_SIZE = 17;
    
    /** Hash table */
    private HashEntry<E>[] elementData;
    /** Number of elements in the set */
    private int size;
    
    
    /**
     * Creates empty HashSet with initial size capacity
     */
    @SuppressWarnings("unchecked") 
    public HashSet() {
        elementData = (HashEntry<E>[])new HashEntry[INITIAL_SIZE];
        size = 0;
    }
    
    
    /**
     * Returns the number of elements in the set
     * 
     * @return      the number of elements in the set
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks whether or not the set is empty
     * 
     * @return      true if the set is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks whether or not a given value is in the set
     * 
     * @param       value       the value to be checked
     * @return      true if the given value is in the set, otherwise false
     */
    public boolean contains(E value) {
        int bucket = hashFunction(value);
        HashEntry<E> current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Returns a string representation of this HashSet, such as "[10, 20, 30]", with elements
     * in no particular order
     * 
     * @return      a string representation of this HashSet
     */
    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry<E> current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }
    
    
    /**
     * Adds the given element to this set, if not already there
     * 
     * @param       value       the value to be added
     * @return      true if the element was added, otherwise false
     */
    public boolean add(E value) {
        if (contains(value)) {
            return false;
        } else {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }
            
            // insert at front of list
            int bucket = hashFunction(value);
            elementData[bucket] = new HashEntry<E>(value, elementData[bucket]);
            size++;
            return true;
        }
    }
    
    /**
     * Removes the given value if in the set; if not in set, does nothing
     * 
     * @param       value       value to be removed
     */
    public void remove(E value) {
        int bucket = hashFunction(value);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data.equals(value)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry<E> current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(value)) {
                    current = current.next;
                }
                
                // if the element is found, remove it
                if (current.next != null && current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }
    
    /**
     * Removes all elements from the set
     */
    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    
    /**
     * Returns the preferred hash bucket index for the given value
     * 
     * @param       value       value to be checked
     * @return      the preferred hash bucket index for the given value
     */
    private int hashFunction(E value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }
    
    /**
     * Calculates the load factor (occupied percentage) for this hash set
     * 
     * @return      the load factor (occupied percentage) for this hash set
     */
    private double loadFactor() {
        return (double) size / elementData.length;
    }
    
    /**
     * Resizes the hash table to twice its former size, then rehashes data
     */
    @SuppressWarnings("unchecked") 
    private void rehash() {
        // replace element data array with a larger empty version
        HashEntry<E>[] oldElementData = elementData;
        elementData = (HashEntry<E>[])new HashEntry[2 * oldElementData.length];
        size = 0;

        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry<E> current = oldElementData[i];
            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }
    
    
    /**
     * Represents a single value in a chain stored in one hash bucket
     */
    private class HashEntry<E> {
        /** Stored value */
        public E                data;
        /** The next entry in the hash bucket */
        public HashEntry<E>     next;
        
        /**
         * Creates a HashEntry with the specified data and a reference to the next entry
         * 
         * @param       data        value to be stored
         * @param       next        the next entry in the hash bucket, or null
         */
        public HashEntry(E data, HashEntry<E> next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Creates a HashEntry with the specified data and null reference for next entry
         * 
         * @param       data        value to be stored
         */
        public HashEntry(E data) {
            this(data, null);
        }
    }
}
