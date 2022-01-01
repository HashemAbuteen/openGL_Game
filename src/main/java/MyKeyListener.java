import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

        int keyCode = event.getKeyCode();
        if(keyCode == 10){
            GlCanvas.controller = 1;
        }
        if(GlCanvas.controller == 1) {
            if(keyCode == 38) //up
            {
                GlCanvas.moveUp();
            }
            if(keyCode == 37) //left
            {
                GlCanvas.moveLeft();
            }
            if(keyCode == 39) //right
            {
                GlCanvas.moveRight();
            }
            if(keyCode == 40) //down
            {
                GlCanvas.moveDown();
            }
        }
    }
}