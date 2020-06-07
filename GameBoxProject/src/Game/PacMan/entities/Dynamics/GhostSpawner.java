package Game.PacMan.entities.Dynamics;

import java.util.Random;
import java.awt.event.KeyEvent;
import Main.Handler;

public class GhostSpawner{
	public int x,y,width,height,spawned = 0,color,speed;
	public Handler handler;
	public boolean firstSpawn = true;
	Random random = new Random();

	public GhostSpawner(int xPos, int yPos, int width, int height, Handler handler) {
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;		
		this.handler = handler;
	}
	
	
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)&& spawned < 10) {
			spawnGhost(random.nextInt(2)+1,random.nextInt(4));//spawns a new Ghost when called with different attributes
			//10 + 1 since the speed can't be 0
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && spawned < 10) {
			spawnSkeletor(2,random.nextInt(4));			
		}
		
		if (handler.getPacman().ghostsEaten == 16) {
			handler.getPacman().ghostsEaten++;
			spawnSkeletor(2,random.nextInt(4));	
		}
		
		//---------SPAWNING OF GHOSTS AT BEGGINING OF GAME-------------------
		if(firstSpawn) {
			for(int i = 0;i < 4;i++) {
				spawnGhost(random.nextInt(2)+1, i);
			}
			firstSpawn = false;
		}
		//-------------------------------------------------------------------
	}
	
	public void spawnGhost(int speed, int color) {
		Ghost ghost = new Ghost(x,y,width,height,speed,color,handler);
		handler.getMap().addEnemy(ghost);
		spawned++;
	}
	
	public void spawnSkeletor(int speed, int color) {
		Skeletor sk = new Skeletor(x,y,width,height,speed,color,handler);
		handler.getMap().addEnemy(sk);
		spawned++;
	}
}
