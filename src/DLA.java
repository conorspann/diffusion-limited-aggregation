

package src;

import com.jogamp.opengl.GL2;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Conor
 */
public class DLA {
    private DLAData dlaData;
    private Random rand;
    private Particle[] particles;
    private Particle seed;
    private int offsetX, offsetY, zoneWidth, zoneHeight, noStuckParticles;
    private double tick;
    public DLA(DLAData dlaData){
        this.dlaData = dlaData;
        rand = new Random();
        particles = new Particle[dlaData.getNoParticles()];
        this.offsetX = dlaData.getOffsetX();
        this.offsetY = dlaData.getOffsetY();
        this.zoneWidth = dlaData.getZoneWidth();
        this.zoneHeight = dlaData.getZoneHeight();
        noStuckParticles = 0;
        tick = 0.0;
        for(int i = 0; i < dlaData.getNoParticles(); i++){
            particles[i] = new Particle(getRandInt(offsetX, offsetX+zoneWidth), getRandInt(offsetY, offsetY+zoneHeight), 1, dlaData.getColourScheme(), dlaData.showUnStuck());
        }
        seed = new Particle(offsetX+(zoneWidth/2), offsetY+(zoneHeight/2), 1, dlaData.getColourScheme(), dlaData.showUnStuck());
        seed.stick(tick);
    }
    public void draw(GL2 gl2){
        drawBackground(gl2);
        seed.draw(gl2);
        checkCollision(seed);
        for(int i = 0; i < particles.length; i++){
            adjustToInZone(particles[i]);
            particles[i].randWalk();
            if(particles[i].isStuck()){
                checkCollision(particles[i]);
            }
            particles[i].draw(gl2);
        }
    }
    private boolean hasFinished(){
        for(int i = 0; i < particles.length; i++){
            if(!particles[i].isStuck()){
                return false;
            }
        }
        return true;
    }
    private void drawBackground(GL2 gl2){
        gl2.glBegin(GL2.GL_POLYGON);
        gl2.glColor3d(0.3, 0.3, 0.3);
        gl2.glVertex2f(offsetX, offsetY);
        gl2.glVertex2f(offsetX+ zoneWidth, offsetY);
        gl2.glVertex2f(offsetX + zoneWidth, offsetY+zoneHeight);
        gl2.glVertex2f(offsetX, offsetY+zoneHeight);
        gl2.glEnd();
    }
    private void checkCollision(Particle stuckPixel){
        boolean collided = false;
        switch(dlaData.getConnectType()){
            case CONNECT_4:
                collided = checkCollision4C(stuckPixel);
                break;
            case CONNECT_8:
                collided = checkCollision8C(stuckPixel);
                break;
        }
        if(collided){
            noStuckParticles++;
            if(hasFinished()){
                JOptionPane.showMessageDialog(null, "Finished!");
            }
        }
    }
    private boolean checkCollision4C(Particle stuckPixel){
        for(int i = 0; i < particles.length; i++){
            if(!particles[i].isStuck()){
                if(     (stuckPixel.getX() == particles[i].getX()+particles[i].getSize() && stuckPixel.getY() == particles[i].getY()) ||
                        (stuckPixel.getX() == particles[i].getX()-particles[i].getSize() && stuckPixel.getY() == particles[i].getY()) ||
                        (stuckPixel.getY() == particles[i].getY()+particles[i].getSize() && stuckPixel.getX() == particles[i].getX()) ||
                        (stuckPixel.getY() == particles[i].getY()-particles[i].getSize() && stuckPixel.getX() == particles[i].getX()) 
                ){
                    particles[i].stick(tick);
                    tick();
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkCollision8C(Particle stuckPixel){
        for(int i = 0; i < particles.length; i++){
            if(!particles[i].isStuck()){
                if(     (stuckPixel.getX() == particles[i].getX()+1 && stuckPixel.getY() == particles[i].getY()) ||
                        (stuckPixel.getX() == particles[i].getX()-1 && stuckPixel.getY() == particles[i].getY()) ||
                        (stuckPixel.getY() == particles[i].getY()+1 && stuckPixel.getX() == particles[i].getX()) ||
                        (stuckPixel.getY() == particles[i].getY()-1 && stuckPixel.getX() == particles[i].getX()) ||
                        
                        (stuckPixel.getX() == particles[i].getX()+1 && stuckPixel.getY() == particles[i].getY()+1) ||
                        (stuckPixel.getX() == particles[i].getX()-1 && stuckPixel.getY() == particles[i].getY()+1) ||
                        (stuckPixel.getX() == particles[i].getX()+1 && stuckPixel.getY() == particles[i].getY()-1) ||
                        (stuckPixel.getX() == particles[i].getX()-1 && stuckPixel.getY() == particles[i].getY()-1)
                ){
                    particles[i].stick(tick);
                    tick();
                    return true;
                }
            }
        }
        return false;
    }
    private void adjustToInZone(Particle particle){
        int x2 = offsetX + zoneWidth;
        int y2 = offsetY + zoneHeight;
        if(particle.getX() >= x2){
            particle.setX(particle.getX() - ( 1 + (particle.getX() - x2)      )   );
        }else if(particle.getX() <= offsetX){
            particle.setX(particle.getX() + ( 1 + (offsetX - particle.getX()) )   );
        }
        if(particle.getY() >= y2){
            particle.setY(particle.getY() - ( 1 + (particle.getY() - y2))         );
        }else if(particle.getY() <= offsetY){
            particle.setY(particle.getY() + ( 1 + (offsetY - particle.getY()) )    );
        }
    }
    private int getRandInt(int min, int max){
        return rand.nextInt((max - min) + 1) + min;
    }
    private void tick(){
        if(tick < 1.0){
            tick += 0.001;
        }else{
            tick = 0.0;
        }
    }
}
