//Adapted for use by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    /** number of columns on screen of TILE_SIZE*/
    public static final int COLUMNS = SCREEN_WIDTH/TILE_SIZE;
    /**number of rows on the screen of TILE_SIZE */
    public static final int ROWS = SCREEN_HEIGHT/TILE_SIZE;
    private TextField requestName;
    private boolean start = true;
    private World world;
    private User theUser;

    public App()
    {    	
        super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
    	try {
			world = new World();
			TrueTypeFont trueTypeFont = 
					new TrueTypeFont(new java.awt.Font("Arial" , java.awt.Font.PLAIN , 16), false);
			
			requestName = new TextField(gc,trueTypeFont,
					SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 5*TILE_SIZE, TILE_SIZE);
			requestName.setTextColor(Color.white);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if(start && input.isKeyPressed(Input.KEY_ENTER)) {
        		this.theUser = Loader.loadUser(requestName.getText());
        		this.start = false;
        }
        if(!start) {
        	this.theUser.incrementScore(delta);
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
        		gc.exit();
        }
        try {
			world.update(input, delta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
    	if(start) {
    		g.setColor(Color.white);
    		g.drawString("Enter username: ", SCREEN_WIDTH/2, SCREEN_HEIGHT/2 - TILE_SIZE);
    		requestName.render(gc, g);
    		requestName.setFocus(true);
    		return;
    	}
    	try {
			world.render(g);
			this.theUser.render(g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}