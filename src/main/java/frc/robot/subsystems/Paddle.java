// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;

import static frc.robot.generated.MechanismConstants.Elevator.*;

/** Add your docs here. */
public class Paddle {
    // Paddle Motor 
    TalonFX m_paddle = new TalonFX(PADDLE_MOTOR_ID);
    PIDController m_pid = new PIDController(PADDLE_kP, PADDLE_kI, PADDLE_kD);
    
    // Stops motor
    public void stop() {
        m_paddle.stopMotor(); // yes it rotates to nothing, because it doesnt move lol
    }

    public void toPaddleSetpoint() {
       m_pid.setSetpoint(PADDLE_BLOCK_SETPOINT); // Fixed Setpoint the Paddle rotates to
       calculateOutput();
    }

    public void returnToStart() {
        m_pid.setSetpoint(PADDLE_STOW_SETPOINT);
        calculateOutput();
    }

    public void calculateOutput() {
        double output = m_pid.calculate(getPaddlePos());
        m_paddle.set(output);
    }

    public double getPaddlePos() {
        return m_paddle.getPosition().getValueAsDouble();
    }
}
