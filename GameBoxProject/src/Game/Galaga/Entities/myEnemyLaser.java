package Game.Galaga.Entities;

import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 1/25/2020
 */
public class myEnemyLaser extends BaseEntity {

    EntityManager enemy;
    int speed = 10;

    public myEnemyLaser(int x, int y, int width, int height, BufferedImage sprite, Handler handler,EntityManager PlayerShip) {
        super(x, y, width, height, sprite, handler);
        this.enemy=PlayerShip;
    }

    @Override
    public void tick() {
        if (!remove) {
            super.tick();
            y += speed;//goes down on the screen
            bounds.y = y;//sets the lasers bounds
            BaseEntity playerEnemy = enemy.playerShip;
            //no need for a loop like the playerShip Laser since it is only one enemy(The Player Ship)
            if (playerEnemy.bounds.intersects(bounds)) {
            	playerEnemy.damage(this);//this kills the Player
            	remove = true;//removes the laser 
                
            }
            if (this.y > handler.getHeight()) {
            	remove = true;
            }
        }
    }
}
