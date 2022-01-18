package CHAPTER09;

import CHAPTER09.Test.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Testing {
    public void _test(){
        try {
            testMoveRightBy();
            testCompareTwoPoints();
            testMoveAllPointsRightBy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMoveRightBy() throws Exception{
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);
        Assertions.assertEquals(15, p2.getX());
        //Assertions.assertEquals(14, p2.getX());
        Assertions.assertEquals(5, p2.getY());
    }

    @Test
    public void testCompareTwoPoints() throws Exception{
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        Assertions.assertTrue(result < 0);
        //Assertions.assertTrue(result == 0);
    }

    @Test
    public void testMoveAllPointsRightBy() throws Exception{
        List<Point> points = Arrays.asList(new Point(5,5), new Point(10,5));
        List<Point> expectedPoints = Arrays.asList(new Point(15,5), new Point(20,5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        Assertions.assertEquals(expectedPoints, newPoints); //equals 메서드 재정의 필요
    }

}
