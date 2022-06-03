import java.awt.Color;

/* 
 * Shown as green in a cell
 * Placed initially in a random order 
 * Never die and cannot move
 */

public class Plant extends Lifeform {

	public static final int COLOUR = Color.GREEN.getRGB();
	private static final int TOTALPLANT = 2;
	private static final int MINCELL = 3;

	public Plant(Cell cell) {
		super(cell);
		setColour(COLOUR);
	}

	@Override
	public void survive() {
		// send seeds and help cross pollenate
		Cell cell = chooseACell(getEmptyCells());
		if (canSeed()) {
			cell.addLife(new Plant(cell));
		}
	}

	public int countNeighbourPlants() {
		int count = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Plant) {
				count++;
			}
		}
		return count;
	}

	public boolean canSeed() {
		int emptyCell = countEmptyCells();
		int plant = countNeighbourPlants();
		if (emptyCell >= MINCELL && plant >= TOTALPLANT) {
			return true;
		} else {
			return false;
		}
	}

}
