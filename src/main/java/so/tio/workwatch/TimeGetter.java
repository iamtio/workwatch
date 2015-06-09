package so.tio.workwatch;

import java.util.Date;

public class TimeGetter {
    private static SettingsManager sm = SettingsManagerFacility.getSettingsManager();
    static CountedTime getUptime(){
        int minutes = (int)(System.nanoTime() / 1000 / 1000 / 1000 / 60);
        return new CountedTime(minutes);
    }

    static CountedTime getRemain(){
        return new CountedTime(sm.getWorkingTime() * 60).minus(getUptime());
    }

    static CountedTime getTime(){
        return new CountedTime(new Date());
    }
}

