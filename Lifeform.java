import java.awt.Color;
import java.util.ArrayList;

public abstract class Lifeform {

    private Color color;
	protected Cell cell;
	
	public Lifeform(Cell c) {
	    cell = c;
    }
	
    protected void setColour(int rgb) {
        color = new Color(rgb);
    }
    
    protected Color getColour() {
        return color;
    }
	
	public abstract void survive();
	
	protected Cell getCell() {
	    return cell;
	}
	
	public ArrayList<Cell> getAdjacentCells() {
		ArrayList<Cell> adjCells = new ArrayList<Cell>();
		for (int row = cell.getXCord() - 1; row <= cell.getXCord() + 1; row++) {
			for (int col = cell.getYCord() - 1; col <= cell.getYCord() + 1; col++) {
				if ((row >= 0 && row <= cell.getWorld().getRow() - 1) &&
						(col >= 0 && col <= cell.getWorld().getColumn() - 1) ) {
					if (cell.getWorld().getCellAt(row, col) != cell) {
					    adjCells.add(cell.getWorld().getCellAt(row, col));
					}
				}
			}
		}
		return adjCells;
	}
	
	public ArrayList<Cell> getEmptyCells() {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() == null) {
				emptyCells.add(cells);
			}
		}
		return emptyCells;
	}
	
	public int countEmptyCells() {
		int count = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() == null) {
				count++;
			}
		}
		return count;
	}
	
	public Cell chooseACell(ArrayList<Cell> cells) {
		int numOfCells = cells.size();
		if (numOfCells == 0) {
			return null;
		} else {
			int index = RandomGenerator.nextNumber(numOfCells);
			return cells.get(index);
		}
	}

	public int countHerbivores() {
		int numOfHerbivores = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Herbivore) {
				numOfHerbivores++;
			}
		}
 		return numOfHerbivores;
	}

	public int countPlants() {
		int numOfPlants = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Plant) {
				numOfPlants++;
			}
		}
 		return numOfPlants;
	}

	public int countCarnivores() {
		int numOfCarnivores = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Carnivore) {
				numOfCarnivores++;
			}
		}
 		return numOfCarnivores;
	}

	public int countOmnivores() {
		int numOfOmnivores = 0;
		for (Cell cells : getAdjacentCells()) {
			if (cells.getLifeform() instanceof Omnivore) {
				numOfOmnivores++;
			}
		}
 		return numOfOmnivores;
	}
}
