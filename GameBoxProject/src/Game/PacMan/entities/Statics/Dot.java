package Game.PacMan.entities.Statics;

import Main.Handler;
import Resources.Images;

public class Dot extends BaseStatic{
	public Dot(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanDots[1]);
		//-----------FRUIT CREATION 1 IN 30-------------------------------------------------------------------------------------------------
		if(random.nextInt(30) == 1){
			this.sprite = Images.pacmanFruits[random.nextInt(5)];
			isFruit = true;
		}
		//----------------------------------------------------------------------------------------------------------------------------------
	}
}
