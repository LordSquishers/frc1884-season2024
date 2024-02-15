package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

/**
 * Intake subsystem consists of one motor w/ solely voltage control. Has one digital sensor to
 * detect game pieces.
 */
public interface IntakeIO {

  @AutoLog
  class IntakeIOInputs {
    public double appliedVolts = 0;
    public double currentAmps = 0;
  }

  /**
   * Updates all loggable inputs for this subsystem.
   *
   * @param inputs Input data
   */
  default void updateInputs(IntakeIOInputs inputs) {}

  /**
   * Run Intake at specified voltage.
   *
   * @param volts Voltage [-12, 12] (V)
   */
  default void setVoltage(double volts) {}

  /** Cuts power to Intake. */
  default void stop() {}
}
