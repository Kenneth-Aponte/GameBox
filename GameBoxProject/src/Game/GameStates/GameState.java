package Game.GameStates;


import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Main.Handler;
import Resources.Images;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GameState extends State {
	public int selector = 1;
	public BufferedImage currentGame,lGame,rGame;

    public GameState(Handler handler){
        super(handler);
        currentGame = Images.games[0];
    }


    @Override
    public void tick() {
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
    		switch(selector) {
    		case 0:
    			System.out.println("game not available");
    			break;
    		case 1:
    			System.out.println("game not available");
    			break;
    		case 2:
    			System.out.println("game not available");
    			break;
    		case 3:
    			System.out.println("game not available");
    			break;
    		case 4:
    			handler.changeState(handler.getPacManState());
    			handler.getMusicHandler().stopMusic();
    			break;
    		case 5:
    			handler.changeState(handler.getGalagaState());
    			handler.getMusicHandler().stopMusic();
    			break;
    		case 6:
    			System.out.println("game not available");
    			break;
    			
    		}
    	}
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
    		selector++;
    	}
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
    		selector--;
    	}
    	if(selector == -1) {
    		selector = 6;
    	}
    	
    	if(selector == 7) {
    		selector = 0;
    	}
    	
    	currentGame = Images.games[selector];
    	
    	if(selector == 0) {
    		lGame = Images.games[6];
    	}
    	else {
    		lGame = Images.games[selector - 1];
    	}
    	if(selector == 6) {
    		rGame = Images.games[0];
    	}
    	else {
    		rGame = Images.games[selector + 1];
    	}
    }

    
    @Override
    public void render(Graphics g) {

    	drawSelectionSquares(g);
    	//game squares drawings
    	g.drawImage(currentGame, handler.getWidth()/4 + handler.getWidth()/8 + handler.getWidth()/125, handler.getHeight()/3 + handler.getWidth()/125, handler.getWidth()/4 - handler.getWidth()/75, handler.getWidth()/4 - handler.getWidth()/75,null);
    	g.drawImage(lGame,(handler.getWidth()/16 + handler.getWidth()/32) + handler.getWidth()/125, (handler.getHeight()/3 + handler.getHeight()/16) + handler.getWidth()/125, handler.getWidth()/6 - handler.getWidth()/75, handler.getWidth()/6 - handler.getWidth()/75,null);
    	g.drawImage(rGame,(handler.getWidth() - (handler.getWidth()/16 + handler.getWidth()/32) - handler.getWidth()/6) + handler.getWidth()/125,  (handler.getHeight()/3 + handler.getHeight()/16) + handler.getWidth()/125, handler.getWidth()/6 - handler.getWidth()/75, handler.getWidth()/6 - handler.getWidth()/75,null);
    }

    
    public void drawSelectionSquares(Graphics g) {
    	//background
    	g.setColor(new Color(31,86,100));
    	g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    	
    	//center square
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRoundRect((handler.getWidth()/4 + handler.getWidth()/8) - handler.getWidth()/250, (handler.getHeight()/3) - handler.getWidth()/250, (handler.getWidth()/4) + handler.getWidth()/125, (handler.getWidth()/4) + handler.getWidth()/125, 55, 55);
    	g.setColor(Color.WHITE);
    	g.fillRoundRect(handler.getWidth()/4 + handler.getWidth()/8, handler.getHeight()/3, handler.getWidth()/4, handler.getWidth()/4, 55, 55);
    	
    	//left square
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRoundRect((handler.getWidth()/16 + handler.getWidth()/32) - handler.getWidth()/250, (handler.getHeight()/3 + handler.getHeight()/16) - handler.getWidth()/250, (handler.getWidth()/6) + handler.getWidth()/125, (handler.getWidth()/6) + handler.getWidth()/125, 55, 55);
    	g.setColor(Color.WHITE);
    	g.fillRoundRect(handler.getWidth()/16 + handler.getWidth()/32, handler.getHeight()/3 + handler.getHeight()/16, handler.getWidth()/6, handler.getWidth()/6, 55, 55);
    	
    	//right square
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRoundRect(handler.getWidth() - ((handler.getWidth()/16 + handler.getWidth()/32) - handler.getWidth()/250) - (handler.getWidth()/6 + handler.getWidth()/125), (handler.getHeight()/3 + handler.getHeight()/16) - handler.getWidth()/250, (handler.getWidth()/6) + handler.getWidth()/125, (handler.getWidth()/6) + handler.getWidth()/125, 55, 55);
    	g.setColor(Color.WHITE);
    	g.fillRoundRect(handler.getWidth() - (handler.getWidth()/16 + handler.getWidth()/32) - handler.getWidth()/6,  (handler.getHeight()/3 + handler.getHeight()/16), handler.getWidth()/6, handler.getWidth()/6, 55, 55);
    	
    }
    
    @Override
    public void refresh() {
       selector = 0;
    }
}
