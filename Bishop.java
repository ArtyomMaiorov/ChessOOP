package lab2Problem3;

public class Bishop extends Piece{

	public Bishop(boolean color, Position pos) {
		super(color, pos);
	}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) {
			return false;			
		}
		if (Math.abs(this.getPosition().deltaX(endPos)) == Math.abs(this.getPosition().deltaY(endPos)))
			if(Board.isDiagonalFree(this.getPosition(), endPos)) {
				return true;	
			}
			else 
				return false;
		else 
			return false;				
	}
	@Override
	public String toString() {
		if(this.getColor()) {
			return "♗";
		} else {
			return "♝";
		}
	}	
}
