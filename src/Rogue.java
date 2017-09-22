import org.newdawn.slick.Input;

public class Rogue extends Unit {
	private final static String IMAGE_SRC = "res/rogue.png";
	private Direction currentDirection = Direction.LEFT;
	
	public Rogue(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Rogue(Rogue thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Rogue copy() throws Exception{
		return new Rogue(this);
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		if(!gameMap.moveWasMade()) {
			return;
		}
		if(!move(this.currentDirection, gameMap)){
			//System.out.println("ROGUE");
			switch (this.currentDirection) {
				case LEFT:
					this.currentDirection = GameObject.Direction.RIGHT;
					break;
				case RIGHT:
					this.currentDirection = GameObject.Direction.LEFT;
					break;
			}
			move(currentDirection, gameMap);
		}
		
	}

}
