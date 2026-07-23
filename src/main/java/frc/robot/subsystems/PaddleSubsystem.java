// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PaddleSubsystem extends SubsystemBase {
  TalonFX m_pivotMotor = new TalonFX(0);

  /** Creates a new PaddleSubsystem. */
  public PaddleSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pivotMotorRun() {
    m_pivotMotor.set(0.5);
  }

  public void pivotMotorRunBack() {
    m_pivotMotor.set(-0.5);
  }
}
