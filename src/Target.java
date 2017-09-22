//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Target extends Floor {
	private final static String IMAGE_SRC = "res/target.png";
	
	public Target(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Target(Target thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Target copy() throws Exception{
		return new Target(this);
	}

}
