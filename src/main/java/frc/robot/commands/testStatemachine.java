package frc.robot.commands;

public class testStatemachine {
    
    // Constants
    private static final int SPEEDUP_COUNT = 3000 / 20;
    private static final int HOLD_COUNT = 1000 / 20;
    private static final int SLOWDOWN_COUNT = 2000 / 20;
    private static final int IDOL_COUNT = 2000 / 20;
    private static final float STEP_SIZE = 12 / (float) SPEEDUP_COUNT;
    private static final float SLOWDOWN_SIZE = 12 / (float) SLOWDOWN_COUNT;
    
    // Enum
    public enum State {IDLE, SPEEDUP, HOLD, SLOWDOWN}
    
    // Instance Variables
    private State m_state;
    private int m_counter;
    private float motorvoltage;

    // Constructor
    public testStatemachine() {
        m_state = State.IDLE;
        m_counter = IDOL_COUNT;
        motorvoltage = 0;
        System.out.println("Initialized with m_counter: " + m_counter);
        System.out.println("Step size: " + STEP_SIZE);
        System.out.println("Slowdown size: " + SLOWDOWN_SIZE);
    }

    // Execute method
    public void execute() {
        System.out.println("Robot executed at: " + System.currentTimeMillis());

        switch (m_state) {
            case IDLE: 
                System.out.println("IDLE");
                if (m_counter == 0) {
                    m_state = State.SPEEDUP;
                    m_counter = SPEEDUP_COUNT;
                } else {
                    m_counter--;
                }
                break;
               
                

            case SPEEDUP:
                System.out.println("SPEEDUP");
                if (m_counter == 0) {
                    m_state = State.HOLD;
                    m_counter = HOLD_COUNT;
                } else {
                    motorvoltage += STEP_SIZE;
                    motorvoltage = Math.min(motorvoltage, 12);
                    m_counter--;
                }
                break;

            case HOLD:
                System.out.println("HOLD");
                if (m_counter == 0) {
                    m_state = State.SLOWDOWN;
                    m_counter = SLOWDOWN_COUNT;
                } else {
                    m_counter--;
                }
                break;

            case SLOWDOWN:
                System.out.println("SLOWDOWN");
               
                if (m_counter == 0) {
                    m_state = State.IDLE;
                    m_counter = IDOL_COUNT;
                } else {
                    motorvoltage -= SLOWDOWN_SIZE; // Decrement voltage during slowdown
                    motorvoltage = Math.max(motorvoltage, 0); // Prevent going below 0V
                    m_counter--;
                }
                break;
        }
        
        System.out.println("Current motor voltage: " + motorvoltage);
    }

    // Main method
    public static void main(String[] args) {
        testStatemachine stateMachine = new testStatemachine();

        // Simulate execution
        for (int i = 0; i < 10000; i++) {
            stateMachine.execute();
            try {
                Thread.sleep(20); // Simulate time delay for each execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
