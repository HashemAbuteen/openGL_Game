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
        brezenhamLine(xCenter + x, yCenter + y , xCenter - x, yCenter - y , gl);
        brezenhamLine(xCenter - x, yCenter + y , xCenter + x, yCenter - y , gl);
        brezenhamLine(xCenter + y, yCenter + x , xCenter - y, yCenter - x , gl);
        brezenhamLine(xCenter - y, yCenter + x , xCenter + y, yCenter - x , gl);
    }
    static void putpixel(int x, int y, GL2 gl){
        gl.glBegin(GL_POINTS);
        gl.glVertex2f(x/1600f , y/900f );
        gl.glEnd();
    }


    static void brezenhamLine (int xa , int ya , int xb, int yb, GL2 gl){
        int x;
        int y;

        boolean isAcsending;

        int dx = Math.abs(xa - xb);
        int dy = Math.abs(ya - yb);

        int xEnd;

        if (dx >= dy) {
            int p = 2 * dy - dx;
            int twoDy = 2 * dy;
            int twoDyDx = 2 * (dy - dx);

            if (xa > xb) {
                x = xb;
                y = yb;
                xEnd = xa;
                isAcsending = yb < ya;
            } else {

                x = xa;
                y = ya;
                xEnd = xb;
                isAcsending = yb > ya;
            }

            putpixel(x, y, gl);

            while (x < xEnd) {
                x = x + 1;
                if (p < 0) {
                    p = p + twoDy;
                } else {
                    if (isAcsending)
                        y = y + 1;
                    else
                        y--;
                    p = p + twoDyDx;
                }
                putpixel(x, y, gl);

            }

        } else {
            int twoDxDy = 2 * (dx - dy);
            int twoDx = 2 * dx;
            int p = 2 * dx - dy;
            int yEnd;

            if (ya > yb) {
                x = xb;
                y = yb;
                yEnd = ya;
                isAcsending = xb < xa;
            } else {

                x = xa;
                y = ya;
                yEnd = yb;
                isAcsending = xb > xa;
            }

            putpixel(x, y, gl);

            while (y < yEnd) {
                y = y + 1;
                if (p < 0) {
                    p = p + twoDx;
                } else {
                    if (isAcsending)
                        x = x + 1;
                    else
                        x--;
                    p = p + twoDxDy;
                }
                putpixel(x, y, gl);

            }
        }

    }


    }

