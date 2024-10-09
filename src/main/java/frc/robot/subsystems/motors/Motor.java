package frc.robot.subsystems.motors;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.units.*;

public class Motor {
    private MotorIO io;
    private MotorIOInputsAutoLogged inputs = new MotorIOInputsAutoLogged();
    private int index;

    public Motor(MotorIO io, int index) {
        this.io = io;
        this.index = index;
    }

    public void setVoltage(Measure<Voltage> volts){
        io.setVoltage(volts);
    }

    public void setPosition(Measure<Angle> position){
        io.setPosition(position);
    }

    public void updateInputs(){
        io.updateInputs(inputs);
        Logger.processInputs("motor " + Integer.toString(index), inputs);
    }
}