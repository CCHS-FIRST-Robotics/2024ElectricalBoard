package frc.robot.subsystems.motors;

import org.littletonrobotics.junction.AutoLog;

public interface MotorIO {

    @AutoLog
    public static class MotorIOInputs {
        public double motorCurrent;
        public double motorVoltage;
        public double motorPosition;
        public double motorVelocity;
        public double motorTemperature;
    }

    public default void setVoltage(double volts) {
    }

    public default double getPosition(){
        return 0.0;
    }


    public default void resetPosition(int posit){
    }

    public default void updateInputs(MotorIOInputs inputs) {
    }
}