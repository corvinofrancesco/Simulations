/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colorado.phet.acidbasesolutions;

import edu.colorado.phet.acidbasesolutions.constants.ABSConstants;
import edu.colorado.phet.common.phetcommon.application.PhetApplication;
import edu.colorado.phet.common.phetcommon.application.PhetApplicationConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 *
 * @author Francesco
 */
public class AcidBaseSolutionsApplet extends JApplet implements ActionListener {

    JFrame fFrame;
    JMenuItem fMenuClose;
    JMenuItem fMenuOpen;
    
    
    private JFrame launchSim(){
        PhetApplicationConfig config = 
                new PhetApplicationConfig( new String[]{"",""}, ABSConstants.PROJECT, "" );
        config.getLookAndFeel().initLookAndFeel();

        final PhetApplication app = new AcidBaseSolutionsApplication(null);
        try {
            SwingUtilities.invokeAndWait( new Runnable() {
                public void run() {
                    //showSplashWindow( config.getName() );
                    app.startApplication();
                    //disposeSplashWindow();
                }
            } );
        } catch (InterruptedException ex) {
            Logger.getLogger(AcidBaseSolutionsApplet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AcidBaseSolutionsApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app.getPhetFrame();
    }
    
    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("File");
        fMenuOpen = new JMenuItem("Open");
        m.add(fMenuOpen);
        fMenuOpen.addActionListener(this);

        fMenuClose = new JMenuItem("Close");
        m.add(fMenuClose);
        fMenuClose.addActionListener(this);
        mb.add(m);

        setJMenuBar(mb);

        
        fFrame = launchSim();
        fFrame.setVisible(true);
        fMenuOpen.setEnabled(false);
        fMenuClose.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Close")) {
            close();
        } else { // Open
            if (fFrame == null) {
                //fFrame = new ParticleFrame(this);
                fFrame.setVisible(true);
                fMenuOpen.setEnabled(false);
                fMenuClose.setEnabled(true);
            }
        }
    }
    
    /** Close the frame. **/
    void close() {
        fFrame.dispose();
        fFrame = null;
        fMenuOpen.setEnabled(true);
        fMenuClose.setEnabled(false);
    }
    
}
