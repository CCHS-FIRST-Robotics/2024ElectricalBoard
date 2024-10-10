package frc.robot.commands;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.GroupOfMotors;

public class SetPosition extends Command {

    
    private PIDController pid; 
    private double kP = 1.0;
    private double kI = 0.0;
    private double kD = 0.0;
    private double outputAngle = 90 * 4098;
    GroupOfMotors motors;

    public SetPosition(GroupOfMotors motors) {
        this.motors = motors;
        pid = new PIDController(kP, kI, kD);
        
    }

    @Override
    public void execute() {
        double currentPosition = motors.getMotorPositsion(0);
        double output = pid.calculate(currentPosition, outputAngle);
        motors.setMotorVoltage(0, Volts.of(output));
    }

    @Override
    public boolean isFinished() {
        return pid.atSetpoint();
    }
}
