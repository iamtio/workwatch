package so.tio.workwatch;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notification extends JDialog {
    private JPanel contentPane;
    private JButton xButton;

    public Notification() {
        setContentPane(contentPane);
        setModal(true);
        setUndecorated(true);
        setAlwaysOnTop(true);
        AWTUtilities.setWindowOpacity(this, (float)0.8);
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getRootPane().setDefaultButton(xButton);
    }
    @Override
    public void pack() {
        super.pack();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        int y = 0;
        setLocation(x, y);
        this.setMinimumSize(new Dimension(contentPane.getWidth(), contentPane.getHeight()));
    }
}
