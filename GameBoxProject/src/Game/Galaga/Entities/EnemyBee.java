package Game.Galaga.Entities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import Game.Galaga.Entities.PlayerShip;

public class EnemyBee extends BaseEntity {
    int row,col;//row 3-4, col 0-7
    boolean justSpawned=true,attacking=false, positioned=false,hit=false,centered = false, atShipx = false,hitByPlayer = true, freeze = false;
    Animation idle,turn90Left;
    int spawnPos, attackCooldown = random.nextInt(100)+ 10, freezeCooldown = 0;
    int formationX,formationY,speed,centerCoolDown=60;
    int timeAlive=0, timeInPosition = 0;    
    public EnemyBee(int x, int y, int width, int height, Handler handler,int row, int col) {
        super(x, y, width, height, Images.galagaEnemyBee[0], handler);
        this.row = row;
        this.col = col;
        BufferedImage[] idleAnimList= new BufferedImage[2];
        idleAnimList[0] = Images.galagaEnemyBee[0];
        idleAnimList[1] = Images.galagaEnemyBee[1];
        idle = new Animation(512,idleAnimList);
        turn90Left = new Animation(128,Images.galagaEnemyBee);
        spawn();
        speed = 4;
        formationX=(handler.getWidth()/4)+(col*((handler.getWidth()/2)/8))+8;
        formationY=(row*(handler.getHeight()/10))+8;
    }

    private void spawn() {
        spawnPos = random.nextInt(3);//the case 3 was removed since it seemed unfair for enemies to spawn under the screen
        switch (spawnPos){
            case 0://left
                x = (handler.getWidth()/4)-width;
                y = random.nextInt(handler.getHeight()-handler.getHeight()/8);
                break;
            case 1://top
                x = random.nextInt((handler.getWidth()-handler.getWidth()/2))+handler.getWidth()/4;
                y = -height;
                break;
            case 2://right
                x = (handler.getWidth()/2)+ width + (handler.getWidth()/4);
                y = random.nextInt(handler.getHeight()-handler.getHeight()/8);
                break;
            case 3://down
                x = random.nextInt((handler.getWidth()/2))+handler.getWidth()/4;
                y = handler.getHeight()+height;
                break;
        }
        bounds.x=x;
        bounds.y=y;
    }

