package Game.PacMan.entities.Statics;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class BigDot extends BaseStatic{

	public Animation blink;
	public int x,y,width,height;
	
	public BigDot(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler,Images.pacmanDots[0]);
		BufferedImage[] blinkAnimList= new BufferedImage[2];
		blinkAnimList[0] = Images.pacmanDots[0];//animation for blinking
		blink = new Animation(128,blinkAnimList);//  ||      ||     ||
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void tick(){
		super.tick();
		blink.tick();//ticks the blinking animation
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(blink.getCurrentFrame(), x, y, height, width, null);//renders the blinking animation
	}
}


