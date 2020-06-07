package Game.PacMan.entities.Dynamics;

import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Game.PacMan.entities.Statics.Teleporter;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacMan extends BaseDynamic{

	protected double velX,velY;
	public static double speed = 1;
	public String facing = "Left";
	public boolean moving = true,turnFlag = false,soundPlayed = false;
	public boolean destroyed = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim;
	public int ghostsEaten = 0;
	int turnCooldown = 20,respawnCooldown = 60*2;


	public PacMan(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanRight[0]);
		leftAnim = new Animation(128,Images.pacmanLeft);
		rightAnim = new Animation(128,Images.pacmanRight);
		upAnim = new Animation(128,Images.pacmanUp);
		downAnim = new Animation(128,Images.pacmanDown);
	}

	@Override
	public void tick(){
		//-------PACMAN COLLISION WITH GHOSTS---------------------------------------------------------------------------------------
		if(destroyed) {
			if(!pacManDeath.end) {
				pacManDeath.tick();
			}
			if(respawnCooldown <= 0 && health > 0) {
				destroyed = false;
				soundPlayed = false;
				this.x = MapBuilder.getpacX();
				this.y = MapBuilder.getpacY();
				pacManDeath.reset();
				facing = "Left";
				respawnCooldown = 60*2;
			}
			else if(respawnCooldown <= 0) {
				destroyed = false;
				pacManDeath.reset();
				handler.changeState(handler.getGameOverState());
				//score will reset if killed 3 times
				if(handler.getScoreManager().getPacmanCurrentScore() > handler.getScoreManager().getPacmanHighScore()) {
					handler.getScoreManager().setPacmanHighScore(handler.getScoreManager().getPacmanCurrentScore());
				}
				handler.getScoreManager().setPacmanCurrentScore(0);
		}
		else {
			if(!soundPlayed) {
				handler.getMusicHandler().playEffect("pacman_death.wav");
				health--;
				soundPlayed = true;
			}
			respawnCooldown--;
			return;
		}
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	if (health > 3) {
		health = 3;
	}

	if (health < 0) {
		health = 0;
	}
	//---------DEBUG KEYS---------------------------------------------------------------------------------------------------------------
	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N) && health < 3) {
		health++;
		handler.getMusicHandler().playEffect("pacman_extrapac.wav");//added sound when adding a new life
	}

	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_O)){
		handler.getPacManState().dotsConsumed += 50;
	}

	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P) && health > 0){
		destroyed =true;
	}
	//----------------------------------------------------------------------------------------------------------------------------------

	switch (facing){
	case "Right":
		x+=velX;
		rightAnim.tick();
		break;
	case "Left":
		x-=velX;
		leftAnim.tick();
		break;
	case "Up":
		y-=velY;
		upAnim.tick();
		break;
	case "Down":
		y+=velY;
		downAnim.tick();
		break;
	}
	if (turnCooldown<=0){
		turnFlag= false;
	}
	if (turnFlag){
		turnCooldown--;
	}

	if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) && !turnFlag && checkPreHorizontalCollision("Right")){
		facing = "Right";
		turnFlag = true;
		turnCooldown = 20;
	}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) && !turnFlag&& checkPreHorizontalCollision("Left")){
		facing = "Left";
		turnFlag = true;
		turnCooldown = 20;
	}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)  ||handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) && !turnFlag&& checkPreVerticalCollisions("Up")){
		facing = "Up";
		turnFlag = true;
		turnCooldown = 20;
	}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) && !turnFlag&& checkPreVerticalCollisions("Down")){
		facing = "Down";
		turnFlag = true;
		turnCooldown = 20;
	}

	if (facing.equals("Right") || facing.equals("Left")){
		checkHorizontalCollision();
	}else{
		checkVerticalCollisions();
	}
	//bounds for the collision with ghost
	bounds.x = x;
	bounds.y = y;
}

