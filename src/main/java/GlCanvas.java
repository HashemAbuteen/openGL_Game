import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GlCanvas implements GLEventListener {

    private static GraphicsEnvironment graphicsEnvironment;
    public static  DisplayMode dm,dm_old;
    private static Dimension xGraphic;
    private static Point point = new Point(0,0);
    private  GLU glu = new GLU();
    private int textureId;

    public static int controller = 0;
    public static int posX = 749;
    public static int posY = 810;
    public static int radius = 10;
    static String mazeAsString = "";




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
        gl.glOrtho(0, 1, 0, 1, 0, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glDepthMask(false);

        if(controller == 0){
            mainMenu(gl);
        }
        else if (controller == 1 ){
            game(gl);
        }
        else if(controller == 2){
            win(gl);
        }




    }


    private void mainMenu(GL2 gl) {
        try {
            File background = new File("src/main/resources/mainMenuBackground.png");
            Texture backgroundTexture = TextureIO.newTexture(background, true);
            textureId = backgroundTexture.getTextureObject(gl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0f, 0f);
        gl.glVertex2f(0, 0);
        gl.glTexCoord2f(0f, 1f);
        gl.glVertex2f(0, 1f);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex2f(1f, 1f);
        gl.glTexCoord2f(1f, 0f);
        gl.glVertex2f(1f, 0);
        gl.glEnd();
        gl.glDepthMask(true);
        gl.glPopMatrix();


    }

    private void game(GL2 gl) {
        Scanner scanner;
        try {
            File maze = new File("src/main/resources/maze1.txt");
            scanner = new Scanner(maze);
            mazeAsString = scanner.next();
        } catch (FileNotFoundException e) {
            System.out.println("Maze file do not exist");
            e.printStackTrace();
        }

        gl.glColor3f(1f,1f,1f);

        for(int i=0;i<9 ; i++){
            for(int j = 0 ; j < 16 ; j++){
                //up
                int index = getIndex( j , i);
                float x1,x2,y1,y2;
                if(mazeAsString.charAt(index ) == '0'){
                    gl.glBegin(GL2.GL_LINES);
                    y1 = 0.95f - i/10f ;
                    y2 = 0.95f - i/10f;
                    x1 = 0.028f+ j/17f;
                    x2 = 0.028f + (j+1)/17f;
                    gl.glVertex2f(x1, y1);
                    gl.glVertex2f(x2, y2);
                    gl.glEnd();
                }
                if(mazeAsString.charAt(index + 1) == '0'){
                    gl.glBegin(GL2.GL_LINES);
                    y1 = 0.95f - (i+1)/10f ;
                    y2 = 0.95f - i/10f;
                    x1 = 0.028f + (j+1)/17f;
                    x2 = 0.028f + (j+1)/17f;
                    gl.glVertex2f(x1, y1);
                    gl.glVertex2f(x2, y2);
                    gl.glEnd();
                }
                if(mazeAsString.charAt(index + 2) == '0'){
                    gl.glBegin(GL2.GL_LINES);
                    y1 = 0.95f - (i+1)/10f ;
                    y2 = 0.95f - (i+1)/10f;
                    x1 = 0.028f + j/17f;
                    x2 = 0.028f + (j+1)/17f;
                    gl.glVertex2f(x1, y1);
                    gl.glVertex2f(x2, y2);
                    gl.glEnd();
                }
                if(mazeAsString.charAt(index + 3) == '0'){
                    gl.glBegin(GL2.GL_LINES);
                    y1 = 0.95f - (i)/10f ;
                    y2 = 0.95f - (i+1)/10f;
                    x1 = 0.028f + (j)/17f;
                    x2 = 0.028f + (j)/17f;
                    gl.glVertex2f(x1, y1);
                    gl.glVertex2f(x2, y2);
                    gl.glEnd();
                }
            }
        }

        MyShapes.circleMidPoint(  posX,posY , radius,gl);
        gl.glPopMatrix();


    }

    public static void moveUp() {
        int cellX = (int)Math.floor((posX/1600.0-0.028)*17);
        int cellY = (int)Math.floor((posY/900.0-0.95)*-10);


        int index = getIndex(cellX , cellY);
        int newPosY = posY + 10;

        //check if it is out of the maze
        if(newPosY > 855 ){
            return;
        }

        if(mazeAsString.charAt(index) == '1'){
            posY = newPosY;
        }
        else {
            if(newPosY/900.0 > 0.95-cellY/10.0 -radius/900.0){
                return;
            }
            else {
                posY = newPosY;
            }
        }
    }

    public static void moveLeft() {
        int cellX = (int)Math.floor((posX/1600.0-0.028)*17);
        int cellY = (int)Math.floor((posY/900.0-0.95)*-10);
        int index = getIndex(cellX , cellY);
        int newPosX = posX - 10;
        if(mazeAsString.charAt(index+3) == '1'){
            posX = newPosX;
        }
        else {
            if(newPosX/1600.0 < 0.028+cellX/17.0 +radius/1600.0){
                return;
            }
            else {
                posX = newPosX;
            }
        }
    }

    public static void moveRight() {
        int cellX = (int)Math.floor((posX/1600.0-0.028)*17);
        int cellY = (int)Math.floor((posY/900.0-0.95)*-10);
        int index = getIndex(cellX , cellY);
        int newPosX = posX + 10;
        if(mazeAsString.charAt(index+1) == '1'){
            posX = newPosX;
        }
        else {
            if(newPosX/1600.0 > 0.028+(cellX+1)/17.0 -radius/1600.0){
                return;
            }
            else {
                posX = newPosX;
            }
        }
    }

    public static void moveDown() {
        int cellX = (int)Math.floor((posX/1600.0-0.028)*17);
        int cellY = (int)Math.floor((posY/900.0-0.95)*-10);
        //check if won

        int index = getIndex(cellX , cellY);
        int newPosY = posY - 10;
        if(mazeAsString.charAt(index+2) == '1'){
            posY = newPosY;
        }

        if(newPosY < 45){
            controller = 2;
            return;
        }

        else {
            if(newPosY/900.0 < 0.95-(cellY+1)/10.0 +radius/900.0){
                return;
            }
            else {
                posY = newPosY;
            }
        }
    }

    static int getIndex (int cellX , int cellY){
        int index = cellY*16*4 + cellX*4;

        if(index < 0){
            System.out.println("String out range :"+ cellX +" "+cellY);
        }
        return index;
    }

    private void win(GL2 gl) {
    }


    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

}
