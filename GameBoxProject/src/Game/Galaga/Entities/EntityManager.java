package Game.Galaga.Entities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 1/25/2020
 */
public class EntityManager {

    public ArrayList<BaseEntity> entities;
    public ArrayList<BaseEntity> laserEntities;
    public PlayerShip playerShip;

    public EntityManager(PlayerShip playerShip) {
        entities = new ArrayList<>();
        laserEntities = new ArrayList<>();
        this.playerShip = playerShip;
    }

    public void tick(){
        playerShip.tick();
        ArrayList<BaseEntity> toRemove = new ArrayList<>();
        for (BaseEntity entity: entities){
            if (entity.remove){
                toRemove.add(entity);
                continue;
            }
            entity.tick();
            if (entity.bounds.intersects(playerShip.bounds)){
                playerShip.damage(entity);
            }
        }
        
//---------------Code for laser Entities of myEnemy-----------------------------------------        
        for (BaseEntity laserEntity: laserEntities){
            if (laserEntity.remove){
                toRemove.add(laserEntity);
                continue;
            }
            
            laserEntity.tick();
            if (laserEntity.bounds.intersects(playerShip.bounds)){
            	laserEntity.damage(playerShip);
            }
        }
//------------------------------------------------------------------------------------------        
        
        for (BaseEntity toErase:toRemove){
        	//if its a Bee or an EnemyBee the entity to remove
        	if (toErase instanceof EnemyBee) {
				EnemyBee toEraseBee = (EnemyBee) toErase;//This block of code will remove the bee from the Array of spawned Bees per row and col.
				int row = toEraseBee.row;
				int col = toEraseBee.col;
				PlayerShip.BeeArray[row - 3][col] = 0;
				PlayerShip.spawnCountBee--;
			}
        	
        	if(toErase instanceof myEnemy) {
        		myEnemy toEraseMyEnemy = (myEnemy) toErase;
        		int row = toEraseMyEnemy.row;
        		int col = toEraseMyEnemy.col;
        		PlayerShip.myEnemyArray[row - 1][col - 2] = 0;
        		PlayerShip.spawnCountMyEnemy--;
        	}
            entities.remove(toErase);
        }

    }

    public void render(Graphics g){
        for (BaseEntity entity: entities){
            entity.render(g);
        }
        //renders all the lasers
        for (BaseEntity laserEntity: laserEntities) {
        	laserEntity.render(g);
        }
        playerShip.render(g);

    }

}
