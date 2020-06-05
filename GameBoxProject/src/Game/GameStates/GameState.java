package Game.GameStates;

import Display.UI.ClickListener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Main.Handler;
import Resources.Images;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GameState extends State {

    private UIManager uiManager;

    public GameState(Handler handler){
        super(handler);
        refresh();
    }


    @Override
    public void tick() {

        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.selectionBackground,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);
    }

    @Override
    public void refresh() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton((handler.getWidth() / 2) - (handler.getWidth() /3) + 24, (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/7, handler.getHeight()/6, Images.muteIcon, new ClickListener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {

                    handler.getMouseManager().setUimanager(null);
                    handler.getMusicHandler().triggerGalaga();
                }
            }
        }));

        uiManager.addObjects(new UIImageButton(((handler.getWidth() / 2)) - ((handler.getWidth() / 14)) , (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/8, handler.getHeight()/8, Images.muteIcon, new ClickListener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {
                    handler.getMouseManager().setUimanager(null);
                    handler.getMusicHandler().stopMusic();
                }
            }
        }));

        uiManager.addObjects(new UIImageButton(((handler.getWidth() / 2)) + ((handler.getWidth() / 5)) - ((handler.getWidth() / 64)), (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/8, handler.getHeight()/8, Images.muteIcon, new ClickListener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {
                    handler.getMouseManager().setUimanager(null);
                    //----------PHASE_1 Spec # 2--------------------------------------------------------------------------------------
                    // add music to the Zelda start screen
                  	handler.getMusicHandler().changeMusic("lost-woods.wav");
                    //----------------------------------------------------------------------------------------------------------------
                }
            }
        }));


    }
}
