// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

private final CANSparkMax m_climberMotor = new CANSparkMax(Constants.ManipulatorConstants.kclimberMotor, MotorType.kBrushless);

private RelativeEncoder m_encoder;

 
  public Climber() {
    m_climberMotor.restoreFactoryDefaults();
    m_climberMotor.setIdleMode(IdleMode.kBrake);

    m_encoder = m_climberMotor.getEncoder();

  }
  // need to go 9 revs
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());

    SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());


  }
}
