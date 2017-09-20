import org.newdawn.slick.Input;

public class Mage extends Unit {
	private final static String IMAGE_SRC = "res/mage.png";
	
	public Mage(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(Direction direction, GameMap map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