    @Override
    public void tick() {
        super.tick();
        idle.tick();
        if (hit){
            if (enemyDeath.end){
                remove = true;
                if(hitByPlayer) {//will only add score if it was hit by player not removed
                	handler.getScoreManager().setGalagaCurrentScore(handler.getScoreManager().getGalagaCurrentScore() + 100);//100 points for killing the Bee
                	handler.getMusicHandler().playEffect("explosion.wav");
                }
                return;
            }
            enemyDeath.tick();
        }
        if (justSpawned){
            timeAlive++;
            if (!centered && Point.distance(x,y,handler.getWidth()/2,handler.getHeight()/2)>speed & !hit){//reach center of screen
                switch (spawnPos){
                    case 0://left
                        x+=speed;
                        if (Point.distance(x,y,x,handler.getHeight()/2)>speed) {
                            if (y > handler.getHeight() / 2) {
                                y -= speed;
                            } else {
                                y += speed;
                            }
                        }
                        break;
                    case 1://top
                        y+=speed;
                        if (Point.distance(x,y,handler.getWidth()/2,y)>speed) {
                            if (x > handler.getWidth() / 2) {
                                x -= speed;
                            } else {
                                x += speed;
                            }
                        }
                        break;
                    case 2://right
                        x-=speed;
                        if (Point.distance(x,y,x,handler.getHeight()/2)>speed) {
                            if (y > handler.getHeight() / 2) {
                                y -= speed;
                            } else {
                                y += speed;
                            }
                        }
                        break;
                    /*case 3://case removed(down)
                        y-=speed;
                        if (Point.distance(x,y,handler.getWidth()/2,y)>speed) {
                            if (x > handler.getWidth() / 2) {
                                x -= speed;
                            } else {
                                x += speed;
                            }
                        }
                        break;*/
                }
                if (timeAlive>=60*60*2){
                    //more than 2 minutes in this state then die
                    //60 ticks in a second, times 60 is a minute, times 2 is 2 minutes
                    damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
                    hitByPlayer = false;
                }

            }else {//move to formation
                if (!centered){
                    centered = true;
                    timeAlive = 0;
                }
                if (centerCoolDown<=0 && !hit){
                    if (Point.distance(x, y, formationX, formationY) > speed) {//reach center of screen
                        if (Math.abs(y-formationY)>6) {
                            y -= speed;
                        }
                        if (Point.distance(x,y,formationX,y)>speed/2) {
                            if (x >formationX) {
                                x -= speed;
                            } else {
                                x += speed;
                            }
                        }
                    }else{
                        positioned =true;
                        justSpawned = false;
                    }
                }else{
                    centerCoolDown--;
                }
                if (timeAlive>=60*60*3){
                    //more than 2 minutes in this state then die
                    //60 ticks in a second, times 60 is a minute, times 2 is a minute
                    damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
                    hitByPlayer = false;//this means the Bee was eliminated, not killed
                }
                
                //sets attacking to true at random times
                if(timeAlive >= 30*attackCooldown) {//this will make them attack at random times(the var attackCooldown is a random integer)
                	justSpawned = false;
                    attacking = true;
                    positioned = false;
                }
            }
            
//------------------This is the code for the Freeze (5000+ pts)---------------------------------------------------------------------------------------
        }if(handler.getKeyManager().attackfreeze && handler.getScoreManager().getGalagaCurrentScore() >= 5000 && !freeze){//if players score is higher than 5000 they will be able to use this Power Up
        	freeze = true;
        }
        if(freeze){
        	freezeCooldown++;//adds to the coolDown until 10s
        
        }
        if(freezeCooldown > 60*10) {//10 secs
        	freeze = false;
        	freezeCooldown = 0;//resets the timer for next freeze
        }
        
//------------------This is the implementation of the random attacking---------------------------------------------------------------------------------------------------------------------------------        
        
        if (attacking && !hit && !freeze){//not frozen by the Power Up
        	if(!atShipx) {
        		//could either be at right or left of ship
        		if(x < PlayerShip.currentX && (PlayerShip.currentX - x > 14)) {
        			x += speed;
        			if(PlayerShip.currentX - x < 32) {
        				atShipx = true;//at ship, now it can go down
        			}
        		
        		}else if(x > PlayerShip.currentX && (x - PlayerShip.currentX  > 14)){
        			x-=speed;
        			if(x - PlayerShip.currentX < 32) {
        				atShipx = true;//at ship, now it can go down
        			}
        		}
        			
        	}else {
        			y+=speed;
        	}
//----------if exits the screen it can either eliminate it or re-spawn it----------------------------------------------------------------------------------	
        }if(x > handler.getWidth() || x < 0 - handler.getWidth()/8 || y > handler.getHeight() + handler.getHeight()/8 || y < 0 - handler.getHeight()/8) {
        	if(attacking) {
        		damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
        		hitByPlayer = false;//this means the Bee was eliminated, not killed
        	}else {
        		justSpawned = true;
        		positioned = false;
        		spawn();
        	}  
        }
        bounds.x=x;
        bounds.y=y;
    }
//----------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void render(Graphics g) {
    	if(Handler.DEBUG) {//auto removes the boxes if debug = false
        ((Graphics2D)g).draw(new Rectangle(formationX,formationY,32,32));
    	}
    	if (arena.contains(bounds)) {
            if (hit){
                g.drawImage(enemyDeath.getCurrentFrame(), x, y, width, height, null);              
            }else{
                g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);

            }
        }
    }

    @Override
    public void damage(BaseEntity damageSource) {
        super.damage(damageSource);
        if (damageSource instanceof PlayerLaser){
            hit=true;
            damageSource.remove = true;
        }
    }
}
