package so.tio.workwatch;

import java.awt.*;
import java.io.*;
import java.util.Properties;

public class SettingsManager {
    private Properties properties = new Properties();
    private File file;
    public enum Mode {Uptime, Remain, Clock, Till};
    SettingsManager(String fileName){
        try {
            file = new File(fileName);
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encodeColor(Color col){
        return String.format("#%06X", (0xFFFFFF & col.getRGB()));
    }

    public static Color decodeColor(String col){
        return Color.decode(col);
    }

    Mode getMode(){
        return Mode.valueOf(properties.getProperty("mode", Mode.Uptime.toString()));
    }

    void setMode(Mode m){
        properties.setProperty("mode", m.toString());
        this.save();
    }

    Color getTextColor(){
        return decodeColor(properties.getProperty("textColor", "#ffffff"));
    }

    void setTextColor(Color color){
        properties.setProperty("textColor", encodeColor(color));
        this.save();
    }

    Color getBackgroundColor(){
        return decodeColor(properties.getProperty("backgroundColor", "#000000"));
    }

    void setBackgroundColor(Color color){
        properties.setProperty("backgroundColor", encodeColor(color));
        this.save();
    }

    int getWorkingTime(){
        return Integer.valueOf(properties.getProperty("workingTime", "8"));
    }

    void setWorkingTime(int hours){
        properties.setProperty("workingTime", String.valueOf(hours));
    }

    int getFontSize(){
        return Integer.valueOf(properties.getProperty("fontSize", "12"));
    }

    void setFontSize(int size){
        properties.setProperty("fontSize", String.valueOf(size));
        this.save();
    }

    int getOffsetX(){
        return Integer.valueOf(properties.getProperty("offsetX", "1"));
    }

    void setOffsetX(int x){
        properties.setProperty("offsetX", String.valueOf(x));
        this.save();
    }

    int getOffsetY(){
        return Integer.valueOf(properties.getProperty("offsetY", "10"));
    }

    void setOffsetY(int y){
        properties.setProperty("offsetY", String.valueOf(y));
        this.save();
    }
    void save(){
        try {
            properties.store(new FileOutputStream(file), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
