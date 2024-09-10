package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class LinearAcceleration extends Command{
    FourMotors motors;

    double t = 0; 
    int duration;
    int maxVoltage;

    public LinearAcceleration(FourMotors motors, int duration, int maxVoltage){
        // addRequirements(motors);
        this.motors = motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
    }
    
    @Override
    public void execute() {
        motors.setAllMotorVoltage(((double) (((int) t / 10) % 2 == 0 ? t % 10 : 10 - t % 10) / 10d) * maxVoltage);
        t += 0.02; 
    }

    @Override
    public boolean isFinished(){
        return t > duration;
    }
}