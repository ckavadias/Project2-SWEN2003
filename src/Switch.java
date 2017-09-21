
public class Switch extends Tile {
	private final static String IMAGE_SRC = "res/switch.png";
	
	public Switch(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	public Switch(Switch thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Switch copy() throws Exception{
		return new Switch(this);
	}
	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

}
