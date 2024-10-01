package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;

import frc.robot.Constants;
import frc.robot.subsystems.motors.FourMotors;

public class ControlWithJoysticks extends Command{
    FourMotors motors;
    Supplier<Double> leftXSupplier;
    Supplier<Double> leftYSupplier;
    Supplier<Double> rightXSupplier;
    Supplier<Double> rightYSupplier;

    
    public ControlWithJoysticks(
        FourMotors motors,
        Supplier<Double> leftXSupplier,
        Supplier<Double> leftYSupplier,
        Supplier<Double> rightXSupplier,
        Supplier<Double> rightYSupplier
    ){
        addRequirements(motors);
        this.motors = motors;
        this.leftXSupplier = leftXSupplier;
        this.leftYSupplier = leftYSupplier;
        this.rightXSupplier = rightXSupplier;
        this.rightYSupplier = rightYSupplier;
    }
    
    @Override
    public void execute() {
        // double leftX = leftXSupplier.get();
        // double leftY = leftYSupplier.get();
        // double rightX = rightXSupplier.get();
        // double rightY = rightYSupplier.get();

        // motors.startMotor(0, applyPreferences(leftX));
        // motors.startMotor(1, applyPreferences(leftY));
        // motors.startMotor(2, applyPreferences(rightX));
        // motors.startMotor(3, applyPreferences(rightY));
    }

    public double applyPreferences(double input){
        if(Math.abs(input) < Constants.ANALOG_DEADZONE){
            return 0; 
        }
        return input * 12;
    }
}