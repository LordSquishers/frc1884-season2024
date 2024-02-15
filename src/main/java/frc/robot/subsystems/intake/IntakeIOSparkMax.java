package frc.robot.subsystems.intake;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

public class IntakeIOSparkMax implements IntakeIO {
  private final CANSparkMax motor =
      new CANSparkMax(CAN_ID_INTAKE_LEADER, CANSparkLowLevel.MotorType.kBrushless);

  public IntakeIOSparkMax() {
    // Motor Controller Config //
    motor.restoreFactoryDefaults();
    motor.setCANTimeout(250);

    // Motor Run Directions //
    motor.setInverted(false);

    motor.enableVoltageCompensation(12.0);
    motor.setSmartCurrentLimit(30);

    motor.burnFlash();
  }

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    inputs.appliedVolts = motor.getAppliedOutput() * motor.getBusVoltage();
    inputs.currentAmps = motor.getOutputCurrent();
  }

  @Override
  public void setVoltage(double volts) {
    motor.setVoltage(volts);
  }

  @Override
  public void stop() {
    motor.stopMotor();
  }
}
