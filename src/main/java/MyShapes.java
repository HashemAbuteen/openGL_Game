import com.jogamp.opengl.*;

import java.awt.*;
import java.util.HashMap;

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
    static void circleMidPoint ( int xCenter , int yCenter , int radius ,Color color,HashMap<Key ,Color>pixelMap ){
        int x = 0 ;
        int y = radius;
        int p = 1 - radius;
        circlePlotPoint (xCenter , yCenter , x , y ,color,pixelMap );
        while (x < y ){
            x = x+1;
            if ( p< 0){
                p = p+2 * x +1;
            }
            else {
                y =y-1;
                p = p + 2*(x-y) + 1;
            }
            circlePlotPoint (xCenter , yCenter , x , y , color,pixelMap);
        }
    }
    static void circlePlotPoint(int xCenter , int yCenter , int x, int y ,Color color,HashMap<Key , Color> pixelMap){
        line(xCenter + x, yCenter + y , xCenter - x, yCenter - y ,color,pixelMap);
        line(xCenter - x, yCenter + y , xCenter + x, yCenter - y ,color, pixelMap);
        line(xCenter + y, yCenter + x , xCenter - y, yCenter - x ,color, pixelMap);
        line(xCenter - y, yCenter + x , xCenter + y, yCenter - x ,color, pixelMap);
    }
    static void putPixel(int x, int y, GL2 gl){
        gl.glBegin(GL_POINTS);
        gl.glVertex2i(x , y);
        gl.glEnd();
    }

    static void putPixel(int x, int y,Color color, HashMap<Key , Color> pixelMap){
        pixelMap.put(new Key(x,y) , color);
    }

    static void putPixel( HashMap<Key,Color> pixelMap, GL2 gl){
        for (Key key : pixelMap.keySet()) {
            putPixel( key.getX(), key.getY(),gl);
        }
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

            putPixel(x, y, gl);

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
                putPixel(x, y, gl);

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

            putPixel(x, y, gl);

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
                putPixel(x, y, gl);

            }
        }

    }


    public static void line(int xa, int ya, int xb, int yb,Color color, HashMap<Key, Color> pixelMap) {
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

            putPixel(x, y,color, pixelMap);

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
                putPixel(x, y,color, pixelMap);

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

            putPixel(x, y, color,pixelMap);

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
                putPixel(x, y, color,pixelMap);

            }
        }

    }

    public static void draw(HashMap<Key , Color> pixelMap , GL2 gl){
        for (Key key : pixelMap.keySet()) {
            Color color = pixelMap.get(key);
            gl.glColor3f(color.getRed()/256f,color.getGreen()/256f,color.getBlue()/256f);
            gl.glBegin(GL_POINTS);
            gl.glVertex2i(key.getX() , key.getY());
            gl.glEnd();
        }
    }
}

