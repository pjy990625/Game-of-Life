public abstract class Animal extends Lifeform{

    protected static final int MAXTURN = 5;
	protected int countLife = 5;

    public Animal(Cell cell) {
        super(cell);
    }

	protected void move(Cell moveFrom, Cell moveTo) {
		if (moveTo != null) {
			moveFrom.removeLife();
			moveTo.addLife(this);
		}
	}

	protected void eat() {
		countLife = MAXTURN;
	}
}
