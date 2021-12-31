import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenu implements GLEventListener {

    private static GraphicsEnvironment graphicsEnvironment;
    public static  DisplayMode dm,dm_old;
    private static Dimension xGraphic;
    private static Point point = new Point(0,0);
    private  GLU glu = new GLU();
    private int textureId;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
            final GL2 gl = glAutoDrawable.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, textureId);
            gl.glPushMatrix();
            gl.glOrtho(0,1,0,1,0,1);
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glPushMatrix();
            gl.glLoadIdentity();
            gl.glDepthMask(false);

            try {
                File background = new File("src/main/resources/mainMenuBackground.png");
                Texture t2 = TextureIO.newTexture(background,true);
                textureId = t2.getTextureObject(gl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            gl.glBegin(GL2.GL_QUADS);
                gl.glTexCoord2f(0f,0f); gl.glVertex2f(0,0);
                gl.glTexCoord2f(0f,1f); gl.glVertex2f(0,1f);
                gl.glTexCoord2f(1f,1f); gl.glVertex2f(1f,1f);
                gl.glTexCoord2f(1f,0f); gl.glVertex2f(1f,0);
            gl.glEnd();
            gl.glDepthMask(true);
            gl.glPopMatrix();



    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
