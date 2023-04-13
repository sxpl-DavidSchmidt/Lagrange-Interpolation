package util.plot;

import util.math.MathFunction;
import util.math.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JPanel {
    private ArrayList<Vec2> points;
    private ArrayList<MathFunction> polynomials;
    private double range_start;
    private double range_end;
    private int iterations;
    private double x_scale = 25;
    private double y_scale = 25;

    public Graph(double range_start, double range_end, int accuracy) {
        super();
        this.range_start = range_start;
        this.range_end = range_end;
        this.iterations = accuracy;

        points = new ArrayList<>();
        polynomials = new ArrayList<>();
    }

    public void addPolynomial(MathFunction plm) {
        this.polynomials.add(plm);
    }

    public void addPoint(Vec2 point) {
        this.points.add(point);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawLine(getWidth()/2, 0, getWidth() / 2, getHeight());
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        for (int i = -10; i <= 10; i++) {
            int x = (int) transform_x(i);
            int y = (int) transform_y(i);
            g.drawLine(x, (int) transform_y(-0.2), x, (int) transform_y(.2));
            g.drawLine((int) transform_x(-.2), y, (int) transform_x(.2), y);
        }

        for (MathFunction plm : polynomials)
            drawPolynomial(g, plm);

        for (Vec2 p : points)
            drawPoint(g, p);
    }

    private void drawPolynomial(Graphics g, MathFunction plm) {
        long t1 = System.currentTimeMillis();

        double increment = (range_end - range_start) / (double) iterations;
        Vec2 last = new Vec2(range_start, plm.at(range_start));
        transform_in_place(last);

        for (double c_x = range_start + increment; c_x <= range_end; c_x += increment) {
            Vec2 current = new Vec2(c_x, plm.at(c_x));
            transform_in_place(current);

            int x1 = (int) last.x;
            int y1 = (int) last.y;
            int x2 = (int) current.x;
            int y2 = (int) current.y;

            last = current;
            g.drawLine(x1, y1, x2, y2);
        }

        System.out.println(System.currentTimeMillis() - t1);
    }

    private void drawPoint(Graphics g, Vec2 point) {
        int radius = 5;
        Vec2 tmpp = transform(point);
        g.drawOval((int) (tmpp.getX() - radius / 2.), (int) (tmpp.getY() - radius / 2.), radius, radius);
    }

    private void transform_in_place(Vec2 point) {
        point.x = getWidth() / 2. + point.x * x_scale;
        point.y = getHeight() / 2. - point.y * y_scale;
    }

    private Vec2 transform(Vec2 point) {
        return new Vec2(getWidth() / 2. + point.x * x_scale, getHeight() / 2. - point.y * y_scale);
    }

    private double transform_x(double x) {
        return getWidth() / 2. + x * x_scale;
    }

    private double transform_y(double y) {
        return getHeight() / 2. + y * y_scale;
    }
}
