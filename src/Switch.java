//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Switch extends Tile {
	private final static String IMAGE_SRC = "res/switch.png";
	private boolean isOn = false;
	
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

	public void turnOnOff(Door theDoor) {
		theDoor.openClose();
		this.isOn = !this.isOn;
	}

}
