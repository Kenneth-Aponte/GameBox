package Game.Galaga.Entities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import Game.Galaga.Entities.PlayerShip;


public class myEnemy extends BaseEntity {
    int row,col;//row 1-2, columns 0-7
    boolean justSpawned=true,attacking=false, positioned=false,hit=false,centered = false, hitByPlayer = true;
    Animation idle,turn90Left;
    int spawnPos;
    int formationX,formationY,speed,centerCoolDown=60;
    int timeAlive=0, attackInt,attackCoolDown = 0;
    public myEnemy(int x, int y, int width, int height, Handler handler,int row, int col) {
        super(x, y, width, height, Images.galagamyEnemy[0], handler);
        this.row = row;
        this.col = col;
        BufferedImage[] idleAnimList= new BufferedImage[2];
        idleAnimList[0] = Images.galagamyEnemy[0];
        idleAnimList[1] = Images.galagamyEnemy[0];
        idle = new Animation(512,idleAnimList);
        turn90Left = new Animation(128,Images.galagamyEnemy);
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
                if(hitByPlayer) {//only if hit by player then add the 200 pts
                	handler.getScoreManager().setGalagaCurrentScore(handler.getScoreManager().getGalagaCurrentScore() + 200);//200 points for killing the Bee
                }           
                return;
            }
            enemyDeath.tick();
        }
        if (justSpawned){
            timeAlive++;
            if (!centered && Point.distance(x,y,handler.getWidth()/2,handler.getHeight()/2)>speed && !hit){//reach center of screen
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
                    /*case 3://down
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
                    //60 ticks in a second, times 60 is a minute, times 2 is a minute
                    damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
                    hitByPlayer = false;
                }

            }else {//move to formation
                if (!centered){
                    centered = true;
                    timeAlive = 0;
                }
                if (centerCoolDown<=0){
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
                    hitByPlayer = false;
                }
            }
            
//----------- Code for random shooting-------------------------------------------------------------------------------------------------------
            attackInt = random.nextInt(100);//a 1 in a 100 chance to shoot
        }if (attackCoolDown >= 300 && attackInt == 1){
            handler.getGalagaState().entityManager.laserEntities.add(new myEnemyLaser(x + (width / 2), y - 3, width , height , Images.galagamyEnemyLaser, handler, handler.getGalagaState().entityManager));
        	attackCoolDown = 0;
        }
        //if it exits the screen it re-spawns it
        if(x > handler.getWidth() || x < 0 - handler.getWidth()/8 || y > handler.getHeight() + handler.getHeight()/8 || y < 0 - handler.getHeight()/8) {
        		justSpawned = true;
        		positioned = false;
        		spawn();
        }
        attackCoolDown++;
        bounds.x=x;
        bounds.y=y;
//------------------------------------------------------------------------------------------------------------------------------------------    
    }

    @Override
    public void render(Graphics g) {
    	if(Handler.DEBUG) {//auto removes hitboxes if debug is false
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
            handler.getMusicHandler().playEffect("explosion.wav");
            damageSource.remove = true;
        }
    }
}
