// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.ejml.dense.block.MatrixOps_DDRB;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.generated.MechanismConstants.Paddle.*;

public class PaddleSubsystem extends SubsystemBase {
  TalonFX m_pivotMotor = new TalonFX(PADDLE_MOTOR_ID);
  TalonFXConfiguration m_pivotConfig = new TalonFXConfiguration();

  /** Creates a new PaddleSubsystem. */
  public PaddleSubsystem() {
    pivotConfigs();
    m_pivotMotor.getConfigurator().apply(m_pivotConfig);
  }

  @Override
  public void periodic() { 
    System.out.println("Rotor: "+ pivotRotorPosition());
    System.out.println("Motor: " + pivotMotorPosition());
    
    // This method will be called once per scheduler run
  }

  public void pivotMotorRun() {
    System.out.println("Deploying");
    m_pivotMotor.set(0.25);
  }

  public void pivotMotorRunBack() {
    System.out.println("Stowing");
    m_pivotMotor.set(-0.1);
  }

  public void pivotMotorStop() {
    m_pivotMotor.stopMotor();
  }

  /*Returns rotations of internal shaft */
  public StatusSignal<Angle> pivotRotorPosition() {
    return m_pivotMotor.getRotorPosition();
  }

  public StatusSignal<Angle> pivotMotorPosition() {
    return m_pivotMotor.getPosition();
  }

  public void pivotConfigs() {
    m_pivotConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    m_pivotConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;

    // Units: Rotations
    m_pivotConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 10;
    m_pivotConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold = -0.01;

    m_pivotConfig.Feedback.SensorToMechanismRatio = 64;
  }
}
