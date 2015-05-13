package so.tio.uptime;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CountedTime{
    private int fullMinutes = 0;

    CountedTime(int minutes){
        this.fullMinutes = minutes;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", this.getHours(), this.getMinutes());
    }

    public Image toImage() {
        Image image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.getGraphics();

        // Transparent background
        graphics.setColor(new Color(0, true));
        graphics.fillRect(0, 0, 32, 32);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));

        graphics.drawString(String.format("%d", this.getHours()), 1, 10);
        graphics.drawString(String.format("%02d", this.getMinutes()), 1, 22);
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