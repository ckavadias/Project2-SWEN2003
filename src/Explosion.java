import org.newdawn.slick.Input;

public class Explosion extends Sprite {
	private final static String IMAGE_SRC = "res/explosion.png";
	private final float TIME = 400f;
	private float timeSinceBang = 0f;
	
	public Explosion(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	public Explosion(Sprite thatSprite) throws Exception {
		super(thatSprite);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Input input, float delta, GameMap gameMap) throws Exception{
		this.timeSinceBang+=delta;
		if(this.timeSinceBang >= TIME) {
			gameMap.removeSprite(this);
		}
	}

	@Override
	public Sprite copy() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
