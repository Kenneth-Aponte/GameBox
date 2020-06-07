package Game.GameStates;

import Game.Galaga.Entities.EntityManager;
import Game.PacMan.World.MapBuilder;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GameOverState extends State {
	public EntityManager entityManager;
	public String Mode = "Menu";
	private Animation rightAnim,redGhostAnim,pinkGhostAnim,blueGhostAnim,brownGhostAnim, message;
	public int selectPlayers = 1,entitySize = handler.getWidth()/10,caser,counter = 0;;
	public int startCooldown = 60*7;//seven seconds for the music to finish
	Random rand = new Random();
	boolean runOnce = false;
	public GameOverState(Handler handler){
		super(handler);
		rightAnim = new Animation(128,Images.pacmanRight);
		caser = rand.nextInt(4);//the case is chosen when constructed
		BufferedImage[] red = new BufferedImage[4];
		red[0] = Images.ghostDown1[0];
		red[1] = Images.ghostLeft1[0];
		red[2] = Images.ghostUp1[0];
		red[3] = Images.ghostRight1[0];
		redGhostAnim = new Animation(200,red);
		
		BufferedImage[] pink = new BufferedImage[4];
		pink[0] = Images.ghostDown1[1];
		pink[1] = Images.ghostLeft1[1];
		pink[2] = Images.ghostUp1[1];
		pink[3] = Images.ghostRight1[1];
		pinkGhostAnim = new Animation(200,pink);
		
		BufferedImage[] blue = new BufferedImage[4];
		blue[0] = Images.ghostDown1[2];
		blue[1] = Images.ghostLeft1[2];
		blue[2] = Images.ghostUp1[2];
		blue[3] = Images.ghostRight1[2];
		blueGhostAnim = new Animation(200,blue);
		
		BufferedImage[] brown = new BufferedImage[4];
		brown[0] = Images.ghostDown1[3];
		brown[1] = Images.ghostLeft1[3];
		brown[2] = Images.ghostUp1[3];
		brown[3] = Images.ghostRight1[3];
		brownGhostAnim = new Animation(200,brown);
		
		String[] s = new String[2];
		s[0] = "Press Enter to Restart the Game...";
		s[1] = "";
		message = new Animation(200,s);//made the animation work with String Arrays
		
	}


	@Override
	public void tick() {
		rightAnim.tick();
		redGhostAnim.tick();
		pinkGhostAnim.tick();
		blueGhostAnim.tick();
		brownGhostAnim.tick();
		message.tick2();
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){//resets the map and starts the game from scratch
			handler.getPacManState().refresh();
			State.setState(handler.getMenuState());
			runOnce = false;
			handler.setMap(MapBuilder.createMap(Images.map1, handler));
		}    
	}


	@Override
	public void render(Graphics g) { 	    
		g.drawImage(Images.gameOver,0,0,handler.getWidth(),handler.getHeight(),null);
		g.setColor(Color.black);
		g.fillRect(0,0,handler.getWidth(),handler.getHeight()/3 + handler.getHeight()/8);
		g.drawImage(rightAnim.getCurrentFrame(), handler.getWidth()/4, handler.getHeight()/2-handler.getHeight()/4, entitySize, entitySize, null);
		g.drawImage(redGhostAnim.getCurrentFrame(),handler.getWidth()/4+entitySize+entitySize/2,handler.getHeight()/2-handler.getHeight()/4,entitySize,entitySize, null);
		g.drawImage(pinkGhostAnim.getCurrentFrame(),handler.getWidth()/4+(entitySize*2)+entitySize/2,handler.getHeight()/2-handler.getHeight()/4,entitySize,entitySize, null);
		g.drawImage(blueGhostAnim.getCurrentFrame(),handler.getWidth()/4+(entitySize*3)+entitySize/2,handler.getHeight()/2-handler.getHeight()/4,entitySize,entitySize, null);
		g.drawImage(brownGhostAnim.getCurrentFrame(),handler.getWidth()/4+(entitySize*4)+entitySize/2,handler.getHeight()/2-handler.getHeight()/4,entitySize,entitySize, null);

		
		g.setFont(new Font("TimesRoman", Font.PLAIN, entitySize/4));
		g.setColor(Color.white);
		g.drawString(message.getCurrentString(),handler.getWidth()/3 , handler.getHeight()-handler.getHeight()/4);

		g.setColor(Color.RED);
		switch (caser) {//switches between the message displayed on the game over screen

		case 0:
			g.drawString("Waka waka waka, waka waka. Waka waka waka <Waka Waka! Waka...>", handler.getWidth()/6, handler.getHeight()/5);
			break;

		case 1:
			g.drawString("At least you do not have to pay a quarter.", handler.getWidth()/4, handler.getHeight()/5);
			break;

		case 2:
			g.drawString("Pac-Man... It's time to talk about your pill addiction.", handler.getWidth()/4, handler.getHeight()/5);
			break;

		case 3:
			g.drawString("Ms.Pac-Man is dissapointed in you", handler.getWidth()/3, handler.getHeight()/5);
			break;
		}
	}


	@Override
	public void refresh() {
	}

	
	public void restart() {
		handler.getScoreManager().setPacmanCurrentScore(0);
		handler.getMusicHandler().changeMusic("nature.wav");
	}
}
