package so.tio.uptime;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        workingHoursSpinner.setValue(sm.getWorkingTime());
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
                if(textColor == null)
                    return;
                sm.setTextColor(textColor);
                textColorPanel.setBackground(textColor);
            }
        });
        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color bgColor = JColorChooser.showDialog(backgroundColorButton, "Choose Background Color", null);
                if(bgColor == null)
                    return;
                sm.setBackgroundColor(bgColor);
                backgroundColorPanel.setBackground(bgColor);
            }
        });
        workingHoursSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newHours = Integer.valueOf(workingHoursSpinner.getValue().toString());
                sm.setWorkingTime(newHours);
            }
        });
    }

}
