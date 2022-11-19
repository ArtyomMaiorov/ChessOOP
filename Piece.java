package lab2Problem3;
abstract public class Piece {
	private boolean color; // is white
	private Position pos;
	
	public Piece(boolean color, Position pos) {
		this.color = color;
		this.pos = pos;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	public void setPosition(Position pos) {
		this.pos = pos;
	}
	
	public abstract boolean isLegalMove(Position endPos) ;
	
	public boolean basicIsLegal(Position endPos) {
		if(endPos.getX()==pos.getX() && endPos.getY()==pos.getY()) return false;
		if(endPos.getX() < 0 || endPos.getX() > 7 || endPos.getY() < 0 || endPos.getY() > 7 || pos.getX() < 0 || pos.getX() > 7 || pos.getY() < 0 || pos.getY() > 7) return false;
		return true;
	}
	
	public void movePiece(Position endPos) {
		if (isLegalMove(endPos) && Board.isSquareFree(endPos)) {
			System.out.println("Moving piece");
			Board.chessBoard[endPos.getX()][endPos.getY()] = Board.getPiece(this.pos);
			Board.removePiece(getPosition());
			this.setPosition(endPos);
		} else if(!Board.isSquareFree(endPos)) {
			System.out.println("Try to capture");
		} else {
			System.out.println("Move is illegal");
		}
	}	
	public boolean canCapture(Position endPos) {
		if(isLegalMove(endPos) && !Board.isSquareFree(endPos))
			if(Board.getPiece(endPos).color != this.getColor())
				return true;
		return false;
	}
	
	public void capture(Position endPos) {	
		if(this.canCapture(endPos)){
			System.out.println("Capturing piece");
			Board.chessBoard[endPos.getX()][endPos.getY()] = Board.getPiece(this.pos);
			Board.chessBoard[this.getPosition().getX()][this.getPosition().getY()] = null;
			this.setPosition(endPos);
		}
		else {
			System.out.println("Can't capture");
		}
	}
	
	public  String toString() {
		return "color" + color + "pos" + pos.toString();
	}	
}