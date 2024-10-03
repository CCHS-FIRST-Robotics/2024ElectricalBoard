package frc.robot.subsystems.motors;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.BaseStatusSignal;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;

    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;
    
    public MotorIOTalonFX(int id) {
        motor = new TalonFX(id);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();
    }

    @Override
    public void setVoltage(double volts) {
        motor.setVoltage(volts);
    }

    @Override
    public double getPosition() {
        return positionSignal.getValue();
    }

    public void setPosition(double deggres) {
        motor.setPosition(deggres / 360 );
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        BaseStatusSignal.refreshAll(voltageSignal, currentSignal, positionSignal, velocitySignal, temperatureSignal);

        inputs.motorVoltage = voltageSignal.getValue();  // Fixed: Motor voltage
        inputs.motorCurrent = currentSignal.getValue();  // Fixed: Motor current
        inputs.motorPosition = positionSignal.getValue();  // Correctly assigned
        inputs.motorVelocity = velocitySignal.getValue();  // Fixed: Motor velocity
        inputs.motorTemperature = temperatureSignal.getValue();  // Correctly assigned
    }
}
