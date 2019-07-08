

package src;

import com.jogamp.opengl.GL2;
import java.util.Random;

/**
 *
 * @author Conor
 */
public class Particle {
    private Random rand;
    private int x, y, size;
    private double r, g, b;
    private boolean stuck;
    private DLAData.Colour colourScheme;
    private boolean showUnStuck;
    public Particle(int x, int y, int size, DLAData.Colour colourScheme, boolean showUnStuck){
        rand = new Random();
        this.x = x;
        this.y = y;
        this.size = size;
        this.colourScheme = colourScheme;
        switch(colourScheme){
            case RG:
            case RB:
                r = 1.0;
                g = 0.0;
                b = 0.0;
                break;
            case GR:
            case GB:
                r = 0.0;
                g = 1.0;
                b = 0.0;
                break;
            case BR:
            case BG:
                r = 0.0;
                g = 0.0;
                b = 1.0;
                break;
        }
        stuck = false;
        this.showUnStuck = showUnStuck;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void stick(double tick){
        stuck = true;
        switch(colourScheme){
            case RG:
            case BG:
                g = tick;
                break;
            case RB:
            case GB:
                b = tick;
                break;
            case GR:
            case BR:
                r = tick;
                break;
        }
    }
    public void randWalk(){
        if(!stuck){
            double d = rand.nextDouble();
            if(d >= 0.75){
                x++;
            }else if(d >= 0.5){
                x--;
            }else if(d >= 0.25){
                y++;
            }else{
                y--;
            }
        }
    }
    public void draw(GL2 gl2){
        if(stuck){
            gl2.glBegin(GL2.GL_POLYGON);
            gl2.glColor3d(r, g, b);
            gl2.glVertex2f(x, y);
            gl2.glVertex2f(x+ size, y);
            gl2.glVertex2f(x + size, y+size);
            gl2.glVertex2f(x, y+size);
            gl2.glEnd();
        } else if(showUnStuck){
            gl2.glBegin(GL2.GL_POLYGON);
            gl2.glColor3d(1, 1, 1);
            gl2.glVertex2f(x, y);
            gl2.glVertex2f(x+ size, y);
            gl2.glVertex2f(x + size, y+size);
            gl2.glVertex2f(x, y+size);
            gl2.glEnd();
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSize(){
        return size;
    }
    public boolean isStuck(){
        return stuck;
    }
}
