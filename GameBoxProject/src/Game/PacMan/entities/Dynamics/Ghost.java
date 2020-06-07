package Game.PacMan.entities.Dynamics;

import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends BaseDynamic{

	protected double velX,velY,speed = 1;
	public String facing = "Up";
	public static String state = "Attack";
	public boolean moving = true,turnFlag = false,eaten = false,edibleEnding = false,drawScore = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim,edible,edibleEndingAnim;
	static Random random = new Random();
	int turnCooldown = 30,color;
	final static int edibleOrigTimer = 60*40,scoreFloatingOrigTimer = 30;
	public static int edibleTimer = edibleOrigTimer,scoreFloatingTimer = scoreFloatingOrigTimer,scoreX, scoreY;
	PacMan enemy = handler.getPacman();
	GhostSpawner spawner = MapBuilder.getSpawner();


	public Ghost(int x, int y, int width, int height,int speed,int color, Handler handler) {
		super(x, y, width, height, handler, Images.ghostUp1[color]);
		
		BufferedImage[] leftA = new BufferedImage[2];
		leftA[0] = Images.ghostLeft1[color];
		leftA[1] = Images.ghostLeft2[color];
		leftAnim = new Animation(128,leftA);
		
		BufferedImage[] rightA = new BufferedImage[2];
		rightA[0] = Images.ghostRight1[color];
		rightA[1] = Images.ghostRight2[color];
		rightAnim = new Animation(128,rightA);
		
		BufferedImage[] upA = new BufferedImage[2];
		upA[0] = Images.ghostUp1[color];
		upA[1] = Images.ghostUp2[color];
		upAnim = new Animation(128,upA);
		
		BufferedImage[] downA = new BufferedImage[2];
		downA[0] = Images.ghostDown1[color];
		downA[1] = Images.ghostDown2[color];
		downAnim = new Animation(128,downA);
		
		BufferedImage[] edibleAnimList = new BufferedImage[2];
		edibleAnimList[0] = Images.ghostEdible[0];
		edibleAnimList[1] = Images.ghostEdible[1];
		edible = new Animation(128,edibleAnimList);
		
		BufferedImage[] edibleAnimListEnding = new BufferedImage[4];//this is the animList for when its almost Done
		edibleAnimListEnding[0] = Images.ghostEdible[0];
		edibleAnimListEnding[1] = Images.ghostEdible[1];
		edibleAnimListEnding[2] = Images.ghostEdible[2];
		edibleAnimListEnding[3] = Images.ghostEdible[3];
		edibleEndingAnim = new Animation(128,edibleAnimListEnding);
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.velX = speed;
		this.velY = speed;
		this.color = color;
	}


	@Override
	public void tick(){
		super.tick();
		bounds.x = x;
		bounds.y = y;
		//verifies if it was eaten and resends it to the cage
		if(eaten) {
			drawScore = true;
			scoreX = this.x;
			scoreY = this.y;
			this.x = spawner.x;
			this.y = spawner.y;
			this.facing = "Up";
			this.velX = 0;
			this.velY = speed;
			waitTime = (random.nextInt(10)+1)*60;//gives it a random amount of time for it to spawn again
			eaten = false;
			handler.getScoreManager().addPacmanCurrentScore(500);
			handler.getPacman().ghostsEaten++;
			return;
		}	
		if(enemy.bounds.intersects(this.bounds) && state.equals("Attack")) {
			enemy.destroyed = true;
		}
		else if (enemy.bounds.intersects(this.bounds) && state.equals("Edible")) {
			this.eaten = true;
		}	
		//debug key
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
			state = "Edible";
		}
		//wait counter for the ghost to exit the cage
		
		if(scoreFloatingTimer > 0) {
			if(scoreFloatingTimer % 5 == 0){scoreY--;}//floating Effect
			scoreFloatingTimer--;
		}
		else if(scoreFloatingTimer <= 0) {
			scoreFloatingTimer = scoreFloatingOrigTimer;
			drawScore = false;
		}
		
		if(waitTime > 0) {
			waitTime--;
			return;
		}
		//depending on the state, it will tick either the normal attacking animation or the edible animation
		//attack
		if(state.equals("Attack")) {
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
		}
		//edible
		else if(state.equals("Edible")) {
			if(edibleTimer > (edibleOrigTimer)/3) {edible.tick();}//a third of the time
			else {edibleEndingAnim.tick();}
			
			switch (facing){
			case "Right":
				x+=velX;
				break;
			case "Left":
				x-=velX;
				break;
			case "Up":
				y-=velY;
				break;
			case "Down":
				y+=velY;
				break;
			}
			//removes from the edible timer
			if(edibleTimer > 0) {
				edibleTimer--;
			}
			else {
				edibleTimer = edibleOrigTimer;
				handler.getMusicHandler().stopMusic();//stops the music when edible state is over
				state = "Attack";
			}
		}

		//------------------------AUTO MOVEMENT OF GHOSTS-----------------------------------------------------------------------------------
		if(velY == 0 && (facing.equals("Up") || facing.equals("Down"))){
			if(random.nextInt(2) == 1) {
				facing = "Left";
				velX = speed;
			}
			else {
				facing = "Right";
				velX = speed;
			}
		}

		if(velX == 0 && (facing.equals("Right") || facing.equals("Left"))) {
			if(random.nextInt(2) == 1) {
				facing = "Up";
				velY = speed;
			}
			else {
				facing = "Down";
				velY = speed;
			}
		}
		//----------------------------------------------------------------------------------------------------------------------------------


		if (facing.equals("Right") || facing.equals("Left")){
			checkHorizontalCollision();
		}else{
			checkVerticalCollisions();
		}
	}


	public void checkVerticalCollisions() {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

		boolean ghostDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						ghost.setY(brick.getY() + ghost.getDimension().height);
					else
						ghost.setY(brick.getY() - brick.getDimension().height);
				}
			}
		}

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
			if (ghostBounds.intersects(enemyBounds)) {
				ghostDies = true;
				break;
			}
		}

		if(ghostDies) {
			handler.getMap().reset();
		}
	}


	public boolean checkPreVerticalCollisions(String facing) {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;

	}



	public void checkHorizontalCollision(){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		velX = speed;
		boolean ghostDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (ghostBounds.intersects(enemyBounds)) {
				ghostDies = true;
				break;
			}
		}

		if(ghostDies) {
			handler.getMap().reset();
		}else {

			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (ghostBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							ghost.setX(brick.getX() - ghost.getDimension().width);
						else
							ghost.setX(brick.getX() + brick.getDimension().width);
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}


	@Override
	public void render(Graphics2D g2) {
		if(state.equals("Edible")) {
			if(edibleTimer > (edibleOrigTimer)/3){
				g2.drawImage(edible.getCurrentFrame(),x,y,width,height,null);//Draws the edible animation
			}
			else {
				g2.drawImage(edibleEndingAnim.getCurrentFrame(),x,y,width,height,null);//Draws the edible animation of when its ending
			}		
		}
		if(drawScore) {
			g2.drawImage(Images.pacmanScore[0],scoreX,scoreY-(this.height/2),width,height/2,null);//draws floating 500 points when eaten	
		}
	}


	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

}
