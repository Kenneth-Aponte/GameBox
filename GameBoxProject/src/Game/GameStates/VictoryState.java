package Game.GameStates;

import Game.Galaga.Entities.EntityManager;
import Game.PacMan.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class VictoryState extends State {
	public EntityManager entityManager;
	public String Mode = "Menu";
	public int selectPlayers = 1;
	public int startCooldown = 60*7;//seven seconds for the music to finish

	public VictoryState(Handler handler){
		super(handler);
		refresh();
	}


	@Override
	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
			if(handler.getScoreManager().getPacmanCurrentScore() > handler.getScoreManager().getPacmanHighScore()) {
				handler.getScoreManager().setPacmanHighScore(handler.getScoreManager().getPacmanCurrentScore());
			}
			handler.getPacManState().level1 = true;
			handler.getPacManState().level2 = false;
			handler.getPacManState().refresh();
			if (handler.getPacManState().CustomPacman == true) {
				handler.setMap(MapBuilder.createMap(Images.map1, handler)); 
				handler.getPacManState().CustomPacman = false;
			}
			else {
				handler.getPacManState().Mode = "OG";
			}
			State.setState(handler.getMenuState());
			    
		}
	}


	@Override
	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
		g.setColor(Color.BLUE);
		g.drawString("You Won, Victory is Yours!", handler.getWidth()/2, handler.getHeight()/2);
		g.drawImage(Images.pakuMan,handler.getWidth()/(4)-16,handler.getHeight()/5,handler.getWidth()/3,handler.getHeight()/3,null);
	}


	@Override
	public void refresh() {

	}


	public void restart() {

		handler.getScoreManager().setPacmanCurrentScore(0);
		handler.getMusicHandler().changeMusic("nature.wav");

	}
}
