// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Swerve;

public class LockWheels extends InstantCommand {
  Swerve swerve;
  double angle;

  public LockWheels(Swerve swerve, double angle) {
    this.angle = angle;
    this.swerve = swerve;
    
    addRequirements(swerve);
  }

  @Override
  public void initialize() {
    
  }
}
