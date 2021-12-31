import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);
        MainMenu mainMenu = new MainMenu();
        gc.addGLEventListener(mainMenu);
        gc.setSize(1600, 900);

        final JFrame frame = new JFrame("Algomaze");
        frame.getContentPane().add(gc);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
