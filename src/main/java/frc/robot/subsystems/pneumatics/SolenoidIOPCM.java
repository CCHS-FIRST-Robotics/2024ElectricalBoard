package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.*;

public class SolenoidIOPCM implements SolenoidIO{
    Solenoid solenoid;

    public SolenoidIOPCM(int id){
        solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, id);
    }

    @Override
    public void set(boolean on){
        solenoid.set(on);
    }

    @Override
    public void updateInputs(SolenoidIOInputs inputs){
        inputs.on = solenoid.get();
    }
}