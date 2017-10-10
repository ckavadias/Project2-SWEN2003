import org.newdawn.slick.Graphics;

public class User {
	private final String name;
	private final double highScore;
	private final int SEC = 1000;
	private double score = 0;
	
	public User(String name, double highScore) {
		this.name = name;
		this.highScore = highScore;
	}
	
	public void incrementScore(double increment) {
		this.score += increment;
	}

	/**
	 * @return the highScore
	 */
	public double getHighScore() {
		return highScore;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	 
	public boolean equals(String name) {
		return name.equals(this.name);
	}
	
	public boolean equals(User user) {
		return user.equals(this.name);
	}

	public void render(Graphics g) {
		g.drawString("Player: " + this.name, 0, App.TILE_SIZE);
		g.drawString(" Time: " + (this.score/SEC), 0, 2*App.TILE_SIZE);
		
	}
}
