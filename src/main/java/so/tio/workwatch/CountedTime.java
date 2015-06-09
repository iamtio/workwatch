package so.tio.workwatch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;

public class CountedTime{
    private int fullMinutes = 0;
    private SettingsManager sm = SettingsManagerFacility.getSettingsManager();

    CountedTime(int minutes){
        this.fullMinutes = minutes;
    }
    CountedTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.fullMinutes = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", this.getHours(), this.getMinutes());
    }

    public Image toImage() {
        Image image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.getGraphics();

        // Transparent background
        graphics.setColor(sm.getBackgroundColor());
        graphics.fillRect(0, 0, 32, 32);

        graphics.setColor(sm.getTextColor());
        graphics.setFont(new Font("Arial", Font.PLAIN, sm.getFontSize()));

        graphics.drawString(String.format("%d", this.getHours()), sm.getOffsetX(), sm.getOffsetY());
        graphics.drawString(String.format("%02d", this.getMinutes()), sm.getOffsetX(),
                sm.getOffsetY() + sm.getFontSize());
        graphics.dispose();
        return image;
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