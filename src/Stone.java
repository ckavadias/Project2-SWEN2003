public class Stone extends Block {
	private final static String IMAGE_SRC = "res/stone.png";
	
	public Stone(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	public Stone(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Stone(Stone thatSprite) throws Exception {
		super(thatSprite);
	}
	
	@Override
	public Sprite copy() throws Exception {
		return new Stone(this);
	}
}
