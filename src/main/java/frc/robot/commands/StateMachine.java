
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

import frc.robot.subsystems.motors.Motor;
import frc.robot.subsystems.motors.MotorIOTalonFX;

public class StateMachine extends Command { 


    MotorIOTalonFX Io = new MotorIOTalonFX(Constants.TALONFX_ID);
    Motor motor = new Motor(Io);
    
    
    private static final int SPEEDUP_COUNT = 3000 / 20;
    private static final int HOLD_COUNT = 1000 / 20;
    private static final int SLOWDOWN_COUNT = 2000 / 20;
    private static final int IDOL_COUNT = 2000 / 20;
    private static final float STEP_SIZE = 12 / (float) SPEEDUP_COUNT;
    private static final float SLOWDOWN_SIZE = 12 / (float) SLOWDOWN_COUNT;

    public enum State {IDLE, SPEEDUP, HOLD, SLOWDOWN};
    State m_state;
    int m_counter = 0;
    float motorvoltage = 0;
    int cycle;
    int stop;


    public StateMachine(int cycles) {
        this.stop = cycles + 1;
        this.cycle = 0;
        //addRequirements(motor);

    }

    
    /** Called once at the beginning of the robot program. */
    @Override
    public void initialize() {
        m_state = State.IDLE;
        m_counter = IDOL_COUNT;
        System.out.println("m_counter"+ m_counter);
    }

    /*
    * The RobotPeriodic function is called every control packet no matter the
    * robot mode.
    */
    @Override
    public void execute() {

        System.out.println("Robot executed at: " + System.currentTimeMillis());

        switch(m_state) {
            case IDLE: 
                System.out.println("IDLE");
                 if (m_counter == 0)
                {
                    m_state = State.SPEEDUP;
                    m_counter = SPEEDUP_COUNT;
                    this.cycle++;
                }
                else m_counter--;
                break;
                
                

            case SPEEDUP:
                System.out.println("SPEEDUP");
                motorvoltage += (STEP_SIZE);
                motorvoltage = Math.min(motorvoltage, 12);
                if (m_counter == 0)
                {
                    m_state = State.HOLD;
                    m_counter = HOLD_COUNT;
                }
                else m_counter--;
                break;

            case HOLD:
                System.out.println("HOLD");
                if (m_counter == 0)
                {
                    m_state = State.SLOWDOWN;
                    m_counter = SLOWDOWN_COUNT;
                }
                else m_counter--;
                break;

            case SLOWDOWN:
                System.out.println("SLOWDOWN");
                motorvoltage -= SLOWDOWN_SIZE;
                motorvoltage = Math.max(motorvoltage, 0); // Prevent going below 0V
                if (m_counter == 0)
                {
                    m_state = State.IDLE;
                    m_counter = IDOL_COUNT;
                   
                }
                else m_counter--;
                break;
           
        }
         motor.setVoltage(motorvoltage);

    }

    @Override
    public boolean isFinished(){
        return this.cycle >= stop;
    }

    
}