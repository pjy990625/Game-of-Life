import java.awt.*;
import javax.swing.JPanel;

/* 
 * Hold Plants or Herbivores or nothing
 * Represented as a square in the world
 */

public class Cell extends JPanel {

    private World world;
    private Lifeform life;
    private int xCord;
    private int yCord;
	
	public Cell(World w, int row, int col) {
	    xCord = row;
	    yCord = col;
		world = w;
		setBackground(Color.WHITE);
	}
	
	protected World getWorld() {
	    return world;
	}
	
	protected int getXCord() {
	    return xCord;
	}
	
	protected int getYCord() {
	    return yCord;
	}
	
	protected Lifeform getLifeform() {
	    return life;
	}
	
	protected void addLife(Lifeform l) {
		if (l == null) {
			life = null;
		} else {
			life = l;
			l.cell = this;
		}
	}
	
	protected void removeLife() {
	    life = null;
	}
	
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
    
    protected void paintCell() {
        if (life == null) {
            setBackground(Color.WHITE);
        } else {
            setBackground(life.getColour());
        }
    }
	
}
