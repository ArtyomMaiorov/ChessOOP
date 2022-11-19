package lab2Problem3;

public class Position {
	private int x;
	private int y;
	public boolean isOccupied;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Position(String position) {
		this.y = position.charAt(0)-97; //column
		this.x = Character.getNumericValue(position.charAt(1)) - 1; //row
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int deltaX(Position other) {
		return Math.abs(this.getX() - other.getX());
	}
	public int deltaY(Position other) {
		return Math.abs(this.getY() - other.getY());
	}
	public boolean getIsOccupied() {
		return this.isOccupied;
	}
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public String toString() {
		return this.x + " " + this.y;
	}
	
}
