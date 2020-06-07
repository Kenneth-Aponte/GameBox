package Game.PacMan.World;

import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Dynamics.Skeletor;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Teleporter;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

import java.awt.event.KeyEvent;

public class Map {

	ArrayList<BaseStatic> blocksOnMap;
	ArrayList<Teleporter> teleporters;
	ArrayList<BaseDynamic> enemiesOnMap;
	Handler handler;
	private double bottomBorder;
	private boolean DEBUG = false;

	public Map(Handler handler) {
		this.handler=handler;
		this.blocksOnMap = new ArrayList<>();
		this.teleporters = new ArrayList<>();
		this.enemiesOnMap = new ArrayList<>();
		bottomBorder=handler.getHeight();
	}

	public void addBlock(BaseStatic block){
		blocksOnMap.add(block);
	}

	public void addTeleporter(Teleporter teleporter) {
		teleporters.add(teleporter);
	}
	
	public void addEnemy(BaseDynamic entity){
		enemiesOnMap.add(entity);
	}
	

	public void drawMap(Graphics2D g2) {
		for (BaseStatic block:blocksOnMap) {
			if(block instanceof BigDot) {
				//Ticks and renders the BigDot for the blinking animation
				block.tick();
				block.render(g2);
				continue;
			}
			g2.drawImage(block.sprite, block.x, block.y, block.width, block.height, null);
		}
		
		for (BaseDynamic entity:enemiesOnMap) {
			if (entity instanceof PacMan) {
				if(((PacMan)entity).getDestroyed() && !((PacMan)entity).pacManDeath.end) {
					//ticks the pacman Death animation
					g2.drawImage(((PacMan) entity).pacManDeath.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				}
				else if(!((PacMan)entity).getDestroyed()){
					switch (((PacMan) entity).facing){
					case "Right":
						g2.drawImage(((PacMan) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Left":
						g2.drawImage(((PacMan) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Up":
						g2.drawImage(((PacMan) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Down":
						g2.drawImage(((PacMan) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					}
				}
			}
			else if(entity instanceof Ghost){
				if(Ghost.state.equals("Edible")) {
					entity.render(g2);
					continue;
				}
				switch (((Ghost) entity).facing){
				case "Right":
					g2.drawImage(((Ghost) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Left":
					g2.drawImage(((Ghost) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Up":
					g2.drawImage(((Ghost) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Down":
					g2.drawImage(((Ghost) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				}
				continue;
			}
			
			else if (entity instanceof Skeletor) {
				//render skeletor and his custom animations.
				entity.render(g2);
				switch (((Skeletor) entity).facing){
				case "Right":
					g2.drawImage(((Skeletor) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Left":
					g2.drawImage(((Skeletor) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Up":
					g2.drawImage(((Skeletor) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Down":
					g2.drawImage(((Skeletor) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				}
				continue;
			}
//Debug key for grid lines on the map
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_B)) {
			if(DEBUG){
				DEBUG = false;
			}
			else
				DEBUG = true;
		}

		//ACTUAL GRID
		if(DEBUG) {
			for(int i = 0; i < 39;i++){
				//vertical lines
				g2.setColor(Color.DARK_GRAY);
				g2.drawLine((i* MapBuilder.pixelMultiplier) + 1+handler.getWidth()/4,MapBuilder.pixelMultiplier,(i*MapBuilder.pixelMultiplier) + 1 + handler.getWidth()/4, 39 * MapBuilder.pixelMultiplier);        	
			}
			for(int i = 0; i < 40;i++){
				//horizontal lines
				g2.drawLine(MapBuilder.pixelMultiplier+handler.getWidth()/4, (i*MapBuilder.pixelMultiplier) + 1, 38* MapBuilder.pixelMultiplier+handler.getWidth()/4, (i*MapBuilder.pixelMultiplier) + 1);
			}
		}
	}

	public ArrayList<BaseStatic> getBlocksOnMap() {
		return blocksOnMap;
	}
	
	public ArrayList<Teleporter> getTeleportersOnMap() {
		return teleporters;
	}

	public ArrayList<BaseDynamic> getEnemiesOnMap() {
		return enemiesOnMap;
	}

	public double getBottomBorder() {
		return bottomBorder;
	}

	public void reset() {
	}
}
