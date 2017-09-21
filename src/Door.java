
public class Door extends Tile {
	private final static String IMAGE_SRC = "res/door.png";
	
	public Door(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Door(Door thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Door copy() throws Exception{
		return new Door(this);
	}
	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

}
