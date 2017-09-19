
public abstract class Tile extends Sprite {

	public Tile(String image_src, float x, float y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public abstract boolean isBlocking();

}
