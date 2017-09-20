
public abstract class Block extends GameObject {

	public Block(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	//pass a directional instruction to an adjacent block
	public abstract boolean push(Direction direction, GameMap gameMap) throws Exception;
	
	public boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception{
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
				isValid = true;
				break;
			case BLOCK:
				isValid = gameMap.pushBlock(x,y, direction);
				break;
			case UNIT:
			case WALL:
				break;
		}
		return isValid;
	}

}
