package so.tio.uptime;


public class TimeGetter {
    static CountedTime getUptime(){
        int minutes = (int)(System.nanoTime() / 1000 / 1000 / 1000 / 60);
        return new CountedTime(minutes);
    }

    static CountedTime getRemain(){
        return new CountedTime(8 * 60).minus(getUptime());
    }
}

