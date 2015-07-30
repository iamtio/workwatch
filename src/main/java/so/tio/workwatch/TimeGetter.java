package so.tio.workwatch;

import java.util.Date;

public class TimeGetter {
    private static SettingsManager sm = SettingsManagerFacility.getSettingsManager();
    private static int getUpMinutes(){
        return (int)(System.nanoTime() / 1000 / 1000 / 1000 / 60);
    }
    static CountedTime getUptime(){
        return new CountedTime(getUpMinutes());
    }

    static CountedTime getRemain(){
        return new CountedTime(sm.getWorkingTime() * 60).minus(getUptime());
    }

    static CountedTime getTime(){
        return new CountedTime(new Date());
    }

    static CountedTime getTill(){
        CountedTime now = new CountedTime(new Date());
        now.setFullMinutes(now.getFullMinutes() - getUpMinutes() + (sm.getWorkingTime() * 60));
        return now;
    }
}

