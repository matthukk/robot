// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax; // unneeded


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final CANSparkMax m_leftDrive1 = new CANSparkMax(1, MotorType.kBrushed);
  private final CANSparkMax m_leftDrive2 = new CANSparkMax(2, MotorType.kBrushed);
  private final CANSparkMax m_rightDrive1 = new CANSparkMax(4, MotorType.kBrushed);
  private final CANSparkMax m_rightDrive2 = new CANSparkMax(3, MotorType.kBrushed);
  private final CANSparkMax m_shooter1 = new CANSparkMax(5, MotorType.kBrushed);
  private final CANSparkMax m_shooter2 = new CANSparkMax(6, MotorType.kBrushed);
  private final DifferentialDrive drive = new DifferentialDrive(m_leftDrive1, m_rightDrive1);
  // private final VictorSPX m_test = new VictorSPX(3);
  // private final DifferentialDrive m_robotDrive =
  //     new DifferentialDrive(m_leftDrive::set, m_rightDrive::set);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();

  public Robot() {
    // SendableRegistry.addChild(m_robotDrive, m_leftDrive);
    // SendableRegistry.addChild(m_robotDrive, m_rightDrive);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive1.setInverted(true);
    m_rightDrive2.setInverted(true);

    // Link motor pairs
    m_rightDrive2.follow(m_rightDrive1);
    m_leftDrive2.follow(m_leftDrive1);
    m_shooter2.follow(m_shooter1);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      drive.arcadeDrive(-0.5, 0.0, false);
    } else {
      drive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {

  drive.arcadeDrive(m_controller.getRawAxis(1), m_controller.getRawAxis(4));
  

  if(m_controller.getRawButton(1)==true){
    m_shooter1.set(0.7);
  }
  else if(m_controller.getRawButton(2)==true){
    m_shooter1.set(-1);
  }
  else if(m_controller.getRawButton(2)==true && m_controller.getRawButton(2)==true){
    m_shooter1.set(0);
  }
  else{
    m_shooter1.set(0);
  }

  }
  
  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

