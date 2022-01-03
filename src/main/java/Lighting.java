import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Lighting {


    public static HashMap<Key, Color> circularLight(int posX, int posY, int radius, HashMap<Key, Color> pixelMap) {
        HashMap<Key, Color> newPixelMap = new HashMap<>();
        int x = 0 ;
        int y = radius;
        int p = 1 - radius;
        circleCastRay (posX , posY , x , y ,pixelMap,newPixelMap );
        while (x < y ){
            x = x+1;
            if ( p< 0){
                p = p+2 * x +1;
            }
            else {
                y =y-1;
                p = p + 2*(x-y) + 1;
            }
            circleCastRay (posX , posY , x , y ,pixelMap , newPixelMap);
        }
        return newPixelMap;
    }


    private static void circleCastRay(int centerX, int centerY, int x, int y, HashMap<Key, Color> pixelMap, HashMap<Key, Color> newPixelMap) {
        lightRay(centerX,centerY,centerX+x,centerY+y,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX-x,centerY+y,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX+x,centerY-y,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX-x,centerY-y,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX+y,centerY+x,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX-y,centerY+x,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX+y,centerY-x,pixelMap,newPixelMap);
        lightRay(centerX,centerY,centerX-y,centerY-x,pixelMap,newPixelMap);
    }

    private static void lightRay(int x0, int y0, int x1, int y1,HashMap<Key, Color> pixelMap, HashMap<Key, Color> newPixelMap) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        // calculate steps required for generating pixels
        int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);

        // calculate increment in x & y for each steps
        float Xinc = dx / (float) steps;
        float Yinc = dy / (float) steps;

        // Put pixel for each step
        float X = x0;
        float Y = y0;

        float intensity = 0.7f;
        for (int i = 0; i <= steps; i++)
        {
            light(Math.round(X),Math.round(Y),intensity,pixelMap,newPixelMap);
            if(pixelMap.containsKey(new Key(Math.round(X),Math.round(Y))) &&pixelMap.get(new Key(Math.round(X),Math.round(Y))).equals(Color.WHITE)){
                break;
            }
            X += Xinc;           // increment in x at each step
            Y += Yinc;           // increment in y at each step
            intensity -= 0.004;
            if(intensity<0){
                break;
            }

        }
    }

    private static void light(int x, int y, float intensity, HashMap<Key, Color> pixelMap, HashMap<Key, Color> newPixelMap) {
        if(!pixelMap.containsKey(new Key(x,y))){
            Color color = new Color(intensity,intensity,intensity);
            newPixelMap.put(new Key(x,y),color);
        }
        else {
            newPixelMap.put(new Key(x,y),pixelMap.get(new Key(x,y)));
        }

    }
}
