package so.tio.uptime;

import static org.junit.Assert.*;

public class CountedTimeTest {
    @org.junit.Test
    public void testCountedTime(){
        assertEquals("0:20 OK", new CountedTime(20).toString(), "0:20");
        assertEquals("1:10 OK", new CountedTime(70).toString(), "1:10");
        assertEquals("8:01 OK", new CountedTime(481).toString(), "8:01");
        assertEquals("11:10 OK", new CountedTime(670).toString(), "11:10");
    }
}