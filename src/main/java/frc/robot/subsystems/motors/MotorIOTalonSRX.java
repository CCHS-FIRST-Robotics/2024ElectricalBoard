package frc.robot.subsystems.motors;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class MotorIOTalonSRX implements MotorIO {
    private TalonSRX motor;
    
    public MotorIOTalonSRX(int id){
        motor = new TalonSRX(id);
    }

    @Override
    public void setVoltage(double volts) {
        motor.set(TalonSRXControlMode.PercentOutput, volts / 12);
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