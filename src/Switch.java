//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Switch extends Tile {
	private final static String IMAGE_SRC = "res/switch.png";
	private boolean isOn = false;
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Switch(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Switch that is being copied
	 * @throws Exception
	 */
	public Switch(Switch thatSprite) throws Exception{
		super(thatSprite);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Switch copy() throws Exception{
		return new Switch(this);
	}
	
	/* (non-Javadoc)
	 * @see Tile#isBlocking()
	 */
	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Change the status of the switch and activate the same change in the door
	 * @param theDoor the door to which this switch is aligned
	 */
	public void turnOnOff(Door theDoor) {
		theDoor.openClose();
		this.isOn = !this.isOn;
	}

}
