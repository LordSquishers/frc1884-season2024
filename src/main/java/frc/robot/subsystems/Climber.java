package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

import java.util.function.DoubleSupplier;

public class Climber extends SubsystemBase {
    private static Climber instance;
    private final CANSparkBase leaderMotor;
    private final CANSparkBase followerMotor;

    private Climber() {
        leaderMotor = new CANSparkMax(RobotMap.ClimberMap.FOLLOWER, MotorType.kBrushless);
        followerMotor = new CANSparkMax(RobotMap.ClimberMap.LEADER, MotorType.kBrushless);

        leaderMotor.restoreFactoryDefaults();
        followerMotor.restoreFactoryDefaults();

        leaderMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        followerMotor.setIdleMode(CANSparkBase.IdleMode.kBrake);

        leaderMotor.setSmartCurrentLimit(30);
        followerMotor.setSmartCurrentLimit(30);

        // leaderMotor.setInverted(false);

        followerMotor.follow(leaderMotor, true);
        leaderMotor.burnFlash();
        followerMotor.burnFlash();
        //followerMotor.setInverted(true);
    }

    public static Climber getInstance() {
        if (instance == null) instance = new Climber();
        return instance;
    }

    public Command run(DoubleSupplier power) {
        return new InstantCommand(() -> leaderMotor.set(power.getAsDouble() / 5.0), this);
    }


    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Climber Power", () -> leaderMotor.get(), (s) -> leaderMotor.set(s));

    }
}
