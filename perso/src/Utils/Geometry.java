package Utils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Geometry {

    public static Point.Double segmentLinetIntersection(Point.Double segmentP1, Point.Double segmentP2, Point.Double lineP1, Point.Double lineP2)
    {
        return segmentLinetIntersection(new Line2D.Double(segmentP1, segmentP2), new Line2D.Double(lineP1, lineP2));
    }

    public static Point.Double segmentLinetIntersection(Line2D segment, Line2D line)
    {
        double x1 = segment.getX1();
        double y1 = segment.getY1();
        double x2 = segment.getX2();
        double y2 = segment.getY2();
        double x3 = line.getX1();
        double y3 = line.getY1();
        double x4 = line.getX2();
        double y4 = line.getY2();
        double num = (x1-x3)*(y3-y4)-(y1-y3)*(x3-x4);
        double den = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
        if(den == 0)
            return null;

        double t = num/den;
        if(t > 1 || t < 0)
            return null;
        return new Point2D.Double(x1+t*(x2-x1), y1+t*(y2-y1));
    }

    /*public static Point.Double circleLineIntersection(Point.Double segStart, Point.Double segEnd, Point.Double circleCenter, double radius)
    {
        double norm;

        Point.Double segDir = new Point2D.Double(segEnd.getX()-segStart.getX(), segEnd.getY()-segStart.getY());
        norm = Math.sqrt(segDir.getX()+segDir.getY());
        segDir = new Point2D.Double(segDir.getX()/norm, segDir.getY()/norm);
        Point.Double normal_line_dir = new Point2D.Double(-segDir.getY(), segDir.getX());
        Point.Double normal_intersection = segmentLinetIntersection(segStart, segEnd, circleCenter, new Point2D.Double(circleCenter.getX()+normal_line_dir.getX(), circleCenter.getY()+normal_line_dir.getY()));
        if(normal_intersection == null)
            return null;
        double dist = Math.sqrt(circleCenter.getX()-normal_intersection.getX()+circleCenter.getY()-normal_intersection.getY());
        if(dist > radius)
            return null;
        double offset_lenght = Math.sqrt(Math.pow(radius, 2) - Math.pow(dist, 2));
        return new Point2D.Double(normal_intersection.getX()-offset_lenght*segDir.getX(), normal_intersection.getY()-offset_lenght*segDir.getY());
    }*/

    public static Point.Double circleLineIntersection(Point.Double pointA, Point.Double pointB, Point.Double center, double radius)
    {
        if(dist(pointA, center) + dist(center, pointB) - dist(pointA, pointB) > 0.01)
            return null;

        double baX = pointB.x - pointA.x;
        double baY = pointB.y - pointA.y;
        double caX = center.x - pointA.x;
        double caY = center.y - pointA.y;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return null;
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;

        Point.Double p1 = new Point.Double(pointA.getX() - baX * abScalingFactor1, pointA.getY() - baY * abScalingFactor1);

        return p1;

    }

    private static double dist(Point.Double p1, Point.Double p2)
    {
        double dist = (p1.getX()-p2.getX())*(p1.getX()-p2.getX());
        dist += (p1.getY()-p2.getY())*(p1.getY()-p2.getY());
        return Math.sqrt(dist);
    }
}
