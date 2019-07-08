

package src;

/**
 *
 * @author Conor
 */
public class DLAData {
    
    public enum Connect{
        CONNECT_4, CONNECT_8;
    }
    public enum Colour{
        RG, RB, GR, GB, BR, BG
    }
    
    private Connect connectType;
    private Colour colourScheme;
    private int offsetX, offsetY, zoneWidth, zoneHeight, noParticles;
    private boolean showUnStuck;
    
    public DLAData(){
        connectType = Connect.CONNECT_4;
        colourScheme = Colour.RG;
        offsetX = 260;
        offsetY = 200;
        zoneWidth = 100;
        zoneHeight = 100;
        noParticles = 1000;
        showUnStuck = true;
    }
    
    public void setConnectType(Connect connectType){
        this.connectType = connectType;
    }
    public void setColourScheme(Colour colourScheme){
        this.colourScheme = colourScheme;
    }
    public void setPosition(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    public void setSize(int zoneWidth, int zoneHeight){
       this.zoneWidth = zoneWidth;
       this.zoneHeight = zoneHeight;
    }
    public void setNoParticles(int noParticles){
        this.noParticles = noParticles;
    }
    public void setShowUnStuck(boolean showUnStuck){
        this.showUnStuck = showUnStuck;
    }
    
    public Connect getConnectType(){
        return connectType;
    }
    public Colour getColourScheme(){
        return colourScheme;
    }
    public int getOffsetX(){
        return offsetX;
    }
    public int getOffsetY(){
        return offsetY;
    }
    public int getZoneWidth(){
        return zoneWidth;
    }
    public int getZoneHeight(){
        return zoneHeight;
    }
    public int getNoParticles(){
       return noParticles; 
    }
    public boolean showUnStuck(){
        return showUnStuck;
    }
    
}
