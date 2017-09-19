
public class Floor extends Tile {
	private final static String IMAGE_SRC = "res/floor.png";
	
	public Floor( float x, float y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Floor( String image_src, float x, float y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

}
