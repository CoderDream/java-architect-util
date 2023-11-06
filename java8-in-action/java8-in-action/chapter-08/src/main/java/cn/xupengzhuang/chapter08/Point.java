package cn.xupengzhuang.chapter08;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x){
        return new Point(this.x + x,this.y);
    }

    public static List<Point> moveAllPointRightBy(List<Point> points,int x){
        return points.stream().map(p -> new Point(p.getX()+x,p.getY())).collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public final static Comparator<Point> compareByXAndThenByY = comparing(Point::getX).thenComparing(comparing(Point::getY));

}
