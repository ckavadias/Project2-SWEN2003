
public abstract class Block extends GameObject {

	public Block(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Block(Block thatSprite) throws Exception {
		super(thatSprite);
	}

	//pass a directional instruction to an adjacent block
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		int newX = 0, newY = 0;
		
		//choose the new x and y values based on direction
		newX = findNewX(direction);
		newY = findNewY(direction);
		
		if (isValidMove(newX,newY,direction, gameMap)) {
			gameMap.putInCell(getX(), getY(), (GameObject)null);
			setX(newX);
			setY(newY);
			gameMap.putInCell(newX, newY, this);
			return true;
		}
		return false;
	}
	
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
			case C_WALL:
			case WALL:
				break;
		}
		return isValid;
	}

}
