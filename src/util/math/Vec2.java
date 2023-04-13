package util.math;

import java.awt.geom.Point2D;

public class Vec2 extends Point2D {
    public double x;
    public double y;

    public Vec2(double x_position, double y_position) {
        this.x = x_position;
        this.y = y_position;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Vec2: " + x + " " + y;
    }
}
