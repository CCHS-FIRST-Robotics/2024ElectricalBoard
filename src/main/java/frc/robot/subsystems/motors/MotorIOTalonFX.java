
package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import edu.wpi.first.units.*;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;
    private final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    private final Slot0Configs PIDF = motorConfig.Slot0;
    private final MotionMagicVoltage motorMotionMagicVoltage = new MotionMagicVoltage(0);
    private final MotionMagicConfigs motionMagicConfig = motorConfig.MotionMagic;

    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;
    
    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();

        // ! filler values
        PIDF.kP = 10;
        PIDF.kD = 0;
        PIDF.kI = 0;
        PIDF.kS = 10;
        PIDF.kV = 1;
        PIDF.kA = 0;

        motionMagicConfig.MotionMagicCruiseVelocity = 100; // motor max rps
        motionMagicConfig.MotionMagicAcceleration = 1;
        motionMagicConfig.MotionMagicJerk = 0;

        motor.getConfigurator().apply(motorConfig);

        // ! look at this
        // StatusCode status = StatusCode.StatusCodeNotInitialized;
        // for (int i = 0; i < 5; ++i) {
        //     status = motor.getConfigurator().apply(motorConfig);
        //     if (status.isOK())
        //         break;
        // }
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> position){
        motor.setControl(motorMotionMagicVoltage.withPosition(position.in(Rotations)).withSlot(0));
        System.out.println(position.in(Rotations));
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        BaseStatusSignal.refreshAll(voltageSignal, currentSignal, positionSignal, velocitySignal, temperatureSignal);

        inputs.motorCurrent = currentSignal.getValue();
        inputs.motorVoltage = voltageSignal.getValue();
        inputs.motorPosition = positionSignal.getValue();
        inputs.motorVelocity = velocitySignal.getValue();
        inputs.motorTemperature = temperatureSignal.getValue();
    }
}
