package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Motor;
// import frc.robot.subsystems.motors.
import frc.robot.subsystems.motors.MotorIOTalonFX;
import frc.robot.Constants;

public class Challenges extends Command {
    
    
    private MotorIOTalonFX io;
    private Motor motor;
    private double percent = 1d;//percent of voltage using
    private int time = 0;// ticks
    private int seconds = 10;// amt of seconds to go from 0 to 12
    boolean incresing = true;
    
    public Challenges(){

        io = new MotorIOTalonFX(Constants.TALONFX_ID);
        motor  = new Motor(io);
        


    

    }
    public void dostuff(){
        motor.setVoltage(percent * time * 12d/(50d * seconds));
        if(incresing) time++;
        else time--;
        if (incresing && time == 50 * seconds) incresing = false;
        else if (!incresing && time == 0) incresing = true;

        
        



        
        
    }

    @Override
    public void execute(){
        dostuff();
        
        
        
        


    }
}
