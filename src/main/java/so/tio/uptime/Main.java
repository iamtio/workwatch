package so.tio.uptime;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) throws Exception {
        TrayText tx = new TrayText();
        while (true) {
            tx.updateTray(UptimeGetter.getUptimeHours());
            Thread.sleep(5000);
        }

    }
}
// TODO: Get work time by API from work calendar at http://basicdata.ru/api/json/calend/
class TrayText {
    private SystemTray systemTray;
    private TrayIcon icon = null;

    class SystemTrayNotSupported extends Error {
    }

    TrayText() {
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported by your system");
            throw new SystemTrayNotSupported();
        }
        this.systemTray = SystemTray.getSystemTray();

    }
    private PopupMenu getMenu(){
        PopupMenu menu = new PopupMenu();
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exit);
        return menu;
    }
    void updateTray(int hours) throws AWTException {
        updateTray(Integer.toString(hours));
    }

    void updateTray(String hours) throws AWTException {
        if (this.icon != null) {
            this.systemTray.remove(this.icon);
            this.icon = null;
        }
        Image trayImage = TextImageBuilder.buildImage(hours);
        this.icon = new TrayIcon(trayImage, null, this.getMenu());
        this.systemTray.add(this.icon);
    }
}

class UptimeGetter {
    static int getUptimeHours() {
        return getUptimeHours(8);
    }

    static int getUptimeHours(int workTime) {
        return (int) (workTime - System.nanoTime() / 1000 / 1000 / 1000 / 60 / 60);
    }
}

class TextImageBuilder {
    static Image buildImage(String text) {
        Image image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.getGraphics();

        // TODO: Use transparent color
        graphics.setColor(new Color(0, true));
        graphics.fillRect(0, 0, 32, 32);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.drawString(text, 3, 16);
        graphics.dispose();
        return image;
    }
}