package com.stevencl.callcentersim;

import java.util.NoSuchElementException;

/**
 * A generic queue which uses a HashSet to ensure that all values are unique
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class HashSetQueue<E extends Comparable<E>> {
    /** Front node */
    private QueueNode<E>    front;
    /** Back node */
    private QueueNode<E>    back;
    /** Queue size */
    private int             size;
    /** Set of all values contained in the queue */
    private HashSet<E>      values;
    
    
    /**
     * Creates an empty queue
     */
    public HashSetQueue() {
        this.size = 0;
        this.values = new HashSet<E>();
    }
    
    
    /**
     * Returns the current number of elements in the queue
     * 
     * @return      the current number of elements in the queue
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks whether or not the queue is empty
     * 
     * @return      true if the queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns a comma-separated, bracketed string representation of the queue
     * 
     * @return      a comma-separated, bracketed string representation of the queue
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + front.data;
            QueueNode<E> current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }
    
    
    /**
     * Appends the given value to the end of the queue, only if the value is not already 
     * contained in the queue
     * 
     * @param       value       value to be added to the queue
     * @return      true if the value was added, otherwise false
     */
    public boolean add(E value) {
        if (!values.add(value)) {
            return false;
        }
        
        if (size == 0) {
            front = back = new QueueNode<E>(value);
            size++;
        } else { // size >= 1
            back.next = new QueueNode<E>(value);
            back = back.next;
            size++;
        }
        return true;
    }
    
    /**
     * Returns the first value in the queue without removing it from the queue
     * 
     * @return      the first value in the queue
     */
    public E peek() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        
        return front.data;
    }
    
    /**
     * Returns the first value in the queue and removes it from the queue
     * 
     * @return      the first value in the queue
     */
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        
        E removedData = front.data;
        front = front.next;
        size--;
        values.remove(removedData);
        return removedData;
    }
    
    /**
     * Empties the queue
     */
    public void clear() {
        front = back = null;
        size = 0;
        values.clear();
    }
    
    
    /**
     * A node for the HashSetQueue
     */
    private class QueueNode<E> {
        /** Stored value */
        public E                data;
        /** The next node in the list */
        public QueueNode<E>     next;
        
        /**
         * Creates a QueueNode with the given data and next node reference
         * 
         * @param       data        value to be stored
         * @param       next        the next node in the queue, or null
         */
        public QueueNode(E data, QueueNode<E> next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Creates a QueueNode with the given data and null reference for next node
         * 
         * @param       data        data to be stored in node
         */
        public QueueNode(E data) {
            this(data, null);
        }
    }
}
