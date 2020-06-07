package Game.GameStates;

import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Dynamics.Skeletor;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PacManState extends State {
	public String Mode = "Intro",gameSelection = "PacMan";
	public int startCooldown = 60*4;//seven seconds for the music to finish
	public int dotsConsumed = 0, gameSelPos = 1;
	public boolean level1 = true, level2 = false,mapCreated = false;
	public Animation pacmanMapAnimation1, pacmanMapAnimation2;
	public boolean CustomPacman = false;
	
	
	public PacManState(Handler handler){
		super(handler);
		handler.setMap(MapBuilder.createMap(Images.map1, handler));
		BufferedImage[] animation1= new BufferedImage[2];
		animation1[0] = Images.pacmanGameSelection[0];
		pacmanMapAnimation1 = new Animation(100,animation1);		
		BufferedImage[] animation2= new BufferedImage[2];
		animation2[0] = Images.pacmanGameSelection[1];
		pacmanMapAnimation2 = new Animation(100,animation2);
	}


	@Override
	public void tick() {
		if (Mode.equals("Stage")){
//------------------------LEVEL SELECTION-------------------------------------------------------------------------------------------
			if(gameSelection.equals("PacMan")) {
				
				if (dotsConsumed >= MapBuilder.dotCounter + MapBuilder.fruitCounter || handler.getPacman().ghostsEaten == 4 ) {
					if (level1 == true) {
						handler.getPacman().ghostsEaten++;
						startCooldown = 60*4;
						Mode = "levelTransition";
						level1 = false;
						MapBuilder.dotCounter = 0;
						MapBuilder.fruitCounter = 0;
						dotsConsumed = 0;
						handler.setMap(MapBuilder.createMap(Images.map2, handler));    				
					}
					else if (level2 == false) {
						startCooldown = 60*4;
						Mode = "levelTransition";
						level2 = true;
						MapBuilder.dotCounter = 0;
						MapBuilder.fruitCounter = 0;
						dotsConsumed = 0;
						handler.setMap(MapBuilder.createMap(Images.map3, handler));
					}
					else if (level2 == true && level1 == false){
						MapBuilder.dotCounter = 0;
						MapBuilder.fruitCounter = 0;
						dotsConsumed = 0;
						Mode = "VictoryState";
						handler.changeState(handler.getVictoryState());
						State.setState(handler.getVictoryState());
					}
				}
				else {
					CustomPacman = true;
				}
			}
//----------------------------------------------------------------------------------------------------------------------------------
			if(gameSelection.equals("OG") && !mapCreated){
				MapBuilder.pixelMultiplier = 36;
				PacMan.speed = 2;
				handler.setMap(MapBuilder.createMap(Images.OriginalMap, handler));
				mapCreated = true;
					
			}
			
			if(gameSelection.equals("OG")){
				if (dotsConsumed >= 209) {
					MapBuilder.dotCounter = 0;
					MapBuilder.fruitCounter = 0;
					dotsConsumed = 0;
					Mode = "VictoryState";
					handler.changeState(handler.getVictoryState());
					State.setState(handler.getVictoryState());
				}
			
			}
			
			if (startCooldown<=0) {
				for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
					entity.tick();
				}
				MapBuilder.getSpawner().tick();//ticks the ghostSpawner
				ArrayList<BaseStatic> toREmove = new ArrayList<>();
				for (BaseStatic blocks: handler.getMap().getBlocksOnMap()){
					if (blocks instanceof Dot){
						if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
							toREmove.add(blocks);
							
							//gives score in terms of entity eaten
							if(((Dot) blocks).isFruit) {
								dotsConsumed++;
								handler.getScoreManager().addPacmanCurrentScore(120);
								handler.getMusicHandler().playEffect("pacman_eatfruit.wav");
							}
							else {
								dotsConsumed++;
								handler.getScoreManager().addPacmanCurrentScore(10);
								handler.getMusicHandler().playEffect("pacman_chomp.wav");
							}

						}
					}else if (blocks instanceof BigDot){
						if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_chomp.wav");
							toREmove.add(blocks);
							dotsConsumed++;
							handler.getScoreManager().addPacmanCurrentScore(100);
							//if its not already on Edible state it will set it to edible state
							if(!Ghost.state.equals("Edible")){
								Ghost.state = "Edible";
								Skeletor.state = "Edible";
								handler.getMusicHandler().startMusic("pacman_intermission.wav");
							}
						}
					}
				}
				for (BaseStatic removing: toREmove){
					handler.getMap().getBlocksOnMap().remove(removing);
				}
			}else{
				startCooldown--;
			}
		}else if (Mode.equals("Menu")){
			//-----------------GAME SELECTION EITHER ORIGINAL PAC MAN OR OUR VERSION--------------------------------------------------------
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && gameSelPos != 1) {
				gameSelPos = 1;
				gameSelection = "PacMan";
			}
			else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && gameSelPos != 2 ){
				gameSelPos = 2;
				gameSelection = "OG";
			}			
			if(gameSelection.equals("PacMan")) {
				pacmanMapAnimation1.tick();
			}
			else {
				pacmanMapAnimation2.tick();
			}
			
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				Mode = "Stage";
				handler.getMusicHandler().playEffect("pacman_beginning.wav");
			}
			//------------------------------------------------------------------------------------------------------------------------------
			else if (Mode.equals("GameOver")){
				if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) {
					Mode = "Stage";
				}
			}
		}else{
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				Mode = "Menu";
			}
		}



	}

	@Override
	public void render(Graphics g) {
		if (Mode.equals("levelTransition")){
			g.drawString("Level up!", handler.getWidth()/2, handler.getHeight()/2);
			Mode = "Stage";
			handler.getMusicHandler().playEffect("pacman_beginning.wav");
		}  
		if (Mode.equals("Stage")){
			//Draws Pac-Man health meter
			for (int i = 0; i< PacMan.health;i++) {
				g.drawImage(Images.pacmanRight[0], (handler.getWidth() - handler.getWidth() / 3 + handler.getWidth() / 48) + ((handler.getPacman().getWidth()*6)*i), handler.getHeight()-handler.getHeight()/4, handler.getWidth() / 18, handler.getHeight() / 18, null);
			}
			
			//draws the image on the right of the screen if its not the original pac man
			if(!gameSelection.equals("OG")) {
				g.drawImage(Images.pakuMan,(handler.getWidth()/2)*5/4,handler.getHeight()/4,handler.getWidth()/3,handler.getHeight()/3,null);
			}
			Graphics2D g2 = (Graphics2D) g.create();
			handler.getMap().drawMap(g2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
			g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(),(handler.getWidth()/2) + handler.getWidth()/6, 25);
			g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),(handler.getWidth()/2) + handler.getWidth()/6, 75);
		}else if (Mode.equals("Menu")){
			g.drawImage(Images.start,handler.getWidth()/4,0,handler.getWidth()/2,handler.getHeight(),null);
			if(gameSelection.equals("PacMan")) {
				g.drawImage(pacmanMapAnimation1.getCurrentFrame(),handler.getWidth()/4+ handler.getWidth()/16,handler.getHeight()/10,handler.getWidth()/6,handler.getWidth()/6, null);
				g.drawImage(Images.pacmanGameSelection[1],handler.getWidth()/2+ handler.getWidth()/16,handler.getHeight()/10,handler.getWidth()/6,handler.getWidth()/6, null);
			}
			else {
				g.drawImage(pacmanMapAnimation2.getCurrentFrame(),handler.getWidth()/2+ handler.getWidth()/16,handler.getHeight()/10,handler.getWidth()/6,handler.getWidth()/6, null);
				g.drawImage(Images.pacmanGameSelection[0],handler.getWidth()/4 + handler.getWidth()/16,handler.getHeight()/10,handler.getWidth()/6,handler.getWidth()/6, null);
			}
		}else{
			g.drawImage(Images.intro,handler.getWidth()/4,0,handler.getWidth()/2,handler.getHeight(),null);

		}
	}

	@Override
	public void refresh() {
		mapCreated = false;
		Mode = "Intro";
		startCooldown = 60*4;
		MapBuilder.pixelMultiplier = 18;
		PacMan.health = 3;
		PacMan.speed = 1;
	}
}