public void checkVerticalCollisions() {
	PacMan pacman = this;
	ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
	ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

	boolean pacmanDies = false;
	boolean toUp = moving && facing.equals("Up");

	Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

	velY = speed;
	for (BaseStatic brick : bricks) {
		if (brick instanceof BoundBlock) {
			Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
			if (pacmanBounds.intersects(brickBounds)) {
				velY = 0;
				if (toUp)
					pacman.setY(brick.getY() + pacman.getDimension().height);
				else
					pacman.setY(brick.getY() - brick.getDimension().height);
			}
		}
	}

	for(BaseDynamic enemy : enemies){
		Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
		if (pacmanBounds.intersects(enemyBounds)) {
			pacmanDies = true;
			break;
		}
	}

	if(pacmanDies) {
		handler.getMap().reset();
	}
}


public boolean checkPreVerticalCollisions(String facing) {
	PacMan pacman = this;
	ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();

	boolean toUp = moving && facing.equals("Up");

	//grid snapping
	int gridBlock = handler.getWidth()/4;
	for(int i = 0; i <= 50;i++){
		if((gridBlock + MapBuilder.pixelMultiplier - (this.width/3)) > x && this.facing.equals("Left")) {
			x = gridBlock;        		
			break;
		}
		else if ((gridBlock + MapBuilder.pixelMultiplier + (this.width/3)) > x && this.facing.equals("Right"))  {
			x = gridBlock + MapBuilder.pixelMultiplier;
			break;
		}
		gridBlock += MapBuilder.pixelMultiplier;
	}

	Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();       
	velY = speed;
	for (BaseStatic brick : bricks) {
		if (brick instanceof BoundBlock) {
			Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
			if (pacmanBounds.intersects(brickBounds)) {
				return false;
			}
		}
	}
	return true;

}



public void checkHorizontalCollision(){
	PacMan pacman = this;
	ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
	ArrayList<Teleporter> teleporters = handler.getMap().getTeleportersOnMap();
	ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
	velX = speed;
	boolean pacmanDies = false;
	boolean toRight = moving && facing.equals("Right");

	Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

	for(BaseDynamic enemy : enemies){
		Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
		if (pacmanBounds.intersects(enemyBounds)) {
			pacmanDies = true;
			break;
		}
	}
	
	for(Teleporter tp: teleporters) {
		if(this.x == tp.x) {
			if(tp.teleporterPos == 2) {
				this.x = teleporters.get(0).x;
				break;
			}
			else {
				this.x = teleporters.get(1).x;
				break;
			}
		}
	}

	if(pacmanDies) {
		handler.getMap().reset();
	}else {

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					velX = 0;
					if (toRight)
						pacman.setX(brick.getX() - pacman.getDimension().width);
					else
						pacman.setX(brick.getX() + brick.getDimension().width);
				}
			}
		}
	}
}


public boolean checkPreHorizontalCollision(String facing){
	PacMan pacman = this;
	ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
	velX = speed;
	boolean toRight = moving && facing.equals("Right");

	//grid snapping
	int gridBlock = 0;
	for(int i = 0; i <= 50;i++){
		if((gridBlock + MapBuilder.pixelMultiplier - (this.height/3)) > y && this.facing.equals("Up")) {
			y = gridBlock;        		
			break;
		}
		else if((gridBlock + MapBuilder.pixelMultiplier) + (this.height/3) > y && this.facing.equals("Down")) {
			y = gridBlock + MapBuilder.pixelMultiplier;
			break;
		} 
		gridBlock += MapBuilder.pixelMultiplier;
	}

	Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

	for (BaseStatic brick : bricks) {
		if (brick instanceof BoundBlock) {
			Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
			if (pacmanBounds.intersects(brickBounds)) {
				return false;
			}
		}
	}
	return true;
}


public double getVelX() {
	return velX;
}
public double getVelY() {
	return velY;
}
public boolean getDestroyed() {
	return destroyed;
}
public static int getHealth() {
	return health;
}


}
