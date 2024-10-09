package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;
import edu.wpi.first.units.*;

public interface ArmIO {
    @AutoLog
    public static class ArmIOInputs {
        public double motorCurrent;
        public double motorVoltage;
        public double motorPosition;
        public double motorVelocity;
        public double motorTemperature;
    }

    public default void setPosition(Measure<Angle> position){
    }

    public default void updateInputs(ArmIOInputs inputs) {
    }
}