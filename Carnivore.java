import java.awt.Color;
import java.util.ArrayList;

/* 
 * Shown as RED in a cell
 * Eat Herbivores and Omnivores
 * Give birth if there are at least 1 other Carnivore neighbors, at least 3 free neighboring cells, and 2 neighboring cells with food (Herbivores or Omnivores)
 * Must eat within 5 moves
 */

public class Carnivore extends Animal {

    public static final int COLOUR = Color.RED.getRGB();

    public Carnivore(Cell cell) {
        super(cell);
        setColour(COLOUR);
    }

    @Override
    public void survive() {
        // give birth if they can
        giveBirth();
        // eat herbivores and omnivores
        ArrayList<Cell> foundCells = findCells();
        if (foundCells.size() > 0) {
            Cell chosenCell = chooseACell(foundCells);
            if (chosenCell.getLifeform() instanceof Herbivore
                    || chosenCell.getLifeform() instanceof Omnivore) {
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
                    cells.getLifeform() instanceof Omnivore ||
                    cells.getLifeform() == null) {
                foundCells.add(cells);
            }
        }
        return foundCells;
    }

    private boolean canGiveBirth() {
        int numOfCarnivores = countCarnivores();
        int numOfCells = countEmptyCells();
        int numOfFood = countHerbivores() + countOmnivores();
        if (numOfCarnivores >= 1 && numOfCells >= 3 && numOfFood == 2) {
            return true;
        } else {
            return false;
        }
    }

    private void giveBirth() {
        Cell cell = chooseACell(getEmptyCells());
        if (canGiveBirth()) {
            cell.addLife(new Carnivore(cell));
        }
    }

}
