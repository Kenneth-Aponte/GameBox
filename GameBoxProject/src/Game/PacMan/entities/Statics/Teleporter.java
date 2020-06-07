package Game.PacMan.entities.Statics;

import Main.Handler;

public class Teleporter{
	public int teleporterPos,x,y,width,height;

	public Teleporter(int x, int y, int width, int height, Handler handler,int teleporterPos) {
		this.teleporterPos = teleporterPos;
		this.x = x;
		this.y = y;
		
	}

}
