package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.units.*;

public class MotorIOSparkMax implements MotorIO {
    private CANSparkMax motor;
    private RelativeEncoder encoder;

    public MotorIOSparkMax(int id){
        motor = new CANSparkMax(id, MotorType.kBrushless);
        encoder = motor.getEncoder(); 
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> position){
        encoder.setPosition(position.in(Rotations) / (2 * Math.PI));
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        inputs.motorCurrent = motor.getOutputCurrent();
        inputs.motorVoltage = motor.getBusVoltage();
        inputs.motorPosition = encoder.getPosition();
        inputs.motorVelocity = encoder.getVelocity();
        inputs.motorTemperature = motor.getMotorTemperature();
    }
}