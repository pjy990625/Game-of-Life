import java.util.ArrayList;

/*
 * Holds cells
 */

public class World {

	private int height;
	private int width;
	private Cell cell[][];

	public World(int w, int h) {
		height = h;
		width = w;
		cell = new Cell[w][h];
	}

	public int getRow() {
		return width;
	}

	public int getColumn() {
		return height;
	}

	public Cell getCellAt(int row, int col) {
		return cell[row][col];
	}

	public void init() {
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				cell[row][col] = new Cell(this, row, col);
				int num = RandomGenerator.nextNumber(99);
				if (num >= 80)
					cell[row][col].addLife(new Herbivore(cell[row][col]));
				else if (num >= 60)
					cell[row][col].addLife(new Plant(cell[row][col]));
				else if (num >= 50)
					cell[row][col].addLife(new Carnivore(cell[row][col]));
				else if (num >= 45)
					cell[row][col].addLife(new Omnivore(cell[row][col]));
				cell[row][col].paintCell();
			}
		}
	}

	public ArrayList<Lifeform> getAllLifeforms() {
		ArrayList<Lifeform> allLife = new ArrayList<Lifeform>();
		Lifeform life = null;
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				life = cell[row][col].getLifeform();
				if (life != null) {
					allLife.add(life);
				}
			}
		}
		return allLife;
	}

	public void update() {
		for (Lifeform life : getAllLifeforms()) {
			life.survive();
		}
		paint();
	}

	public void paint() {
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				getCellAt(row, col).paintCell();
			}
		}
	}

}
