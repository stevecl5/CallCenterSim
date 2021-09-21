package com.stevencl.callcentersim;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * Provides a display window with J-components to accept user input and display results
 *
 * @author      Steven Leighton
 * @version     2018-12-06
 */
public class View extends JFrame {
    /** Toolkit */
    private Toolkit         toolkit;
    /** Main panel */
    private JPanel          panel;
    /** Customer count label */
    private JLabel          labelCustCount;
    /** Call count label */
    private JLabel          labelCallCount;
    /** Call interval label */
    private JLabel          labelCallInterval;
    /** Call length label */
    private JLabel          labelCallLength;
    /** Day label */
    private JLabel          labelDay;
    /** Shift label */
    private JLabel          labelShift;
    /** Output label */
    private JLabel          labelOutput;
    /** Remaining calls label */
    private JLabel          labelCount;
    /** In queue label */
    private JLabel          labelQueue;
    /** Occupied techs label */
    private JLabel          labelOccupied;
    /** Available techs label */
    private JLabel          labelAvailable;
    /** Customer count slider */
    private JSlider         sliderCustCount;
    /** Call count slider */
    private JSlider         sliderCallCount;
    /** Call interval slider */
    private JSlider         sliderCallInterval;
    /** Call length slider */
    private JSlider         sliderCallLength;
    /** Customer count text field */
    private JTextField      textCustCount;
    /** Call count text field */
    private JTextField      textCallCount;
    /** Call interval text field */
    private JTextField      textCallInterval;
    /** Call length text field */
    private JTextField      textCallLength;
    /** Day combo box */
    private JComboBox       comboDay;
    /** Shift combo box */
    private JComboBox       comboShift;
    /** Start button */
    private JButton         buttonStart;
    /** Output text area */
    private JTextArea       textAreaOutput;
    /** Output scroll pane */
    private JScrollPane     scrollOutput;
    
    
    /**
     * Creates a View (JFrame) to host J-components
     */
    public View() {
        this.toolkit = getToolkit();
        
        setSize(1200, 600);
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth())/2, (screenSize.height - getHeight())/2);
        setTitle("Tech Support Simulation");
        setResizable(false);
        
        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);
        
        custCount();
        callCount();
        callInterval();
        callLength();
        day();
        shift();
        start();
        output();
        statusBar();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    /**
     * Sets up 'initial queue size' option
     */
    private void custCount() {
        labelCustCount = new JLabel("Initial queue size:");
        labelCustCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelCustCount.setBounds(20, 20, 200, 30);
        panel.add(labelCustCount);
        
        int initialValue = 20;
        sliderCustCount = new JSlider(10, 50, initialValue);
        sliderCustCount.setBounds(20, 50, 200, 50);
        sliderCustCount.setMajorTickSpacing(10);
        sliderCustCount.setMinorTickSpacing(5);
        sliderCustCount.setSnapToTicks(true);
        sliderCustCount.setOrientation(JSlider.HORIZONTAL);
        sliderCustCount.setPaintLabels(true);
        sliderCustCount.setPaintTicks(true);
        sliderCustCount.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                textCustCount.setText(String.format("%d", sliderCustCount.getValue()));
            }
        });
        panel.add(sliderCustCount);
        
        textCustCount = new JTextField(String.format("%d", initialValue));
        textCustCount.setBackground(Color.WHITE);
        textCustCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        textCustCount.setHorizontalAlignment(JTextField.CENTER);
        textCustCount.setEditable(false);
        textCustCount.setBounds(240, 50, 40, 30);
        panel.add(textCustCount);
    }
    
    /**
     * Returns the selected customer count
     * 
     * @return      the selected customer count
     */
    public int getCustCount() {
        return sliderCustCount.getValue();
    }
    
    
    /**
     * Sets up 'number of calls' option
     */
    private void callCount() {
        labelCallCount = new JLabel("Number of calls:");
        labelCallCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelCallCount.setBounds(20, 100, 200, 30);
        panel.add(labelCallCount);
        
        int initialValue = 30;
        sliderCallCount = new JSlider(20, 100, initialValue);
        sliderCallCount.setBounds(20, 130, 200, 50);
        sliderCallCount.setMajorTickSpacing(20);
        sliderCallCount.setMinorTickSpacing(10);
        sliderCallCount.setSnapToTicks(true);
        sliderCallCount.setOrientation(JSlider.HORIZONTAL);
        sliderCallCount.setPaintLabels(true);
        sliderCallCount.setPaintTicks(true);
        sliderCallCount.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                textCallCount.setText(String.format("%d", sliderCallCount.getValue()));
            }
        });
        panel.add(sliderCallCount);
        
        textCallCount = new JTextField(String.format("%d", initialValue));
        textCallCount.setBackground(Color.WHITE);
        textCallCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        textCallCount.setHorizontalAlignment(JTextField.CENTER);
        textCallCount.setEditable(false);
        textCallCount.setBounds(240, 130, 40, 30);
        panel.add(textCallCount);
    }
    
    /**
     * Returns the selected call count
     * 
     * @return      the selected call count
     */
    public int getCallCount() {
        return sliderCallCount.getValue();
    }
    
    
    /**
     * Sets up 'call interval' option
     */
    private void callInterval() {
        labelCallInterval = new JLabel("Call interval:");
        labelCallInterval.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelCallInterval.setBounds(20, 180, 200, 30);
        panel.add(labelCallInterval);
        
        int initialValue = 1;
        sliderCallInterval = new JSlider(1, 5, initialValue);
        sliderCallInterval.setBounds(20, 210, 200, 50);
        sliderCallInterval.setMajorTickSpacing(4);
        sliderCallInterval.setMinorTickSpacing(1);
        sliderCallInterval.setSnapToTicks(true);
        sliderCallInterval.setOrientation(JSlider.HORIZONTAL);
        sliderCallInterval.setPaintLabels(true);
        sliderCallInterval.setPaintTicks(true);
        sliderCallInterval.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                textCallInterval.setText(String.format("%d", sliderCallInterval.getValue()));
            }
        });
        panel.add(sliderCallInterval);
        
        textCallInterval = new JTextField(String.format("%d", initialValue));
        textCallInterval.setBackground(Color.WHITE);
        textCallInterval.setFont(new Font("SansSerif", Font.BOLD, 14));
        textCallInterval.setHorizontalAlignment(JTextField.CENTER);
        textCallInterval.setEditable(false);
        textCallInterval.setBounds(240, 210, 40, 30);
        panel.add(textCallInterval);
    }
    
    /**
     * Returns the selected call interval
     * 
     * @return      the selected call interval
     */
    public int getCallInterval() {
        return sliderCallInterval.getValue();
    }
    
    
    /**
     * Sets up 'call length' option
     */
    private void callLength() {
        labelCallLength = new JLabel("Avg call length:");
        labelCallLength.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelCallLength.setBounds(20, 260, 200, 30);
        panel.add(labelCallLength);
        
        int initialValue = 2;
        sliderCallLength = new JSlider(1, 10, initialValue);
        sliderCallLength.setBounds(20, 290, 200, 50);
        sliderCallLength.setMajorTickSpacing(9);
        sliderCallLength.setMinorTickSpacing(1);
        sliderCallLength.setSnapToTicks(true);
        sliderCallLength.setOrientation(JSlider.HORIZONTAL);
        sliderCallLength.setPaintLabels(true);
        sliderCallLength.setPaintTicks(true);
        sliderCallLength.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                textCallLength.setText(String.format("%d", sliderCallLength.getValue()));
            }
        });
        panel.add(sliderCallLength);
        
        textCallLength = new JTextField(String.format("%d", initialValue));
        textCallLength.setBackground(Color.WHITE);
        textCallLength.setFont(new Font("SansSerif", Font.BOLD, 14));
        textCallLength.setHorizontalAlignment(JTextField.CENTER);
        textCallLength.setEditable(false);
        textCallLength.setBounds(240, 290, 40, 30);
        panel.add(textCallLength);
    }
    
    /**
     * Returns the selected call length
     * 
     * @return      the selected call length
     */
    public int getCallLength() {
        return sliderCallLength.getValue();
    }
    
    
    /**
     * Sets up 'day' option
     */
    private void day() {
        labelDay = new JLabel("Day:");
        labelDay.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelDay.setBounds(20, 340, 200, 30);
        panel.add(labelDay);
        
        Day[] days = {Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI, Day.SAT, Day.SUN};
        comboDay = new JComboBox<>(days);
        comboDay.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboDay.setBounds(20, 370, 80, 30);
        comboDay.setBackground(Color.WHITE);
        panel.add(comboDay);
    }
    
    /**
     * Returns the selected day
     * 
     * @return      the selected day
     */
    public Day getDay() {
        return (Day)comboDay.getSelectedItem();
    }
    
    
    /**
     * Sets up 'shift' option
     */
    private void shift() {
        labelShift = new JLabel("Shift:");
        labelShift.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelShift.setBounds(20, 410, 200, 30);
        panel.add(labelShift);
        
        String[] shifts = {"1", "2"};
        comboShift = new JComboBox<>(shifts);
        comboShift.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboShift.setBounds(20, 440, 80, 30);
        comboShift.setBackground(Color.WHITE);
        panel.add(comboShift);
    }
    
    /**
     * Returns the selected shift
     * 
     * @return      the selected shift
     */
    public int getShift() {
        switch((String)comboShift.getSelectedItem()) {
            case "1" :  return 1;
            case "2" :  return 2;
            default  :  throw new IllegalStateException("Invalid shift");
        }
    }
    
    
    /**
     * Sets up start button
     */
    private void start() {
        buttonStart = new JButton("Start");
        buttonStart.setFont(new Font("SansSerif", Font.BOLD, 14));
        buttonStart.setBounds(100, 500, 100, 50);
        panel.add(buttonStart);
    }
    
    /**
     * Adds an ActionListener to the start button
     * 
     * @param       startListener       action listener to be added
     */
    public void addStartListener(ActionListener startListener) {
        buttonStart.addActionListener(startListener);
    }
    
    /**
     * Toggles the start button text between 'Start' or 'Stop'
     */
    public void toggleStartStop() {
        String buttonText = buttonStart.getText().equals("Start") ? "Stop" : "Start";
        buttonStart.setText(buttonText);
    }
    
    
    /**
     * Sets up 'console' area
     */
    private void output() {
        labelOutput = new JLabel("Console:");
        labelOutput.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelOutput.setBounds(305, 20, 100, 30);
        panel.add(labelOutput);
        
        textAreaOutput = new JTextArea();
        textAreaOutput.setEditable(false);
        textAreaOutput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        
        DefaultCaret caret = (DefaultCaret)textAreaOutput.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        scrollOutput = new JScrollPane(textAreaOutput);
        scrollOutput.setBounds(300, 50, 880, 500);
        panel.add(scrollOutput);
    }
    
    /**
     * Clears contents of the console
     */
    public void clearDisplay() {
        textAreaOutput.setText("");
    }
    
    /**
     * Adds text to the console
     * 
     * @param       newText     text to be added
     */
    public void addDisplayText(String newText) {
        textAreaOutput.append(newText + "\n");
    }
    
    
    /**
     * Sets up 'status bar'
     */
    private void statusBar() {
        labelCount = new JLabel("Remaining:");
        labelCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelCount.setForeground(Color.BLUE);
        labelCount.setBounds(724, 20, 120, 30);
        panel.add(labelCount);
        
        labelQueue = new JLabel("In Queue:");
        labelQueue.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelQueue.setForeground(Color.MAGENTA);
        labelQueue.setBounds(845, 20, 120, 30);
        panel.add(labelQueue);
        
        labelOccupied = new JLabel("Occupied:");
        labelOccupied.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelOccupied.setForeground(Color.RED);
        labelOccupied.setBounds(962, 20, 120, 30);
        panel.add(labelOccupied);
        
        labelAvailable = new JLabel("Available:");
        labelAvailable.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelAvailable.setForeground(Color.GREEN);
        labelAvailable.setBounds(1080, 20, 120, 30);
        panel.add(labelAvailable);
    }
    
    /**
     * Updates all status fields
     * 
     * @param       count           number of calls remaining in the simulation
     * @param       queue           number of customers in queue
     * @param       occupied        number of occupied techs
     * @param       available       number of available techs
     */
    public void updateStatus(int count, int queue, int occupied, int available) {
        labelCount.setText(String.format("Remaining: %d", count));
        labelQueue.setText(String.format("In Queue: %d", queue));
        labelOccupied.setText(String.format("Occupied: %d", occupied));
        labelAvailable.setText(String.format("Available: %d", available));
    }
}
