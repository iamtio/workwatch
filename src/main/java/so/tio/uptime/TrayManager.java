package so.tio.uptime;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TrayManager {
    private SystemTray systemTray;
    private TrayIcon icon = null;
    private SettingsManager sm;
    private boolean wantExit = false;

    class SystemTrayNotSupported extends Error {
    }

    TrayManager() {
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported by your system");
            throw new SystemTrayNotSupported();
        }
        sm = SettingsManagerFacility.getSettingsManager();
        this.systemTray = SystemTray.getSystemTray();
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
        MenuItem settings = new MenuItem("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsView dialog = new SettingsView();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        menu.add(isRemain);
        menu.addSeparator();
        menu.add(settings);
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
