package frc.robot.util;

public class ActionSetpoint {
    private final double vK;
    private final double angleSetpoint;
    private final double vF;

    ActionSetpoint(double vK, double aS, double vF) {
        this.vK = vK;
        this.angleSetpoint = aS;
        this.vF = vF;
    }

    public double getFlywheelV() {
        return vK;
    }

    public double getFeederV() {
        return vF;
    }

    public double getAngleSetpoint() {
        return angleSetpoint;
    }
}
