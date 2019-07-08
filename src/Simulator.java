

package src;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Conor
 */
public class Simulator {

    private JFrame frame;
    private GLProfile glprofile;
    private GLCapabilities glcapabilities;
    private GLCanvas glcanvas;
    private JPanel controlPanel;
    private JButton buildButton, pauseButton;
    private ButtonActionListener buttonListener;
    
    enum State{
        PAUSED, BUILDING
    }
    
    private State state;
    
    
    private volatile boolean flag = true;
    
    
    private DLAData dlaData;
    private MyOpenGLEventListener glListener;
    private SetupPanel setupPanel;
    private JDialog setupDialog;
    private JButton createButton;
    
    public Simulator(){
        dlaData = new DLAData();
        buttonListener = new ButtonActionListener();
        setupPanel = new SetupPanel();
        setupPanel.setup();
        createButton =  new JButton("create");
        createButton.addActionListener(buttonListener);
        setupDialog = new JDialog((JDialog)null, true);
        setupDialog.setTitle("DLA Setup");
        setupDialog.setSize(360,300);
        setupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setupPanel.add(createButton);
        setupDialog.add(setupPanel);
        setupDialog.setVisible(true);
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        controlPanel = new JPanel();
        buildButton = new JButton("build");
        pauseButton = new JButton("pause");
        
        
        
        buildButton.addActionListener(buttonListener);
        pauseButton.addActionListener(buttonListener);
        pauseButton.setEnabled(false);
        controlPanel.add(buildButton);
        controlPanel.add(pauseButton);
        frame.add(controlPanel, BorderLayout.PAGE_END);
        glprofile = GLProfile.getDefault();
        glcapabilities = new GLCapabilities( glprofile );
        glcanvas = new GLCanvas( glcapabilities );
        glcanvas.setPreferredSize(new Dimension(640,480));
        glListener = new MyOpenGLEventListener(dlaData);
        glcanvas.addGLEventListener(glListener);
        frame.getContentPane().add(glcanvas);
        frame.pack();
        state = State.PAUSED;
    }
    
    
    public void start(){
        glcanvas.repaint();
        while(flag){
            if(state == State.BUILDING){
                glcanvas.repaint(); 
            }
        }
    }
    
    
    class ButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == createButton){
                
                dlaData.setColourScheme(setupPanel.getColourScheme());
                dlaData.setConnectType(setupPanel.getConnectionType());
                dlaData.setNoParticles(setupPanel.getNoParticles());
                dlaData.setPosition(setupPanel.getXPos(), setupPanel.getYPos());
                dlaData.setShowUnStuck(setupPanel.showUnstuck());
                dlaData.setSize(setupPanel.getZoneWidth(), setupPanel.getZoneHeight());
                
                
                setupDialog.dispose();
                
            }else if(e.getSource() == buildButton){
                state = State.BUILDING;
                buildButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }else if(e.getSource() == pauseButton){
                state = State.PAUSED;
                buildButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
            
            
        }
    }
    
}
