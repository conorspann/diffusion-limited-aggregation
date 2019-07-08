

package src;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Conor
 */
public class SetupPanel extends JPanel{
    private JPanel positionPanel, settingsPanel;
    private TitledBorder positionBorder, settingsBorder;
    private JLabel xPosLabel, yPosLabel, widthLabel, heightLabel, particlesLabel, connectionLabel, colourLabel, showUnStuckLabel;
    private JTextField xPosField, yPosField, widthField, heightField, particlesField;
    private JComboBox<String> connectionBox, colourBox;
    private JCheckBox showUnStuckBox;
    private String[] connectionTypes = {"4-connect", "8-connect"};
    private String[] colourTypes = {"red-green", "red-blue", "green-red", "green-blue", "blue-red", "blue-green"};
    public SetupPanel(){
        positionPanel = new JPanel();
        settingsPanel = new JPanel();
        positionBorder = BorderFactory.createTitledBorder("Positioning");
        settingsBorder = BorderFactory.createTitledBorder("Settings");
        positionPanel.setBorder(positionBorder);
        settingsPanel.setBorder(settingsBorder);
        
        
        xPosLabel = new JLabel("x");
        yPosLabel = new JLabel("y");
        widthLabel = new JLabel("width");
        heightLabel = new JLabel("height");
        particlesLabel = new JLabel("number of particles");
        connectionLabel = new JLabel("connection type");
        colourLabel = new JLabel("colour scheme");
        showUnStuckLabel = new JLabel("show unstuck particles");
        
        xPosField = new JTextField("260", 5);
        yPosField = new JTextField("200", 5);
        widthField = new JTextField("100", 5);
        heightField = new JTextField("100", 5);
        particlesField = new JTextField("1000", 8);
        
        connectionBox = new JComboBox<>(connectionTypes);
        colourBox = new JComboBox<>(colourTypes);
        
        showUnStuckBox = new JCheckBox();
        showUnStuckBox.setSelected(true);
        
        positionPanel.setLayout(new GridLayout(2, 2));
        positionPanel.add(xPosLabel);
        positionPanel.add(xPosField);
        positionPanel.add(yPosLabel);
        positionPanel.add(yPosField);
        positionPanel.add(widthLabel);
        positionPanel.add(widthField);
        positionPanel.add(heightLabel);
        positionPanel.add(heightField);
        
        
        
        
        settingsPanel.setLayout(new GridLayout(4, 4));
        settingsPanel.add(connectionLabel);
        settingsPanel.add(connectionBox);
        settingsPanel.add(colourLabel);
        settingsPanel.add(colourBox);
        settingsPanel.add(particlesLabel);
        settingsPanel.add(particlesField);
        settingsPanel.add(showUnStuckLabel);
        settingsPanel.add(showUnStuckBox);
        
    }
    public void setup(){
        add(positionPanel);
        add(settingsPanel);
    }
    public int getXPos(){
        return Integer.parseInt(xPosField.getText());
    }
    public int getYPos(){
        return Integer.parseInt(yPosField.getText());
    }
    public int getZoneWidth(){
        return Integer.parseInt(widthField.getText());
    }
    public int getZoneHeight(){
        return Integer.parseInt(heightField.getText());
    }
    public DLAData.Connect getConnectionType(){
        switch(connectionBox.getSelectedIndex()){
            case 0:
                return DLAData.Connect.CONNECT_4;
            case 1:
                return DLAData.Connect.CONNECT_8;
            default:
                return DLAData.Connect.CONNECT_4;
        }
    }
    public DLAData.Colour getColourScheme(){
        switch(colourBox.getSelectedIndex()){
            case 0:
                return DLAData.Colour.RG;
            case 1:
                return DLAData.Colour.RB;
            case 2:
                return DLAData.Colour.GR;
            case 3:
                return DLAData.Colour.GB;
            case 4:
                return DLAData.Colour.BR;
            case 5:
                return DLAData.Colour.BG;
            default:
                return DLAData.Colour.RG;
        }
    }
    public int getNoParticles(){
        return Integer.parseInt(particlesField.getText());
    }
    public boolean showUnstuck(){
        return showUnStuckBox.isSelected();
    }
}
