package Game.PacMan.entities.Dynamics;

import Game.PacMan.entities.BaseEntity;
import Game.PacMan.entities.Statics.BaseStatic;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BaseDynamic extends BaseEntity {

    protected String direction = "Right";
    public boolean ded = false;
    protected int dedCounter=0;
    protected double velX=1,velY = 1;
    public static int health = 3;
    public Rectangle bounds;
    public Animation pacManDeath;
    public int waitTime = 0;

    public BaseDynamic(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height,handler,sprite);
        this.bounds = new Rectangle(x,y,width,height);
        pacManDeath = new Animation(128,Images.pacManDeath);//pacman Death animation
    }
    
    
    public void tick(){

    }
    
    
    protected void checkHorizontal() {
        boolean toRight = direction.equals("Right");

        Rectangle mushroomBounds = toRight ? getRightBounds() : getLeftBounds();

        for (BaseStatic brick : handler.getMap().getBlocksOnMap()) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (mushroomBounds.intersects(brickBounds)) {
                if(toRight) {
                    direction = "Left";
                    setX(brick.getX() - getDimension().width);
                }
                else{
                    direction = "Right";
                    setX(brick.getX() + brick.getDimension().width);
                }
            }
        }

    }


    protected void move(){

        if(direction.equals("Right")){
            x+=velX;
        }else{
            x-=velX;
        }
    }

    public void render(Graphics2D g2) {
    	
    }
    
    public void kill(){

    }


}
