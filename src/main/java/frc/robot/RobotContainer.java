// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.*;
import frc.robot.subsystems.motors.*;
import frc.robot.subsystems.pneumatics.*;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

    Trigger nintendo1 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_1)::get);
    Trigger nintendo2 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_2)::get);
    Trigger irSensor = new Trigger(new DigitalInput(Constants.IR_SENSOR_PORT)::get);

    GroupOfMotors motors = new GroupOfMotors();
    
    Pneumatics pneumatics = new Pneumatics(Constants.PISTON_ID_1, Constants.PISTON_ID_2);

    public RobotContainer() {
        motors.addMotor(new Motor(new MotorIOTalonFX(Constants.TALONFX_ID))); // kraken
        motors.addMotor(new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_1))); // cim

        configureBindings();
    }

    private void configureBindings() {
        //-----Motors-----//

        // joystick controls
        motors.setDefaultCommand(
            new ControlWithJoysticks(
                motors,
                () -> controller.getLeftX(),
                () -> controller.getLeftY(),
                () -> controller.getRightX(),
                () -> controller.getRightY()
            )
        );

        // button controls
        controller.x().onTrue(new LinearProfile(motors, 20, 12, 10));
        controller.y().onTrue(new ExponentialProfile(motors, 20, 12, 10));
        controller.b().onTrue(new InstantCommand(() -> motors.setMotorPosition(0, Math.PI)));
        controller.a().onTrue(new InstantCommand(() -> motors.toggleMotors()));

        //-----Pneumatics-----//
        // controller.b().onTrue(new InstantCommand(() -> pneumatics.togglePiston1()));
        // controller.a().onTrue(new InstantCommand(() -> pneumatics.togglePiston2()));

        //-----DIO-----//
        nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        nintendo2.onTrue(new InstantCommand(() -> System.out.println("switch 2")));
        irSensor.onTrue(new InstantCommand(() -> System.out.println("beam broken")));
    }
}