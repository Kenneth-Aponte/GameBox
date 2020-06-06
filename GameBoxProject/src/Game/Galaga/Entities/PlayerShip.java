package Game.Galaga.Entities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
 * Created by AlexVR on 1/25/2020
 */
public class PlayerShip extends BaseEntity{

    private int health = 3,attackCooldown = 30,speed =6,destroyedCoolDown = 60*7, BeeRow, BeeCol, myEnemyRow, myEnemyCol;
    private boolean attacking = false, destroyed = false,spawnedBee = false,spawnedMyEnemy = false, powerUpInUse = false;
    private Animation deathAnimation;
    public static int BeeArray[][] = new int[2][8];
    public static int myEnemyArray[][] = new int[2][4];
    public static int spawnCountBee = 0, spawnCountMyEnemy = 0, Spawns;
    public static int currentX,currentY;
    
    public PlayerShip(int x, int y, int width, int height, BufferedImage sprite, Handler handler) {
        super(x, y, width, height, sprite, handler);
        
        deathAnimation = new Animation(256,Images.galagaPlayerDeath);

    }

    @Override
    public void tick() {
        super.tick();
        if (destroyed){
            if (destroyedCoolDown<=0){
                destroyedCoolDown=60*7;
                destroyed=false;
                deathAnimation.reset();
                bounds.x=x;
                handler.getScoreManager().setGalagaCurrentScore(0);//if player dies the score resets and plays the start music 
            }else{
                deathAnimation.tick();
                destroyedCoolDown--;
            }
        }else {
            if (attacking) {
                if (attackCooldown <= 0) {
                    attacking = false;
                } else {
                    attackCooldown--;
                }
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attacking) {
                handler.getMusicHandler().playEffect("laser.wav");
                attackCooldown = 30;
                attacking = true;
                
//------------------------------------This is the implementation of the triple laser(Every 500 pts)--------------------------------------------------------------------
                if(handler.getScoreManager().getGalagaCurrentScore() % 500 == 0 && handler.getScoreManager().getGalagaCurrentScore() != 0 && !powerUpInUse) {//if the game score is divisible by 5 (500 pts, 100 pts, etc)
                	handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2), this.y - 3, width / 4, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));
                	handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2) - (width), this.y - 3 + (height), width / 4, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));
                	handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2) + (width), this.y - 3 + (height), width / 4, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));
                	powerUpInUse = true;
                }
                //this is the code for the big laser(just for fun, makes no difference to the gameplay what so ever)
                else if(handler.getScoreManager().getGalagaCurrentScore() % 700 == 0 && !powerUpInUse && handler.getScoreManager().getGalagaCurrentScore() != 0){ // New humongous rocket. 
                	handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2), this.y, width * 2, height * 4, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));
                	powerUpInUse = true;
                }
                //normal lasers will shoot through here
                else {
                	handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2), this.y - 3, width / 5, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));
                	powerUpInUse = false;
                }
            }
            
            
//----------------------------The bounds and up and down movement of ship----------------------------------------------------------------------------------------------
            if (handler.getKeyManager().left && this.x > (handler.getWidth()/4) + 5) {//(the width of the screen / 4) + 5 so that it does not disappear.
                x -= (speed);
            }
            if (handler.getKeyManager().right && this.x < (((handler.getWidth()/4) * 3) - this.width)-5) {//((the width of the screen / 4) * 3) - the width of the Player Ship
                x += (speed);
            }            
            // Adding Down movement
            if (handler.getKeyManager().down && this.y < ((handler.getHeight() - this.height) - (handler.getHeight()/18)- 5)) {
            	y+=speed;
            }
            // Adding Up movement
            if (handler.getKeyManager().up && this.y > ((handler.getHeight()/2)  + this.height)) {
            	y-=speed;            
            }
            
            
//----------------------Implementation of N and L keys-------------------------------------------------------------------------------------------------------------------------------------------       
            if (handler.getKeyManager().dies) {
            	destroyed = true;
            	handler.getMusicHandler().playEffect("explosion.wav");
            	health--;// when key pressed is N, the player dies and looses a life
            }
            if(handler.getKeyManager().newLife & health < 3) {
            	health ++;//when key pressed is M, the player receives a new life(unless they already have 3)
            }
            
            
//---------------------------- Spawning (Random spawning or P / O keys)----------------------------------------------------------------------------------------------------------------------------
            
            //Bee spawn
            Spawns = random.nextInt(100);//a 1 in 100 chance to spawn a Bee
            if((handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)|| Spawns == 1) && spawnCountBee < 16) {//either P was pressed or random spawn
            	spawnedBee = false;
            	while(!spawnedBee) {
            		BeeRow = random.nextInt(2)+3;//rows 3 - 4
            		BeeCol = random.nextInt(8);//cols 0 - 7
            		
            		if(BeeArray[BeeRow - 3][BeeCol] != 1) {//if the value is not 1 it can spawn a Bee in that pos(the value is 0)
            			handler.getGalagaState().entityManager.entities.add(new EnemyBee(0,0,32,32,handler,BeeRow,BeeCol));
            			spawnCountBee++;
            			BeeArray[BeeRow - 3][BeeCol] = 1;
            			spawnedBee = true;//it picked an unoccupied spot            			
            		}
            		else {
            			continue;//Keep checking for a place to take since it has to take one at this point.
            		}
            	}           	
            }
            
            //myEnemy spawn
            Spawns = random.nextInt(100);//a 1 in 100 chance to spawn a myEnemy
            if((handler.getKeyManager().keyJustPressed(KeyEvent.VK_O) || Spawns == 1)&& spawnCountMyEnemy < 8) {//either O was pressed or random spawn
            	spawnedMyEnemy = false;
            	while(!spawnedMyEnemy) {
            		myEnemyRow = random.nextInt(2) + 1;//rows 1 - 2
            		myEnemyCol = random.nextInt(4) + 2;//cols 2 - 5
            		
            		if(myEnemyArray[myEnemyRow - 1][myEnemyCol - 2] != 1) {//if the value is not 1 it can spawn a Bee in that pos(the value is 0)
            			handler.getGalagaState().entityManager.entities.add(new myEnemy(0,0,32,32,handler,myEnemyRow,myEnemyCol));
            			spawnCountMyEnemy++;
            			myEnemyArray[myEnemyRow - 1][myEnemyCol - 2] = 1;
            			spawnedMyEnemy = true;//it picked an unoccupied spot            			
            		}            		
            		else {
            			continue;//Keep checking for a place to take since it has to take one at this point.
            		}
            	}
            }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
        //added y bounds for hitbox, since movement up and down requires it
            bounds.x = x;
            bounds.y = y;
            
        //added a way to access the x and y from other files
            currentX = this.x;
            currentY = this.y;
        }

    }

    @Override
    public void render(Graphics g) {
         if (destroyed){
             if (deathAnimation.end){
                 g.drawString("READY",handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2);
             }else {
                 g.drawImage(deathAnimation.getCurrentFrame(), x, y, width, height, null);
             }
         }else {
             super.render(g);
         }
    }

    @Override
    public void damage(BaseEntity damageSource) {
        if (damageSource instanceof PlayerLaser){
            return;
        }
        health--;
        destroyed = true;
        handler.getMusicHandler().playEffect("explosion.wav");
        bounds.x = -10;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

}
