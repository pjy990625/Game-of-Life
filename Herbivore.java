import java.awt.Color;
import java.util.ArrayList;

/* 
 * Shown as yellow in a cell
 * Move into cells randomly
 * Eat plants found in cell if any, die if they don't find food within 5 turns
 */

public class Herbivore extends Animal {

	public static final int COLOUR = Color.YELLOW.getRGB();

	public Herbivore(Cell cell) {
		super(cell);
		setColour(COLOUR);
	}

	@Override
	public void survive() {
		// give birth if they can
		giveBirth();
		// eat plants found
		ArrayList<Cell> foundCells = findCells();
		if (foundCells.size() > 0) {
			Cell chosenCell = chooseACell(foundCells);
			if (chosenCell.getLifeform() instanceof Plant) {
				move(cell, chosenCell);
				eat();
			} else if (chosenCell.getLifeform() == null) {
				move(cell, chosenCell);
				countLife--;
			}
		} else {
			countLife--;
		}
		if (countLife < 0) {
			cell.removeLife();
		}
	}

	private ArrayList<Cell> findCells() {
		ArrayList<Cell> foundCells = new ArrayList<Cell>();
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Plant ||
					cells.getLifeform() == null) {
				foundCells.add(cells);
			}
		}
		return foundCells;
	}
	
	private boolean canGiveBirth() {
		int numOfHerbivores = countHerbivores();
		int numOfCells = countEmptyCells();
		int numOfPlants = countPlants();
		if (numOfHerbivores >= 1 && numOfCells >= 2 && numOfPlants >= 2) {
			return true;
		} else {
			return false;
		}
	}

	private void giveBirth() {
		Cell cell = chooseACell(getEmptyCells());
		if (canGiveBirth()) {
			cell.addLife(new Herbivore(cell));
		}
	}
}
