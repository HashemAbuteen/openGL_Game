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
import java.util.HashMap;
import java.util.Scanner;

public class GlCanvas implements GLEventListener {

    private static GraphicsEnvironment graphicsEnvironment;
    public static  DisplayMode dm,dm_old;
    private static Dimension xGraphic;
    private static Point point = new Point(0,0);
    private  GLU glu = new GLU();
    private int textureId;

    public static int controller = 0;
    public static int posX = 720;
    public static int posY = 810;
    public static int radius = 10;
    static String mazeAsString = "";

    HashMap<Key , Color> pixelMap;
    HashMap<Key , Color> exitLight;




    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

        Scanner scanner;
        try {
            File maze = new File("src/main/resources/maze1.txt");
            scanner = new Scanner(maze);
            mazeAsString = scanner.next();
        } catch (FileNotFoundException e) {
            System.out.println("Maze file do not exist");
            e.printStackTrace();
        }

        pixelMap = new HashMap<>();

        for(int i=0;i<9 ; i++){
            for(int j = 0 ; j < 16 ; j++){
                //up
                int index = getIndex( j , i);
                int x1,x2,y1,y2;
                if(mazeAsString.charAt(index ) == '0'){
                    y1 = 855 - i*90 ;
                    y2 = 855 - i*90;
                    x1 = 45+ j*90;
                    x2 = 45 + (j+1)*90;
                    MyShapes.line(x1,y1,x2,y2,Color.WHITE,pixelMap);
                }
                if(mazeAsString.charAt(index + 1) == '0'){
                    y1 = 855 - (i+1)*90 ;
                    y2 = 855 - i*90;
                    x1 = 45 + (j+1)*90;
                    x2 = 45 + (j+1)*90;
                    MyShapes.line(x1,y1,x2,y2,Color.WHITE,pixelMap);

                }
                if(mazeAsString.charAt(index + 2) == '0'){
                    y1 = 855 - (i+1)*90 ;
                    y2 = 855 - (i+1)*90;
                    x1 = 45 + j*90;
                    x2 = 45 + (j+1)*90;
                    MyShapes.line(x1,y1,x2,y2,Color.WHITE,pixelMap);

                }
                if(mazeAsString.charAt(index + 3) == '0'){
                    y1 = 855 - (i)*90 ;
                    y2 = 855 - (i+1)*90;
                    x1 = 45 + (j)*90;
                    x2 = 45 + (j)*90;
                    MyShapes.line(x1,y1,x2,y2,Color.WHITE,pixelMap);

                }
            }
        }
        exitLight = Lighting.circularLight(810,1,50*radius,pixelMap);

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0f,0f,0f,0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureId);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_PROJECTION_MATRIX);
        gl.glLoadIdentity();
        gl.glOrtho(0, 1600, 0, 900, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
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
        gl.glVertex2i(0, 0);
        gl.glTexCoord2f(0f, 1f);
        gl.glVertex2i(0, 900);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex2i(1600, 900);
        gl.glTexCoord2f(1f, 0f);
        gl.glVertex2i(1600, 0);
        gl.glEnd();
        gl.glPopMatrix();
    }

    private void game(GL2 gl) {
        gl.glDisable(GL2.GL_TEXTURE_2D);






        HashMap<Key , Color> renderedPixelMap = Lighting.circularLight(posX,posY,50*radius,pixelMap);
        MyShapes.circleMidPoint(  posX,posY , radius,Color.WHITE,renderedPixelMap);
        MyShapes.draw(exitLight,gl);
        MyShapes.draw(renderedPixelMap,gl);

        gl.glPopMatrix();


    }

    public static void moveUp() {
        int cellX = (posX-45)/90;
        int cellY = (855-posY)/90;


        int index = getIndex(cellX , cellY);
        int newPosY = posY + 10;

        //check if it is out of the maze
        if(newPosY > 845 ){
            posY = 845;
            return;
        }

        if(mazeAsString.charAt(index) == '1'){
            posY = newPosY;
        }
        else {
            if((855-newPosY-10)/90 < cellY){
                posY = 855- cellY*90 -10;
                return;
            }
            else {
                posY = newPosY;
            }
        }
    }

    public static void moveLeft() {
        int cellX = (posX-45)/90;
        int cellY = (855-posY)/90;
        int index = getIndex(cellX , cellY);
        int newPosX = posX - 10;
        if(mazeAsString.charAt(index+3) == '1'){
            posX = newPosX;
        }
        else {
            if((newPosX-45-10)/90.0 < cellX ){
                posX = 45+10+cellX*90;
                return;
            }
            else {
                posX = newPosX;
            }
        }
    }

    public static void moveRight() {
        int cellX = (posX-45)/90;
        int cellY = (855-posY)/90;
        int index = getIndex(cellX , cellY);
        int newPosX = posX + 10;
        if(mazeAsString.charAt(index+1) == '1'){
            posX = newPosX;
        }
        else {
            if((newPosX-45+10)/90 > cellX){
                posX = 45-10+(cellX+1)*90;
                return;
            }
            else {
                posX = newPosX;
            }
        }
    }

    public static void moveDown() {
        int cellX = (posX-45)/90;
        int cellY = (855-posY)/90;

        int index = getIndex(cellX , cellY);
        int newPosY = posY - 10;
        if(mazeAsString.charAt(index+2) == '1'){
            posY = newPosY;
            //check if won
            if(newPosY < 45){
                controller = 2;
                return;
            }
        }
        else {
            if((855-newPosY+10)/90 > cellY){
                posY = 855- (cellY+1)*90 +10;
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
