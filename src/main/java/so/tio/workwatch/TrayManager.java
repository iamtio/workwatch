package so.tio.workwatch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayManager {
    private SystemTray systemTray;
    private TrayIcon icon = null;
    private boolean wantExit = false;

    class SystemTrayNotSupported extends Error {
    }

    TrayManager() {
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported by your system");
            throw new SystemTrayNotSupported();
        }
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

        MenuItem settings = new MenuItem("Show settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsView dialog = new SettingsView();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        menu.add(settings);
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
