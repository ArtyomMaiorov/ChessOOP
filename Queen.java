package lab2Problem3;

public class Queen extends Piece{

	public Queen(boolean color, Position pos) {
		super(color, pos);
	}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) return false;
		
		if(new Bishop(true, this.getPosition()).isLegalMove(endPos) || new Rook(true, this.getPosition()).isLegalMove(endPos))
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		if(this.getColor()) {
			return "♕";
		} else {
			return "♛";
		}
	}	
}