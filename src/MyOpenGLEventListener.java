

package src;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLEventListener;

/**
 *
 * @author Conor
 */
public class MyOpenGLEventListener implements GLEventListener{
    
    private DLAData dlaData;
    private DLA dla;
    
    public MyOpenGLEventListener(DLAData dlaData){
        this.dlaData = dlaData;
        dla = new DLA(this.dlaData);
    }

    @Override
    public void init(GLAutoDrawable glad) {
        
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl2 = glad.getGL().getGL2();
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
        dla.draw(gl2);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        GL2 gl2 = glad.getGL().getGL2();
        int width = glad.getSurfaceWidth();
        int height = glad.getSurfaceHeight();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluOrtho2D(0.0f, width, 0.0f, height);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();
        gl2.glViewport(0, 0, width, height);
    }
    
}
