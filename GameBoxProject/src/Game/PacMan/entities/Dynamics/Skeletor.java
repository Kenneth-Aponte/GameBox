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

public class Skeletor extends BaseDynamic{

	protected double velX,velY,speed = 20;
	public String facing = "Up";
	public static String state = "Attack";
	public boolean moving = true,turnFlag = false,eaten = false,edibleEnding = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim,edible,edibleEndingAnim, skeletorAnim, skeletorAttackedAnim;
	static Random random = new Random();
	int turnCooldown = 30,color;
	final static int edibleOrigTimer = 60*40;
	public static int edibleTimer = edibleOrigTimer;
	PacMan enemy = handler.getPacman();
	GhostSpawner spawner = MapBuilder.getSpawner();


	public Skeletor(int x, int y, int width, int height,int speed,int color, Handler handler) {
		super(x, y, width, height, handler, Images.skeletorDefault[color]);
		
		BufferedImage[] Skeletor = new BufferedImage[12];
		Skeletor[0] = Images.skeletorDefault[0];
		Skeletor[1] = Images.skeletorDefault[1];
		Skeletor[2] = Images.skeletorDefault[2];
		Skeletor[3] = Images.skeletorDefault[3];
		Skeletor[4] = Images.skeletorDefault[4];
		Skeletor[5] = Images.skeletorDefault[5];
		Skeletor[6] = Images.skeletorDefault[6];
		Skeletor[7] = Images.skeletorDefault[7];
		Skeletor[8] = Images.skeletorDefault[8];
		Skeletor[9] = Images.skeletorDefault[9];
		Skeletor[10] = Images.skeletorDefault[10];
		Skeletor[11] = Images.skeletorDefault[11];
		skeletorAnim = new Animation(128, Skeletor);
		
		BufferedImage[] SkeletorAttacked = new BufferedImage[14];
		SkeletorAttacked[0] = Images.skeletorAttacked[0];
		SkeletorAttacked[1] = Images.skeletorAttacked[1];
		SkeletorAttacked[2] = Images.skeletorAttacked[2];
		SkeletorAttacked[3] = Images.skeletorAttacked[3];
		SkeletorAttacked[4] = Images.skeletorAttacked[4];
		SkeletorAttacked[5] = Images.skeletorAttacked[5];
		SkeletorAttacked[6] = Images.skeletorAttacked[6];
		SkeletorAttacked[7] = Images.skeletorAttacked[7];
		SkeletorAttacked[8] = Images.skeletorAttacked[8];
		SkeletorAttacked[9] = Images.skeletorAttacked[9];
		SkeletorAttacked[10] = Images.skeletorAttacked[10];
		SkeletorAttacked[11] = Images.skeletorAttacked[11];
		SkeletorAttacked[12] = Images.skeletorAttacked[12];
		SkeletorAttacked[13] = Images.skeletorAttacked[13];
		
		
		BufferedImage[] leftA = new BufferedImage[2];
		leftA[0] = Images.ghostLeft1[color];
		leftA[1] = Images.ghostLeft2[color];
		leftAnim = new Animation(128, Skeletor);
		
		BufferedImage[] rightA = new BufferedImage[2];
		rightA[0] = Images.ghostRight1[color];
		rightA[1] = Images.ghostRight2[color];
		rightAnim = new Animation(128, Skeletor);
		
		BufferedImage[] upA = new BufferedImage[2];
		upA[0] = Images.ghostUp1[color];
		upA[1] = Images.ghostUp2[color];
		upAnim = new Animation(128, Skeletor);
		
		BufferedImage[] downA = new BufferedImage[2];
		downA[0] = Images.ghostDown1[color];
		downA[1] = Images.ghostDown2[color];
		downAnim = new Animation(128, Skeletor);
		
		BufferedImage[] edibleAnimList = new BufferedImage[13];
		edibleAnimList[0] = Images.skeletorAttacked[0];
		edibleAnimList[1] = Images.skeletorAttacked[1];
		edibleAnimList[2] = Images.skeletorAttacked[2];
		edibleAnimList[3] = Images.skeletorAttacked[3];
		edibleAnimList[4] = Images.skeletorAttacked[4];
		edibleAnimList[5] = Images.skeletorAttacked[5];
		edibleAnimList[6] = Images.skeletorAttacked[6];
		edibleAnimList[7] = Images.skeletorAttacked[5];
		edibleAnimList[8] = Images.skeletorAttacked[4];
		edibleAnimList[9] = Images.skeletorAttacked[3];
		edibleAnimList[10] = Images.skeletorAttacked[2];
		edibleAnimList[11] = Images.skeletorAttacked[1];
		edibleAnimList[12] = Images.skeletorAttacked[0];

		edible = new Animation(128, SkeletorAttacked);
		
		BufferedImage[] edibleAnimListEnding = new BufferedImage[14];//this is the animList for when its almost Done
		edibleAnimListEnding[0] = Images.skeletorAttacked[7];
		edibleAnimListEnding[1] = Images.skeletorAttacked[8];
		edibleAnimListEnding[2] = Images.skeletorAttacked[9];
		edibleAnimListEnding[3] = Images.skeletorAttacked[10];
		edibleAnimListEnding[4] = Images.skeletorAttacked[11];
		edibleAnimListEnding[5] = Images.skeletorAttacked[12];
		edibleAnimListEnding[6] = Images.skeletorAttacked[13];
		edibleAnimListEnding[7] = Images.skeletorAttacked[13];
		edibleAnimListEnding[8] = Images.skeletorAttacked[12];
		edibleAnimListEnding[9] = Images.skeletorAttacked[11];
		edibleAnimListEnding[10] = Images.skeletorAttacked[10];
		edibleAnimListEnding[11] = Images.skeletorAttacked[9];
		edibleAnimListEnding[12] = Images.skeletorAttacked[8];
		edibleAnimListEnding[13] = Images.skeletorAttacked[7];
		
		edibleEndingAnim = new Animation(128, SkeletorAttacked);
		
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.velX = speed;
		this.velY = speed;
		this.color = color;
		
		
		}


