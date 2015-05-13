package so.tio.uptime;

public class CountedTime{
    private int fullMinutes = 0;

    CountedTime(int minutes){
        this.fullMinutes = minutes;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", this.getHours(), this.getMinutes());
    }

    public int getHours() {
        return fullMinutes / 60;
    }

    public int getMinutes() {
        return fullMinutes % 60;
    }

    public int getFullMinutes() {
        return fullMinutes;
    }

    public void setFullMinutes(int minutes) {
        this.fullMinutes = minutes;
    }

    public CountedTime minus(CountedTime another) {
        return new CountedTime(this.getFullMinutes() - another.getFullMinutes());
    }
}