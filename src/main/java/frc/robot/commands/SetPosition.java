package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.Motor;
import frc.robot.subsystems.motors.MotorIOTalonFX;
import frc.robot.Constants;

public class SetPosition extends Command {

    private MotorIOTalonFX io;
    private Motor motor;
    private PIDController pid; 
    private double kP = 0.01;
    private double kI = 0.0;
    private double kD = 0.0;
    private double outputAngle = 90;

    public SetPosition() {
        io = new MotorIOTalonFX(Constants.TALONFX_ID);
        motor = new Motor(io);
        pid = new PIDController(kP, kI, kD);
        motor.setPosition(90);
    }

    @Override
    public void execute() {
        double currentPosition = motor.getPosition();
        double output = pid.calculate(currentPosition, outputAngle);
        motor.setVoltage(output); 
    }

    @Override
    public boolean isFinished() {
        return pid.atSetpoint();
    }
}
