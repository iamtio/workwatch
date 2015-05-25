package so.tio.uptime;

import java.awt.*;
import java.io.*;
import java.util.Properties;

public class SettingsManager {
    private Properties properties = new Properties();
    private File file;

    SettingsManager(String fileName){
        try {
            file = new File(fileName);
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encodeColor(Color col){
        return String.format("#%06X", (0xFFFFFF & col.getRGB()));
    }

    private static Color decodeColor(String col){
        return Color.decode(col);
    }

    boolean getRemainFlag(){
        return properties.getProperty("showRemain", Boolean.toString(false)).equals(Boolean.toString(true));
    }

    void setRemainFlag(boolean flag){
        properties.setProperty("showRemain", Boolean.toString(flag));
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
