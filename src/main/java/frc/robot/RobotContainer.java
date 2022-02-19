// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.EjectBall;
import frc.robot.commands.HoldBall;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.RunFlipper;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flipper;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  //Subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Intake m_intake = new Intake();
  private final Flipper m_flipper = new Flipper();
  private final Flywheel m_flywheel = new Flywheel();

 //Commands
  private final IntakeBall m_intakeBall = new IntakeBall(m_intake);
  private final EjectBall m_ejectBall = new EjectBall(m_intake);
  private final HoldBall m_holdBall = new HoldBall(m_intake);
  private final RunFlywheel m_runFlywheel = new RunFlywheel(m_flywheel);
  private final RunFlipper m_runFlipper = new RunFlipper(m_flipper);

 //Controllers
  XboxController m_driverController = new XboxController(OIConstants.kdriveJoyStick);
  XboxController m_actuatorController = new XboxController(OIConstants.kactuatorJoyStick);
 

 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   
    m_driveTrain.setDefaultCommand(new RunCommand(
        () -> m_driveTrain.drive(m_driverController.getRightY(), m_driverController.getRightX()),m_driveTrain));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

     // Run Intake Ball command when the 'X' button is pressed
     new JoystickButton(m_actuatorController, Button.kX.value).whileHeld(m_intakeBall);
     
     // Run Eject Ball command when the 'B' button is pressed
    new JoystickButton(m_actuatorController, Button.kB.value).whileHeld(m_ejectBall);

     //Run Hold Ball command when the 'Left Bumper' button is pressed
     new JoystickButton(m_actuatorController, Button.kLeftBumper.value).toggleWhenPressed(m_holdBall);

     //Run Shoot Ball command when the 'Y' button is pressed
     new JoystickButton(m_actuatorController, Button.kY.value ).toggleWhenPressed(m_runFlywheel);

     // Run RunFlipper command when the 'A' button is pressed
     new JoystickButton(m_actuatorController, Button.kA.value).whenPressed(m_runFlipper);

   /*  if(isIntakeOn = true){
      m_actuatorController.setRumble(GenericHID.RumbleType.kLeftRumble, 1.0);
      m_actuatorController.setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
    } else {
      m_actuatorController.setRumble(GenericHID.RumbleType.kLeftRumble, 0.0);
      m_actuatorController.setRumble(GenericHID.RumbleType.kRightRumble, 0.0); 
    } */

     //new POVButton(m_actuatorController, 0).whileActiveContinuous(m_ejectBall);
     //new POVButton(m_actuatorController, 180).whileActiveContinuous(m_intakeBall);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
