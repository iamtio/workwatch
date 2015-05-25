package so.tio.uptime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsView extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JSpinner workingHoursSpinner;
    private JButton textColorButton;
    private JButton backgroundColorButton;
    private JPanel backgroundColorPanel;
    private JPanel textColorPanel;
    private SettingsManager sm = SettingsManagerFacility.getSettingsManager();

    public SettingsView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonClose);

        backgroundColorPanel.setBackground(sm.getBackgroundColor());
        textColorPanel.setBackground(sm.getTextColor());

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        textColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color textColor = JColorChooser.showDialog(textColorButton, "Choose text Color", null);
                sm.setTextColor(textColor);
                textColorPanel.setBackground(textColor);
            }
        });
        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color textColor = JColorChooser.showDialog(backgroundColorButton, "Choose Background Color", null);
                sm.setBackgroundColor(textColor);
                backgroundColorPanel.setBackground(textColor);
            }
        });
    }

}
