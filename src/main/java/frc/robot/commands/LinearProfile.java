package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;
import frc.robot.Constants;

public class LinearProfile extends Command{
    FourMotors motors;
    int duration;
    int maxVoltage;
    int halfWavelength;
    
    double t = 0; 
    double totalVolts = 0;

    public LinearProfile(FourMotors motors, int duration, int maxVoltage, int quarterWavelength){
        addRequirements(motors);
        this.motors = motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
        this.halfWavelength = quarterWavelength;
    }
    
    @Override
    public void execute() {
        double change = maxVoltage / (halfWavelength * (1 / Constants.PERIOD));
        
        if (((int) t / halfWavelength) % 2 == 0) { // increasing
            totalVolts += change;
        } else { // decreasing
            totalVolts -= change;
        }

        motors.setAllMotorVoltage(totalVolts);
        t += Constants.PERIOD; 
    }

    @Override
    public boolean isFinished(){
        return t > duration;
    }
}