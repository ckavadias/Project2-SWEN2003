
public class Ice extends Block {
	private final static String IMAGE_SRC = "res/ice.png";
	
	public Ice(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
