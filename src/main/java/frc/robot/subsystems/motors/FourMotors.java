package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FourMotors extends SubsystemBase{
    private Motor[] motors = new Motor[4];

    public FourMotors(Motor motor0, Motor motor1, Motor motor2, Motor motor3){
        motors[0] = motor0;
        motors[1] = motor1;
        motors[2] = motor2;
        motors[3] = motor3;
    }

    public void periodic() {
        for(Motor motor : motors){
            motor.periodic();
        }
    }

    public void startMotor(int index, double volts){
        motors[index].setVoltage(volts);
    }

    public void stopAll(){
        for(Motor motor : motors){
            motor.setVoltage(0);
        }
    }
}