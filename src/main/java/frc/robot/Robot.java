// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  CANSparkMax frontRightMotor = new CANSparkMax(Constants.FRONT_RIGHT_ID, MotorType.kBrushless);
  CANSparkMax frontLeftMotor = new CANSparkMax(Constants.FRONT_LEFT_ID, MotorType.kBrushless);
  CANSparkMax backRightMotor = new CANSparkMax(Constants.BACK_RIGHT_ID, MotorType.kBrushless);
  CANSparkMax backLeftMotor = new CANSparkMax(Constants.BACK_LEFT_ID, MotorType.kBrushless);

  XboxController controller = new XboxController(0);

  @Override
  public void robotInit() {

    // Factory Defaults
    frontRightMotor.restoreFactoryDefaults();
    frontLeftMotor.restoreFactoryDefaults();
    backRightMotor.restoreFactoryDefaults();
    backLeftMotor.restoreFactoryDefaults();

    // Coast Mode
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);

    // Inversions
    frontRightMotor.setInverted(Constants.FRONT_RIGHT_INVERT);
    frontLeftMotor.setInverted(Constants.FRONT_LEFT_INVERT);
    backRightMotor.setInverted(Constants.BACK_RIGHT_INVERT);
    backLeftMotor.setInverted(Constants.BACK_LEFT_INVERT);

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    frontRightMotor.set(0);
    frontLeftMotor.set(0);
    backRightMotor.set(0);
    backLeftMotor.set(0);
  }

  @Override
  public void teleopPeriodic() {

    double drive = controller.getLeftY();
    drive = (Math.abs(drive) < Constants.JOYSTICK_DEADBAND) ? 0 : drive * Constants.MAX_SPEED_PERCENT;

    double turn = controller.getRightX();
    turn = (Math.abs(turn) < Constants.JOYSTICK_DEADBAND) ? 0 : turn * Constants.MAX_SPEED_PERCENT;

    frontRightMotor.set(drive - turn);
    backRightMotor.set(drive - turn);
    frontLeftMotor.set(drive + turn);
    backLeftMotor.set(drive + turn);

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
