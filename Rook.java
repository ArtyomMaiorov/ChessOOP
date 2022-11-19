package lab2Problem3;

public class Rook extends Piece{
	private boolean hasMoved = false;

	public Rook(boolean color, Position pos) {
		super(color, pos);
	}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) return false;
		
		if(!Board.isHorizVertFree(this.getPosition(), endPos)) 
			return false;
		
		if(this.getPosition().deltaX(endPos)==0 && this.getPosition().deltaY(endPos) != 0) {
			this.hasMoved = true;
			return true;
		}
		else if(this.getPosition().deltaY(endPos)==0 && this.getPosition().deltaX(endPos) != 0)
			{
			this.hasMoved = true;
			return true;
			}
		else
			return false;
	}
	
	public boolean hasMoved() {
		return this.hasMoved;
	}
	@Override
	public String toString() {
		if(this.getColor()) {
			return "♖";
		} else {
			return "♜";
		}
	}		
}