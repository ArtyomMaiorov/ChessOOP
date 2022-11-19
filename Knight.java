package lab2Problem3;

public class Knight extends Piece {

	public Knight(boolean color, Position pos) {
		super(color, pos);
		}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) return false;
		int val = this.getPosition().deltaX(endPos)*this.getPosition().deltaY(endPos);	
		return (val==2);
	}
	@Override
	public String toString() {
		if(this.getColor()) {
			return "♘";
		} else {
			return "♞";
		}
	}
}
