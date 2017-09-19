
public abstract class Block extends GameObject {

	public Block(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	public boolean push(Direction direction) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isValidMove(int x, int y, Direction direction, GameMap gameMap){
		return true;
	}

}
