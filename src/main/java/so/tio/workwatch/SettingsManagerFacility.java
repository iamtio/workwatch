package so.tio.workwatch;

import java.io.File;

public class SettingsManagerFacility {
    private static SettingsManager sm = null;

    static SettingsManager getSettingsManager(){
        if(sm == null) {
            String homeDir = System.getProperty("user.home");
            sm = new SettingsManager(new File(homeDir, ".workwatch.properties").toString());
        }
        return sm;
    }
}
