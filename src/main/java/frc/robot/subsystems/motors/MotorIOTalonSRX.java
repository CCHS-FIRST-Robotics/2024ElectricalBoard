package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.units.*;

public class MotorIOTalonSRX implements MotorIO {
    private TalonSRX motor;
    
    public MotorIOTalonSRX(int id){
        motor = new TalonSRX(id);
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.set(TalonSRXControlMode.PercentOutput, volts.in(Volts) / 12);
    }

    @Override
    public void setPosition(Measure<Angle> position){
        motor.set(TalonSRXControlMode.Position, motor.getSelectedSensorPosition() + position.in(Rotations)); // ! not in radians
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        inputs.motorCurrent = motor.getStatorCurrent();
        inputs.motorVoltage = motor.getBusVoltage();
        inputs.motorPosition = motor.getSelectedSensorPosition();
        inputs.motorVelocity = motor.getSelectedSensorVelocity();
        inputs.motorTemperature = motor.getTemperature();
    }
}