/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Auto.*;
import frc.robot.commands.BallIntake.*;
import frc.robot.commands.Climb.*;
import frc.robot.commands.Conveyor.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Spinner.*;
import frc.robot.commands.Turret.*;
import frc.robot.commands.Vision.PIDVisionFindTarget;
import frc.robot.commands.Vision.PIDVisionTurretTarget;
import frc.robot.commands.Vision.VisionFindTarget;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and default commands are defined here...
  
  //Subsystems
  private final Drive drive;
  private final Climber climber;
  private final BallIntake ballIntake;
  private final Conveyor conveyor;
  private final Shooter shooter;
  private final Spinner spinner;
  private final Turret turret;
  private final Vision vision;
  private final Auto auto;

  //Commands
  private final DefaultDrive defaultdrive;
  private final DefaultIntake defaultintake;
  private final DefaultTurret defaultturret;

  BarrelRacingPath barrelRacingPath;
  ShootAndCrossLineAuto shootAndCrossLineAuto;
  CrossLineAndShootAuto crossLineAndShootAuto;
  DriveSquareAuto driveSquareAuto;
  DriveSquareAutoPID driveSquareAutoPID;
  SlalomPath slalomPath;
  DriveTriangleAuto driveTriangleAuto;
  SendableChooser<Command> m_chooser;
  
  //OI
  private static RobotContainer robotContainer;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Subsystem instantiation
    drive = Drive.getInstance();
    climber = Climber.getInstance();
    ballIntake = BallIntake.getInstance();
    conveyor = Conveyor.getInstance();
    shooter = Shooter.getInstance();
    spinner = Spinner.getInstance();
    turret = Turret.getInstance();
    vision = Vision.getInstance();
    auto = Auto.getInstance();

    //Default command instantiation
    defaultdrive = new DefaultDrive(drive);
    drive.setDefaultCommand(defaultdrive);
    defaultintake = new DefaultIntake(ballIntake);
    ballIntake.setDefaultCommand(defaultintake);
    defaultturret = new DefaultTurret(turret);
    turret.setDefaultCommand(defaultturret);

    // Auto command instantiation
    barrelRacingPath = new BarrelRacingPath();
    driveSquareAuto = new DriveSquareAuto();
    driveSquareAutoPID = new DriveSquareAutoPID();
    slalomPath = new SlalomPath();
    driveTriangleAuto = new DriveTriangleAuto();
    crossLineAndShootAuto = new CrossLineAndShootAuto();
    shootAndCrossLineAuto = new ShootAndCrossLineAuto();
    m_chooser = new SendableChooser<>();
    m_chooser.setDefaultOption("ShootAndCrossLineAuto", shootAndCrossLineAuto);
    m_chooser.addOption("CrossLineAndShootAuto", crossLineAndShootAuto);
    SmartDashboard.putData(m_chooser);
    //OI Device instantiation
    OI.getInstance();


    // Register commands with TestingDashboard commands
    DefaultDrive.registerWithTestingDashboard();
    DriveDistance.registerWithTestingDashboard();
    TurnAngle.registerWithTestingDashboard();
    PIDTurnAngle.registerWithTestingDashboard();
    PIDDriveDistance.registerWithTestingDashboard();
    DriveSquareAuto.registerWithTestingDashboard();
    DriveSquareAutoPID.registerWithTestingDashboard();
    SlalomPath.registerWithTestingDashboard();
    DriveTriangleAuto.registerWithTestingDashboard();
    BarrelRacingPath.registerWithTestingDashboard();
    DefaultIntake.registerWithTestingDashboard();
    DefaultTurret.registerWithTestingDashboard();
    SpinConveyorHTimed.registerWithTestingDashboard();
    SpinConveyorVTimed.registerWithTestingDashboard();
    SpinIntakeRoller.registerWithTestingDashboard();
    SpinSpinnerMotorTimed.registerWithTestingDashboard();
    SpinSpinnerToColor.registerWithTestingDashboard();
    SpinSpinner3Times.registerWithTestingDashboard();
    DeploySpinner.registerWithTestingDashboard();
    RetractSpinner.registerWithTestingDashboard();
    TimedForward.registerWithTestingDashboard();
    SpinBothConveyorsTimed.registerWithTestingDashboard();
    SpinShooter.registerWithTestingDashboard();
    TurretStop.registerWithTestingDashboard();
    TurretLeft.registerWithTestingDashboard();
    TurretRight.registerWithTestingDashboard();
    ShootAndCrossLineAuto.registerWithTestingDashboard();
    CrossLineAndShootAuto.registerWithTestingDashboard();
    ShootBallsAuto.registerWithTestingDashboard();
    MoveBalls.registerWithTestingDashboard();
    MoveBallsAfterDelay.registerWithTestingDashboard();
    BallIntakeDown.registerWithTestingDashboard();
    BallIntakeUp.registerWithTestingDashboard();
    ShooterUp.registerWithTestingDashboard();
    ShooterDown.registerWithTestingDashboard();
    PIDTopShooter.registerWithTestingDashboard();
    PIDBottomShooter.registerWithTestingDashboard();
    PIDVisionShoot.registerWithTestingDashboard();
    Wait.registerWithTestingDashboard();
    Climb.registerWithTestingDashboard();
    FeedBalls.registerWithTestingDashboard();
    ClimberUp.registerWithTestingDashboard();
    ClimberDown.registerWithTestingDashboard();
    Release.registerWithTestingDashboard();
    TurnToLimit.registerWithTestingDashboard();
    PIDTurnToCenter.registerWithTestingDashboard();
    SpitBalls.registerWithTestingDashboard();
    PIDTurnToAngle.registerWithTestingDashboard();
    BouncePath.registerWithTestingDashboard();
    Rotate.registerWithTestingDashboard();
    PIDVisionFindTarget.registerWithTestingDashboard();
    PIDVisionTurretTarget.registerWithTestingDashboard();
    VisionFindTarget.registerWithTestingDashboard();
    VisionShootBallsAuto.registerWithTestingDashboard();

    // Create Testing Dashboard
    TestingDashboard.getInstance().createTestingDashboard();
  }

  /**
   * <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton Method</a> to return one instance of a class
   * @return New instance of RobotContainer class
   */
  public static RobotContainer getInstance() {
   if (robotContainer == null) {
     robotContainer = new RobotContainer();
   } 
   return robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // TODO: This needs to be changed to collect the autonomous command
    // from a chooser on ShuffleBoard
    return m_chooser.getSelected();
    // Create a voltage constraint to ensure we don't accelerate too fast
    // Run path following command, then stop at the end.
    //return ramseteCommand.andThen(() -> drive.tankDriveVolts(0, 0));
  }
}
