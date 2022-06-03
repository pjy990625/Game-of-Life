import java.awt.Color;
import java.util.ArrayList;

/* 
 * Shown as BLUE in a cell
 * Eat Herbivores, Carnivores, Plants
 * Give birth if there are at least 1 other Omnivore neighbors, at least 3 free neighboring cells, and 1 neighboring cells with food
 * Must eat within 5 moves
 */

public class Omnivore extends Animal {

    public static final int COLOUR = Color.BLUE.getRGB();

    public Omnivore(Cell cell) {
        super(cell);
        setColour(COLOUR);
    }

    @Override
    public void survive() {
        // give birth if they can
        giveBirth();
        // eat herbivores, carnivores and plants
        ArrayList<Cell> foundCells = findCells();
        if (foundCells.size() > 0) {
            Cell chosenCell = chooseACell(foundCells);
            if (chosenCell.getLifeform() instanceof Herbivore ||
                    chosenCell.getLifeform() instanceof Omnivore ||
                    chosenCell.getLifeform() instanceof Plant) {
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
            if (cells.getLifeform() instanceof Herbivore ||
                    cells.getLifeform() instanceof Carnivore ||
                    cells.getLifeform() instanceof Plant ||
                    cells.getLifeform() == null) {
                foundCells.add(cells);
            }
        }
        return foundCells;
    }

    private boolean canGiveBirth() {
        int numOfOmnivores = countOmnivores();
        int numOfCells = countEmptyCells();
        int numOfFood = countHerbivores() + countCarnivores() + countPlants();
        if (numOfOmnivores >= 1 && numOfCells >= 3 && numOfFood == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void giveBirth() {
        Cell cell = chooseACell(getEmptyCells());
        if (canGiveBirth()) {
            cell.addLife(new Omnivore(cell));
        }
    }

}
