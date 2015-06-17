package so.tio.workwatch;

import static org.junit.Assert.*;

public class CountedTimeTest {
    @org.junit.Test
    public void testCountedTimeToString(){
        assertEquals("0:20 OK", new CountedTime(20).toString(), "0:20");
        assertEquals("1:10 OK", new CountedTime(70).toString(), "1:10");
        assertEquals("8:01 OK", new CountedTime(481).toString(), "8:01");
        assertEquals("11:10 OK", new CountedTime(670).toString(), "11:10");
    }
    @org.junit.Test
    public void testCountedTime(){
        CountedTime time = new CountedTime(75);
        assertEquals("Hour OK", time.getHours(), 1);
        assertEquals("Minutes OK", time.getMinutes(), 15);
        assertEquals("Full minutes OK", time.getFullMinutes(), 75);
    }
    @org.junit.Test
    public void testCountedTimeMinus(){
        CountedTime time = new CountedTime(75);
        time = time.minus(new CountedTime(4));
        assertEquals("Minus OK", time.getFullMinutes(), 71);
    }
}