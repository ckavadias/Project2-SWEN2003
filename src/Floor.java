
public class Floor extends Tile {
	private final static String IMAGE_SRC = "res/floor.png";
	
	public Floor(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Floor( String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}


	public Floor(Floor thatSprite) throws Exception {
		super(thatSprite);
	}
	public Floor copy() throws Exception {
		return new Floor(this);
	}
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

}
