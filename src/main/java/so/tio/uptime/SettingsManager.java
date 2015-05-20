package so.tio.uptime;

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

    boolean getRemainFlag(){
        return properties.getProperty("showRemain", Boolean.toString(false)).equals(Boolean.toString(true));
    }

    void setRemainFlag(boolean flag){
        properties.setProperty("showRemain", Boolean.toString(flag));
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
