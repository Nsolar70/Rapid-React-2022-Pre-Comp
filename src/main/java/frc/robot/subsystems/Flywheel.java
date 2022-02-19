// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;


public class Flywheel extends SubsystemBase {
  
  //Flywheel Motor
  private final WPI_TalonFX flywheelMotor = new WPI_TalonFX(ManipulatorConstants.kflywheelMotor);

  // The network table 
  private static final String TABLE_NAME = "Preferences";
  
  private static final NetworkTable m_table;
  private ShuffleboardTab VariablesTab = Shuffleboard.getTab("Variables");
  private NetworkTableEntry FlywheelInSpeed = VariablesTab.add("FlywheelIn", 1.0).getEntry();
  private NetworkTableEntry FlywheelOutSpeed = VariablesTab.add("Flywheelout", -1.0).getEntry();
  /** Creates a new Flywheel. */
  public Flywheel() {

   // Configures the Intake Victors's to default configuration
   flywheelMotor.configFactoryDefault();
  
   // Invert motor direction if set to true
   flywheelMotor.setInverted(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
  public void ballIn() {
    double InSpeed = FlywheelInSpeed.getDouble(1.0);
    flywheelMotor.set(InSpeed);
  }

  public void ballOut () {
    double OutSpeed = FlywheelOutSpeed.getDouble(-1.0);
    flywheelMotor.set(OutSpeed);  
  }

   // Stop Motion
   public void stopMotion() {
    flywheelMotor.set(0.0);
  
  }

  static {
   m_table = NetworkTableInstance.getDefault().getTable(TABLE_NAME);
   m_table.getEntry(".type").setString("RobotPreferences");
  }

 }

