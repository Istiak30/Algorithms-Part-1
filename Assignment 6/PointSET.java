import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;

public class PointSET {

    private final SET<Point2D> all;

    public PointSET() {
        all = new SET<>();
    }

    public boolean isEmpty() {
        return all.isEmpty();
    }

    public int size() {
        return all.size();
    }

    public void insert(
            Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        if (!all.contains(p)) {
            all.add(p);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        return all.contains(p);
    }

    public void draw() {
        for (Point2D p : all) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(
            RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("null rectangle");
        }
        SET<Point2D> list = new SET<>();
        for (Point2D p : all) {
            if (rect.contains(p)) {
                list.add(p);
            }
        }
        return list;
    }

    public Point2D nearest(
            Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        if (all.isEmpty()) {
            return null;
        }
        Point2D n = null;
        for (Point2D q : all) {
            if (n == null) {
                n = q;
                continue;
            }
            if (p.distanceSquaredTo(q) < p.distanceSquaredTo(n)) {
                n = q;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        String filename = StdIn.readLine();
        In in = new In(filename);
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }
        System.out.println(brute.contains(new Point2D(0.956346, 0.295675)));
        System.out.println(brute.isEmpty());
        System.out.println(brute.size());
    }
}
