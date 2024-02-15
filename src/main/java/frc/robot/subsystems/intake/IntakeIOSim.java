package frc.robot.subsystems.intake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants;

public class IntakeIOSim implements IntakeIO {
  private final DCMotorSim sim =
      new DCMotorSim(DCMotor.getNEO(1), Constants.GEAR_RATIO_INTAKE_MOTOR, 0.001);

  private double appliedVolts = 0.0;

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    sim.setInputVoltage(appliedVolts);
    sim.update(Constants.SIM_STEP_TIME);

    inputs.appliedVolts = appliedVolts;
    inputs.currentAmps = sim.getCurrentDrawAmps();
  }

  @Override
  public void setVoltage(double volts) {
    appliedVolts = volts;
    sim.setInputVoltage(volts);
  }

  @Override
  public void stop() {
    setVoltage(0);
  }
}
