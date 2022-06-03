import java.awt.GridLayout;
import javax.swing.JFrame;

/*
 * Controls turns and initializes the World including first rendering
 */

public class GameFrame extends JFrame {
	
	private World world;
	
	public GameFrame(World w) {
		world = w;
	}
	
	public void init() {
		setTitle("The Game of Life");
		setLayout(new GridLayout(world.getRow(), world.getColumn()));
		for (int row = 0; row < world.getRow(); row++) {
			for (int col = 0; col < world.getColumn(); col++) {
				add(world.getCellAt(row, col));
			}
		}
		addMouseListener(new TurnListener(this));
	}
	
	public void takeTurn() {
		world.update();
	}
	
}
