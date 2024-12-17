package walkWithMe;

import java.io.Serializable;

public class Walk implements Serializable {
    private double elevation;
    private double distance;
    private double time;
    private double speed;
    private static final long serialVersionUID = 1L;

    public Walk() {
        this.elevation = 0;
        this.distance = 0;
        this.time = 0;
        this.speed = 0;
    }

    public Walk(double elevation, double distance, double time, double speed) {
        this.elevation = elevation;
        this.distance = distance;
        this.time = time;
        this.speed = speed;
    }

    public double getElevation() { return elevation; }
    public void setElevation(double elevation) { this.elevation = elevation; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public double getTime() { return time; }
    public void setTime(double time) { this.time = time; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    @Override
    public String toString() {
        return "Elevation: " + elevation + " Distance: " + distance + " Time: " + time + " Speed: " + speed;
    }
}