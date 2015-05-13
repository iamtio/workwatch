package so.tio.uptime;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        TrayText tx = new TrayText();
        tx.initTray();
        while (!tx.wantExit()) {
            if(tx.getRemainFlag()) {
                tx.updateTray(UptimeGetter.getRemain());
            }else{
                tx.updateTray(UptimeGetter.getUptime());
            }
            Thread.sleep(200);
        }
        System.exit(0);
    }
}

class TrayText {
    private SystemTray systemTray;
    private TrayIcon icon = null;
    private SettingsManager sm;
    private boolean wantExit = false;

    class SystemTrayNotSupported extends Error {
    }

    TrayText() {
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported by your system");
            throw new SystemTrayNotSupported();
        }
        String homeDir = System.getProperty("user.home");
        sm = new SettingsManager(new File(homeDir, ".uptime.properties").toString());
        this.systemTray = SystemTray.getSystemTray();
    }

    boolean getRemainFlag(){
        return sm.getRemainFlag();
    }

    boolean wantExit(){
        return wantExit;
    }

    private PopupMenu getMenu(){
        PopupMenu menu = new PopupMenu();
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sm.save();
                wantExit = true;
            }
        });
        CheckboxMenuItem isRemain = new CheckboxMenuItem("Show remain instead uptime", sm.getRemainFlag());
        isRemain.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                sm.setRemainFlag(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        menu.add(isRemain);
        menu.addSeparator();
        menu.add(exit);
        return menu;
    }

    void initTray() throws AWTException{
        this.icon = new TrayIcon(new CountedTime(0).toImage());
        this.icon.setPopupMenu(this.getMenu());
        this.systemTray.add(this.icon);
    }

    void updateTray(CountedTime time) throws AWTException {
        Image trayImage = time.toImage();
        if(this.icon == null)
            initTray();
        this.icon.setImage(trayImage);
    }
}

class UptimeGetter {
    static CountedTime getUptime(){
        int minutes = (int)(System.nanoTime() / 1000 / 1000 / 1000 / 60);
        return new CountedTime(minutes);
    }

    static CountedTime getRemain(){
        return new CountedTime(8 * 60).minus(getUptime());
    }
}

class SettingsManager {
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
