
public class MapCell {
	private GameObject object;
	private Tile tile;
	
	public MapCell() {
		// TODO Auto-generated constructor stub
		this.setObject(null);
		this.setTile(null);
	}

	/**
	 * @return the object
	 */
	public GameObject getObject() {
		return this.object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(GameObject object) {
		this.object = object;
	}

	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return this.tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	

}
