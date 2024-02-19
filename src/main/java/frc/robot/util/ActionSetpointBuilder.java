package frc.robot.util;

public class ActionSetpointBuilder {
    private final double vK;
    private final double angleSetpoint;
    private final double vF;

    public ActionSetpointBuilder(double vK, double angleSetpoint, double vF) {
        this.vK = vK;
        this.angleSetpoint = angleSetpoint;
        this.vF = vF;
    }

    public ActionSetpoint build() {
        return new ActionSetpoint(vK, angleSetpoint, vF);
    }
}
