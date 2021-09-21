package com.stevencl.callcentersim;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

/**
 * The test class for HashSetQueue
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class HashSetQueueTest {
    private HashSetQueue<String> days;
    
    /**
     * Default constructor for test class HashSetQueueTest
     */
    public HashSetQueueTest() {
        days = new HashSetQueue<>();
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        days.clear();
    }
    
    @Test
    public void emptyQueue() {
        HashSetQueue<String> testSet = new HashSetQueue<>();
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
    }
    
    @Test
    public void addAndRemove() {
        HashSetQueue<String> names = new HashSetQueue<>();
        String name1 = "Sarah";
        String name2 = "Brian";
        String name3 = "Andrew";
        String name4 = "Mary";
        
        assertTrue(names.isEmpty());
        assertTrue(names.add(name1));
        assertFalse(names.isEmpty());
        assertTrue(names.add(name2));
        assertTrue(names.add(name3));
        assertFalse(names.add(name2));
        assertFalse(names.add("Brian"));
        assertTrue(names.add(name4));
        assertFalse(names.add("Sarah"));
        assertTrue(names.add("sarah"));
        
        assertEquals(5, names.size());
        assertEquals("Sarah", names.peek());
        assertEquals("Sarah", names.remove());
        assertEquals("Brian", names.peek());
        assertEquals("Brian", names.remove());
        assertEquals("Andrew", names.peek());
        assertEquals("Andrew", names.remove());
        assertEquals("Mary", names.peek());
        assertEquals("Mary", names.remove());
        assertEquals("sarah", names.peek());
        assertEquals("sarah", names.remove());
    }
    
    @Test
    public void addRemoveOne() {
        HashSetQueue<String> names = new HashSetQueue<>();
        assertTrue(names.add("Sarah"));
        assertEquals(1, names.size());
        assertEquals("Sarah", names.peek());
        assertEquals("Sarah", names.remove());
        assertTrue(names.isEmpty());
    }
    
    @Test
    public void clear() {
        assertFalse(days.isEmpty());
        days.clear();
        assertTrue(days.isEmpty());
    }
    
    @Test (expected = NoSuchElementException.class)
    public void peekEmpty() {
        HashSetQueue<String> names = new HashSetQueue<>();
        names.peek();
    }
    
    @Test (expected = NoSuchElementException.class)
    public void removeEmpty() {
        HashSetQueue<String> names = new HashSetQueue<>();
        names.remove();
    }
    
}
