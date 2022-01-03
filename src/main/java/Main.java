import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int controller;
    public static void main(String[] args) {
        controller = 0 ;
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);
        GlCanvas glCanvas = new GlCanvas();
        gc.addGLEventListener(glCanvas);
        gc.addKeyListener(new MyKeyListener());
        gc.setSize(1600, 900);

        final FPSAnimator animator
                = new FPSAnimator(gc, 60,true );
        animator.start();


        final JFrame frame = new JFrame("Algomaze");
        frame.getContentPane().add(gc);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        Scanner scanner = null;
        try {
            scanner = new Scanner( new File("src/main/resources/readme.txt") );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String help = scanner.useDelimiter("\\A").next();
        scanner.close();

        JOptionPane.showMessageDialog(null,help);
    }
}
