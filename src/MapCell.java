//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class MapCell {
	private GameObject object;
	private Tile tile;
	
	/**
	 * Constructor
	 * starts with null tile and object
	 * 
	 */
	public MapCell() {
		// TODO Auto-generated constructor stub
		this.setObject(null);
		this.setTile(null);
	}

	/**
	 * Getter for the object stored in the cell
	 * @return the object
	 */
	public GameObject getObject() {
		return this.object;
	}

	/**
	 * Setter for the object stored in the cell
	 * @param object the object to set
	 */
	public void setObject(GameObject object) {
		this.object = object;
	}

	/**
	 * Getter for the tile stored in the cell
	 * @return the tile
	 */
	public Tile getTile() {
		return this.tile;
	}

	/**
	 * Setter for the tile stored in the cell
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	

}
