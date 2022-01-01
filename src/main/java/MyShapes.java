import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

import static com.jogamp.opengl.GL.GL_POINTS;

public class MyShapes   {

    static void circleMidPoint ( int xCenter , int yCenter , int radius , GL2 gl){
        int x = 0 ;
        int y = radius;
        int p = 1 - radius;
        circlePlotPoint (xCenter , yCenter , x , y ,gl );
        while (x < y ){
            x = x+1;
            if ( p< 0){
                p = p+2 * x +1;
            }
            else {
                y =y-1;
                p = p + 2*(x-y) + 1;
            }
            circlePlotPoint (xCenter , yCenter , x , y , gl);
        }
    }
    static void circlePlotPoint(int xCenter , int yCenter , int x, int y , GL2 gl){
        putpixel ( xCenter + x, yCenter + y , gl );
        putpixel ( xCenter - x, yCenter + y , gl );
        putpixel ( xCenter + x, yCenter - y , gl );
        putpixel ( xCenter - x, yCenter - y , gl);
        putpixel ( xCenter + y, yCenter + x , gl);
        putpixel ( xCenter - y, yCenter + x , gl);
        putpixel ( xCenter + y, yCenter - x , gl);
        putpixel ( xCenter - y, yCenter - x , gl);
    }
    static void putpixel(int x, int y, GL2 gl){
        gl.glBegin(GL_POINTS);
        gl.glVertex2f(x/1600f , y/900f );
        gl.glEnd();
    }


    static void brezenhamLine (int x1 , int y1 , int x2, int y2, GL2 gl){
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        int p = 2 * dy - dx;
        int twoDy = 2*dy;
        int twoDyDx = 2 * (dy - dx);
        int x;
        int y;
        int xEnd;

        if(x1 > x2) {
            x = x2;
            y = y2;
            xEnd = x1;
        }
        else {
            x = x1;
            y = y1;
            xEnd = x2;
        }
        gl.glBegin(GL_POINTS);
        gl.glVertex2f(x/400f , y/400f );
        gl.glEnd();
        while (x < xEnd){
            x = x+1;
            if (p < 0) {
                p = p +twoDy ;
            }
            else {
                y = y +1;
                p = p +twoDyDx;
            }
            gl.glBegin(GL_POINTS);
            gl.glVertex2f(x/400f , y/400f );
            gl.glEnd();
        }
    }


    }

