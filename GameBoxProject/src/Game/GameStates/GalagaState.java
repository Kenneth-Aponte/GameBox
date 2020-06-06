package Game.GameStates;

import Game.Galaga.Entities.EnemyBee;
import Game.Galaga.Entities.myEnemy;
import Game.Galaga.Entities.EntityManager;
import Game.Galaga.Entities.PlayerShip;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GalagaState extends State {

    public EntityManager entityManager;
    public String Mode = "Menu";
    private Animation titleAnimation;
    public int selectPlayers = 1;
    public int startCooldown = 60*7;//seven seconds for the music to finish
    Color LIGHT_BLUE = new Color(100,255,255,255);//new Color added rgba
    public boolean finishedSpawn = false;
    public int counter = 0;
    public int col, row;

    public GalagaState(Handler handler){
        super(handler);
        refresh();
        entityManager = new EntityManager(new PlayerShip(handler.getWidth()/2-64,handler.getHeight()- handler.getHeight()/7,62,62,Images.galagaPlayer[0],handler));
        titleAnimation = new Animation(256,Images.galagaLogo);
        
//------this only runs at the beginning of the game(Enemy Formation)---------------------------------------      
        if(! finishedSpawn) {
        	for(counter = 0;counter <= 7; counter++) {
            	entityManager.entities.add(new EnemyBee(0,0,32,32,handler,3,counter));
            	entityManager.entities.add(new EnemyBee(0,0,32,32,handler,4,counter));
            	PlayerShip.BeeArray[0][counter] = 1;
            	PlayerShip.BeeArray[1][counter] = 1;
            	PlayerShip.spawnCountBee+=2;
        	}
        	for(counter = 0;counter <= 3; counter++) {
            	entityManager.entities.add(new myEnemy(0,0,32,32,handler,1,counter+2));
            	entityManager.entities.add(new myEnemy(0,0,32,32,handler,2,counter+2));
            	PlayerShip.myEnemyArray[0][counter] = 1;
            	PlayerShip.myEnemyArray[1][counter] = 1;
            	PlayerShip.spawnCountMyEnemy+=2;
        	}
        finishedSpawn = true;//this is the code for the starting formation, it will then take over in playerShip
        }
    }//---------------------------------------------------------------------------------------------------


    
    @Override
    public void tick() {
//-----------this code was added to implement Pause State-------------------------------------------------
    	if(handler.getKeyManager().pausebutt) {
    		State.setState(handler.getPauseState());
    		handler.getMusicHandler().playEffect("Galaga.wav");
    	}
//--------------------------------------------------------------------------------------------------------    	
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
                entityManager.tick();
            }else{
                startCooldown--;
            }
        }else{
            titleAnimation.tick();
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selectPlayers=1;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selectPlayers=2;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Stage";
                handler.getMusicHandler().playEffect("Galaga.wav");
            }
        }
    }

    
    @Override
    public void render(Graphics g) {
        g.setColor(LIGHT_BLUE);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(handler.getWidth()/4,0,handler.getWidth()/2,handler.getHeight());
        Random random = new Random(System.nanoTime());

        for (int j = 1;j < random.nextInt(15)+60;j++) {
            switch (random.nextInt(6)) {//not including 6
                case 0:
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.BLUE);
                    break;
                case 2:
                    g.setColor(Color.YELLOW);
                    break;
                case 3:
                    g.setColor(Color.GREEN);
                    break;                    
//--------------added case 4 and 5------------------------------------------------------------------------
                case 4:
                    g.setColor(Color.WHITE);
                    break;
                case 5:
                    g.setColor(Color.MAGENTA);
//--------------------------------------------------------------------------------------------------------
            
            }
            int randX = random.nextInt(handler.getWidth() - handler.getWidth() / 2) + handler.getWidth() / 4;
            int randY = random.nextInt(handler.getHeight());
            g.fillRect(randX, randY, 2, 2);

        }
        if (Mode.equals("Stage")) {
            g.setColor(Color.MAGENTA);//changed to MAGENTA
            g.setFont(new Font("TimesRoman", Font.PLAIN, 62));
            g.drawString("HIGH",handler.getWidth()-handler.getWidth()/4,handler.getHeight()/16);
            g.drawString("SCORE",handler.getWidth()-handler.getWidth()/4+handler.getWidth()/48,handler.getHeight()/8);
            g.setColor(Color.MAGENTA);
            
//----------Added the Score Manager------------------------------------------------------------------------            
            if(handler.getScoreManager().getGalagaCurrentScore() > handler.getScoreManager().getGalagaHighScore()) {//score is higher than the High Score
            	handler.getScoreManager().setGalagaHighScore(handler.getScoreManager().getGalagaCurrentScore());
            	g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()-handler.getWidth()/4+handler.getWidth()/48,handler.getHeight()/5);
            }
            else {
            	g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()-handler.getWidth()/4+handler.getWidth()/48,handler.getHeight()/5);
            }
//--------------------------------------------------------------------------------------------------------
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("CURRENT - SCORE:  " + String.valueOf(handler.getScoreManager().getGalagaCurrentScore()), handler.getWidth()/4 + handler.getWidth()/64, handler.getHeight()/32);
            for (int i = 0; i< entityManager.playerShip.getHealth();i++) {
                g.drawImage(Images.galagaPlayer[0], (handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48) + ((entityManager.playerShip.width*2)*i), handler.getHeight()-handler.getHeight()/4, handler.getWidth() / 18, handler.getHeight() / 18, null);
            }
            g.setFont(new Font("TimesRoman", Font.PLAIN, 62));
            if (startCooldown<=0) {
                entityManager.render(g);
            }else{
                g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
                g.setColor(Color.MAGENTA);
                g.drawString("Start",handler.getWidth()/2-handler.getWidth()/18,handler.getHeight()/2);
            }
        }else{

            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));

            g.setColor(Color.MAGENTA);
            g.drawString("HIGH-SCORE:",handler.getWidth()/2-handler.getWidth()/18,32);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()/2-32,64);

            g.drawImage(titleAnimation.getCurrentFrame(),handler.getWidth()/2-(handler.getWidth()/12),handler.getHeight()/2-handler.getHeight()/3,handler.getWidth()/6,handler.getHeight()/7,null);

            g.drawImage(Images.galagaCopyright,handler.getWidth()/2-(handler.getWidth()/8),handler.getHeight()/2 + handler.getHeight()/3,handler.getWidth()/4,handler.getHeight()/8,null);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            g.drawString("1   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2);
            g.drawString("2   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2+handler.getHeight()/12);
            if (selectPlayers == 1){
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2-handler.getHeight()/32,32,32,null);
            }else{
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2+handler.getHeight()/18,32,32,null);
            }


        }
    }

    @Override
    public void refresh() {
    }
}
