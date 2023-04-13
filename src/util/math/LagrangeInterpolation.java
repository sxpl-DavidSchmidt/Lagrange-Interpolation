package util.math;

public class LagrangeInterpolation extends MathFunction {
    private final Vec2[] points;

    public LagrangeInterpolation (Vec2[] points) {
        this.points = points;
    }

    public double at(double x) {
        double ret = 0;
        for (int i = 0; i < points.length; i++) {
            double at_zero = 1;
            double scale_i = 1;

            // at zero function
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    at_zero *= x - points[j].getX();
                    scale_i *= points[i].getX() - points[j].getX();
                }
            }

            ret += (points[i].getY() / scale_i) * at_zero;
        }
        return ret;
    }
}
