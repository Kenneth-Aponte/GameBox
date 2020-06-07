package Game.PacMan.entities.Statics;

import Game.PacMan.entities.BaseEntity;
import Main.Handler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BaseStatic extends BaseEntity {
	BufferedImage[] idleAnimList= new BufferedImage[2];
	public Random random = new Random();
	public boolean isFruit;
    public BaseStatic(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height, handler,sprite);
    }
    
    public void tick() {
    	
    }

    public void render(Graphics2D g2) {
    	 g2.drawImage(sprite, x, y, width, height, null);
    }
}
