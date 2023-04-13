import util.math.*;
import util.plot.Plot;

import java.util.HashSet;

public class Main {

    private static Vec2[] generatePoints(int amount, double min_x, double max_x, double min_y, double max_y) {
        HashSet<Double> used_x = new HashSet<>();
        Vec2[] points = new Vec2[amount];

        for (int i = 0; i < amount; i++) {
            double p_x = Math.random() * (max_x - min_x + 1) + min_x;

            while(used_x.contains(p_x)) {
                p_x = Math.random() * (max_x - min_x + 1) + min_x;
            }
            used_x.add(p_x);

            points[i] = new Vec2(
                    Math.random() * (max_x - min_x + 1) + min_x,
                    Math.random() * (max_y - min_y + 1) + min_y
            );
        }

        return points;
    }
    public static void main(String[] args) {
        Vec2[] points = Main.generatePoints(10, -10, 10, -5, 5);

        LagrangeInterpolation li = new LagrangeInterpolation(points);
        Plot p = new Plot();

        p.addPolynomial(li);
        p.addPoints(points);

        // p.addPolynomial(new SinFunction());
    }
}