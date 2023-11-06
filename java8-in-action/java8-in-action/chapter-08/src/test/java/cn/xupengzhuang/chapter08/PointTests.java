package cn.xupengzhuang.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class PointTests {
    @Test
    public void test1(){
        Point p1 = new Point(5,10);
        Point p2 = p1.moveRightBy(10);
        assertEquals(15,p2.getX());
        assertEquals(10,p2.getY());
    }

    @Test
    public void test2(){
        Point p1 = new Point(15,10);
        Point p2 = new Point(15,10);
        int i = Point.compareByXAndThenByY.compare(p1, p2);
        assertEquals(0,i);
    }

    @Test
    public void testMoveAllPointRightBy(){
        List<Point> points = Arrays.asList(new Point(5,10), new Point(10,10));
        List<Point> newPoint = Point.moveAllPointRightBy(points, 5);

        List<Point> expectedList = Arrays.asList(new Point(10,10), new Point(15,10));
        assertEquals(expectedList,newPoint);
    }
}
