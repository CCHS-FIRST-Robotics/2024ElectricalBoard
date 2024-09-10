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

    FourMotors motors = new FourMotors(
        new Motor( new MotorIOTalonSRX(Constants.TALONFX_ID)), // kraken
        new Motor(new MotorIOTalonSRX(Constants.SPARKMAX_ID)), // neo
        new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_1)), // cim1
        new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_2)) // cim2
    );
    Pneumatics pneumatics = new Pneumatics(Constants.PISTON_ID_1, Constants.PISTON_ID_2);

    public RobotContainer() {
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
        controller.x().onTrue(new InstantCommand(() -> motors.startMotor(0, 8)));
        controller.x().onTrue(new Quadratic(motors));
        controller.y().onTrue(new InstantCommand(() -> motors.startMotor(1, 8)));
        controller.b().onTrue(new InstantCommand(() -> motors.startMotor(2, 8))
                    .andThen(new InstantCommand(() -> motors.startMotor(3, 8))));
        controller.a().onTrue(new InstantCommand(() -> motors.stopAll()));

        //-----Pneumatics-----//
        // controller.b().onTrue(new InstantCommand(() -> pneumatics.togglePiston1()));
        // controller.a().onTrue(new InstantCommand(() -> pneumatics.togglePiston2()));

        //-----DIO-----//
        nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        nintendo2.onTrue(new InstantCommand(() -> System.out.println("switch 2")));
        irSensor.onTrue(new InstantCommand(() -> System.out.println("beam broken")));
    }
}