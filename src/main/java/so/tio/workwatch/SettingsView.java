package so.tio.workwatch;

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
    private JRadioButton uptimeRadioButton;
    private JRadioButton remainRadioButton;
    private JSpinner fontSizeSpinner;
    private JSpinner offsetXSpinner;
    private JSpinner offsetYSpinner;
    private JRadioButton clockRadioButton;
    private JTabbedPane tabbedPane1;
    private SettingsManager sm = SettingsManagerFacility.getSettingsManager();

    public SettingsView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonClose);
        // Setting up controls from config
        backgroundColorPanel.setBackground(sm.getBackgroundColor());
        textColorPanel.setBackground(sm.getTextColor());

        workingHoursSpinner.setValue(sm.getWorkingTime());
        fontSizeSpinner.setValue(sm.getFontSize());
        offsetXSpinner.setValue(sm.getOffsetX());
        offsetYSpinner.setValue(sm.getOffsetY());

        switch (sm.getMode()){
            case Uptime:
                uptimeRadioButton.setSelected(true);
                break;
            case Remain:
                remainRadioButton.setSelected(true);
                break;
            case Clock:
                clockRadioButton.setSelected(true);
                break;
        }
        // Adding handlers to controls
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        ActionListener modeClicked  = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sm.setMode(SettingsManager.Mode.valueOf(e.getActionCommand()));
            }
        };
        uptimeRadioButton.setActionCommand(SettingsManager.Mode.Uptime.toString());
        uptimeRadioButton.addActionListener(modeClicked);
        remainRadioButton.setActionCommand(SettingsManager.Mode.Remain.toString());
        remainRadioButton.addActionListener(modeClicked);
        clockRadioButton.setActionCommand(SettingsManager.Mode.Clock.toString());
        clockRadioButton.addActionListener(modeClicked);

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
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSize = Integer.valueOf(fontSizeSpinner.getValue().toString());
                sm.setFontSize(newSize);
            }
        });
        offsetXSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newX = Integer.valueOf(offsetXSpinner.getValue().toString());
                sm.setOffsetX(newX);
            }
        });
        offsetYSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newY = Integer.valueOf(offsetYSpinner.getValue().toString());
                sm.setOffsetY(newY);
            }
        });
    }

    @Override
    public void pack() {
        super.pack();
        this.setTitle("Work watch settings");
        this.setMinimumSize(new Dimension(contentPane.getWidth(), contentPane.getHeight()));
    }
}
