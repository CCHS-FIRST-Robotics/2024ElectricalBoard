package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.*;

public class Arm extends SubsystemBase{
    private ArmIO io;
    private ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

    public Arm(ArmIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("arm", inputs);
    }

    public void setPosition(Measure<Angle> position) {
        io.setPosition(position);
    }
}