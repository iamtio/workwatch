package so.tio.uptime;

public class CountedTime{
    private int hours = 0;
    private int minutes = 0;

    CountedTime(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    CountedTime(int minutes){
        for(;minutes >= 60; minutes-=60){
            this.hours += 1;
        }
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", hours, minutes);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}