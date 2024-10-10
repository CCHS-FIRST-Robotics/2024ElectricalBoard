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

    Measure<Angle> angle = Radians.of(0);
    
    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();

        // ! somehow set the starting position as the 0

        PIDF.kP = 20;
        PIDF.kD = 0;
        PIDF.kI = 0;
        PIDF.kS = 0;
        PIDF.kV = 0;
        PIDF.kA = 0;

        motionMagicConfig.MotionMagicCruiseVelocity = 80; // motor max rps
        motionMagicConfig.MotionMagicAcceleration = 10;
        motionMagicConfig.MotionMagicJerk = 0;

        motor.getConfigurator().apply(motorConfig);
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> position){
        iteratePosition();
        // motor.setControl(motorMotionMagicVoltage.withPosition(position.in(Rotations)).withSlot(0));
    }

    public void iteratePosition(){
        motor.setControl(motorMotionMagicVoltage.withPosition(angle.in(Rotations)).withSlot(0));
        System.out.println(angle.in(Rotations));

        angle = Radians.of(angle.in(Radians) + Math.PI / 2);
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