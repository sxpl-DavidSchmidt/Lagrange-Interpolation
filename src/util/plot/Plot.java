package util.plot;

import util.math.MathFunction;
import util.math.Vec2;

import javax.swing.*;

public class Plot extends JFrame {
    private final Graph graph;
    public Plot(double range_start, double range_end, int accuracy) {
        super("Plot");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graph = new Graph(range_start, range_end, accuracy);
        add(graph);
    }

    public Plot() {
        this(-100, +100, 10000);
    }

    public void addPolynomial(MathFunction plm) {
        graph.addPolynomial(plm);
    }

    public void addPoint(Vec2 point) {
        graph.addPoint(point);
    }

    public void addPoints(Vec2[] points) {
        for (Vec2 p : points)
            this.addPoint(p);
    }
}
