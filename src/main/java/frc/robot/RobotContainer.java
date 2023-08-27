package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    private final Joystick controller = new Joystick(0);

    private final int translationAxis = 1;
    private final int strafeAxis = 0;
    private final int rotationAxis = 5;

    private final JoystickButton zeroGyro = new JoystickButton(controller, PS4Controller.Button.kTriangle.value);
    private final JoystickButton robotCentric = new JoystickButton(controller, PS4Controller.Button.kL1.value);
    private final JoystickButton halfSpeed = new JoystickButton(controller, 1);
    private final JoystickButton quarterSpeed = new JoystickButton(controller, 4);
    private final JoystickButton changeMode = new JoystickButton(controller, 3);
    private final JoystickButton lock = new JoystickButton(controller, 9);

    private final Swerve s_Swerve = new Swerve();
    private final Mode mode = Mode.getInstance();

    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -controller.getRawAxis(translationAxis),
                () -> -controller.getRawAxis(strafeAxis), 
                () -> -controller.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        halfSpeed.onTrue(new InstantCommand(() -> mode.changeSpeed(.5)));
        halfSpeed.onFalse(new InstantCommand(() -> mode.changeSpeed(1)));
        quarterSpeed.onTrue(new InstantCommand(() -> mode.changeSpeed(.25)));
        quarterSpeed.onFalse(new InstantCommand(() -> mode.changeSpeed(1)));
        changeMode.onTrue(new InstantCommand(() -> mode.changeMode()));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
