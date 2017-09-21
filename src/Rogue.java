import org.newdawn.slick.Input;

public class Rogue extends Unit {
	private final static String IMAGE_SRC = "res/rogue.png";
	
	public Rogue(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(Direction direction, GameMap map) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Rogue(Rogue thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Rogue copy() throws Exception{
		return new Rogue(this);
	}
	
	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
