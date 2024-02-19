package frc.robot.auto;

import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotMap.Coordinates;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeDirection;
import frc.robot.subsystems.PoseEstimator;
import frc.robot.subsystems.Shamper;
import frc.robot.subsystems.Vision.Vision;
import frc.robot.util.FlywheelLookupTable;

public class AutoCommands {
    public static void registerAutoCommands() {
        FlywheelLookupTable lookupTable = FlywheelLookupTable.getInstance();
        Pose2d target = DriverStation.getAlliance().get() == (DriverStation.Alliance.Blue) ? Coordinates.BLUE_SPEAKER
                : Coordinates.RED_SPEAKER;
        PoseEstimator poseEstimator = PoseEstimator.getInstance();
        Shamper shooter = Shamper.getInstance();
        Intake intake = Intake.getInstance();
        NamedCommands.registerCommand("SpoolShooter", shooter.setShootVelocityCommand(() -> lookupTable.get(
                        poseEstimator.getDistanceToPose(target.getTranslation())).getFlywheelV(),
                () -> lookupTable.get(poseEstimator.getDistanceToPose(target.getTranslation())).getFeederV()));
        NamedCommands.registerCommand("Intake", intake.intakeUntilLoadedCommand());
        NamedCommands.registerCommand("Shoot", new SequentialCommandGroup(
                intake.setIntakeState(IntakeDirection.FORWARD),
                new WaitCommand(1),
                intake.setIntakeState(IntakeDirection.STOPPED)
        ));
        NamedCommands.registerCommand("VisionIntake",
                intake.intakeUntilLoadedCommand().alongWith(Vision.getInstance().followNoteCommand().onlyIf(
                        () -> !Vision.getInstance().getNotePose2d().getTranslation().equals(new Translation2d(0, 0)))));
    }
}
