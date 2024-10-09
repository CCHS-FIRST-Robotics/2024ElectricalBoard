package frc.robot.commands;


import static edu.wpi.first.units.Units.*;


import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;

import frc.robot.Constants;
import frc.robot.subsystems.motors.GroupOfMotors;


public class ControlWithJoysticks extends Command{
    GroupOfMotors motors;
    Supplier<Double> leftXSupplier;
    Supplier<Double> leftYSupplier;
    Supplier<Double> rightXSupplier;
    Supplier<Double> rightYSupplier;

    
    public ControlWithJoysticks(
        GroupOfMotors motors,
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
        double leftX = leftXSupplier.get();
        double leftY = leftYSupplier.get();
        double rightX = rightXSupplier.get();
        double rightY = rightYSupplier.get();

        motors.setMotorVoltage(0, Volts.of(applyPreferences(leftX)));
        motors.setMotorVoltage(1, Volts.of(applyPreferences(rightX)));
    
    }

    public double applyPreferences(double input){
        if(Math.abs(input) < Constants.ANALOG_DEADZONE){
            return 0; 
        }
        return input * 12;
    }
}