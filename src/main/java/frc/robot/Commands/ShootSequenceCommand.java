package frc.robot.Commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotMap.Coordinates;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PoseEstimator;
import frc.robot.subsystems.Shamper;
import frc.robot.util.FlywheelLookupTable;

public class ShootSequenceCommand extends SequentialCommandGroup {
    Shamper shooter = Shamper.getInstance();
    Intake intake = Intake.getInstance();
    PoseEstimator poseEstimator = PoseEstimator.getInstance();
    FlywheelLookupTable lookupTable = FlywheelLookupTable.getInstance();
    Pose2d target = DriverStation.getAlliance().equals(DriverStation.Alliance.Blue) ? Coordinates.BLUE_SPEAKER : Coordinates.RED_SPEAKER;

    public ShootSequenceCommand() {
        addCommands(
        );
    }
}