	public Skeletor(int x, int y, int width, int height, Handler handler, BufferedImage bufferedImage) {
		super(x, y, width, height, handler, bufferedImage);
		
		BufferedImage[] Skeletor = new BufferedImage[12];
		Skeletor[0] = Images.skeletorDefault[0];
		Skeletor[1] = Images.skeletorDefault[1];
		Skeletor[2] = Images.skeletorDefault[2];
		Skeletor[3] = Images.skeletorDefault[3];
		Skeletor[4] = Images.skeletorDefault[4];
		Skeletor[5] = Images.skeletorDefault[5];
		Skeletor[6] = Images.skeletorDefault[6];
		Skeletor[7] = Images.skeletorDefault[7];
		Skeletor[8] = Images.skeletorDefault[8];
		Skeletor[9] = Images.skeletorDefault[9];
		Skeletor[10] = Images.skeletorDefault[10];
		Skeletor[11] = Images.skeletorDefault[11];
		skeletorAnim = new Animation(128, Skeletor);
		
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
			
			eaten = false;
			handler.getScoreManager().addPacmanCurrentScore(-100);
			return;
		}	
		if(enemy.bounds.intersects(this.bounds)) {
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
		
		//depending on the state, it will tick either the normal attacking animation or the edible animation
		//attack

		if(state.equals("Attack")) {
			
			switch (facing) {
			case "Right":
				x+=velX;
				rightAnim.tick();
				//skeletorAnim.tick();
				break;
			case "Left":
				x-=velX;
				leftAnim.tick();
				//skeletorAnim.tick();
				break;
			case "Up":
				y-=velY;
				upAnim.tick();
				//skeletorAnim.tick();
				break;
			case "Down":
				y+=velY;
				downAnim.tick();
				//skeletorAnim.tick();
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
				edible.tick();
				break;
			case "Left":
				x-=velX;
				edible.tick();
				break;
			case "Up":
				y-=velY;
				edible.tick();
				break;
			case "Down":
				y+=velY;
				edible.tick();
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
		//velY velX
		
		
		
		if((facing.equals("Up") || facing.equals("Down"))){
			if(this.bounds.getCenterX() > handler.getPacman().bounds.getCenterX() && checkPreHorizontalCollision("Left") == true) {
				facing = "Left";
				velX = speed;
				if (checkPreVerticalCollisions("Left") == false && state == "Attack") {
					this.x -= 20;
					
					}
				}
			else if (this.bounds.getCenterX() < handler.getPacman().bounds.getCenterX() && checkPreHorizontalCollision("Right") == true) {
				facing = "Right";
				velX = speed;
				if (checkPreVerticalCollisions("Right") == false && state == "Attack") {
					this.x += 20;
					
					
					}
				}
			
			if (velY == 0) {
			if(this.bounds.getCenterX() > handler.getPacman().bounds.getCenterX()) {
				facing = "Left";
				velX = speed;
				
				if (checkPreHorizontalCollision("Right") == true) {
					facing = "Left";
				}
		
			}
			else {
				facing = "Right";
				velX = speed;
				if (checkPreHorizontalCollision("Left") == true) {
					facing = "Right";
				}
			}
		}
	}

		else if((facing.equals("Right") || facing.equals("Left"))) {
			//Our y is smaller than Pacman's hence we must increase it to get the same level
			if(this.bounds.getCenterY() > handler.getPacman().bounds.getCenterY() && checkPreVerticalCollisions("Up") == true) {
				facing = "Up";
				velY = speed;
				if (checkPreVerticalCollisions("Up") == false && state != "Attack") {
					this.y -= 20;
					}
				}
			else if (this.bounds.getCenterY() < handler.getPacman().bounds.getCenterY() && checkPreVerticalCollisions("Down") == true) {
				facing = "Down";
				velY = speed;
				if (checkPreVerticalCollisions("Down") == false && state != "Attack") {
					this.y += 20;
				}

				}
			
			
			
			//if collision occurs
			if (velX == 0) {
			if(this.bounds.getCenterY() > handler.getPacman().bounds.getCenterY()) {
				facing = "Up";
				velY = speed;
				if (checkPreVerticalCollisions("Down") == true) {
					facing = "Up";
				}
			}
			else {
				facing = "Down";
				velY = speed;
				if (checkPreVerticalCollisions("Up") == true) {
					facing = "Down";
					}
				}
			}
		}
		

		//----------------------------------------------------------------------------------------------------------------------------------


		if (facing.equals("Right") || facing.equals("Left")){
			checkHorizontalCollision();
		}else{
			checkVerticalCollisions();}
		}
	


	public void checkVerticalCollisions() {
		Skeletor ghost = this;
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
		Skeletor ghost = this;
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
		Skeletor ghost = this;
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
		Skeletor ghost = this;
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
		else {
			g2.drawImage(this.sprite,x,y,width,height,null);//Draws the attacking normal animation
		}
		
		if (enemy.destroyed == true) {
			g2.drawImage(Images.skeletorKillsAgain,x,y,width*4,height*2,null); //Mocks the player		
		}
	}


	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

}
