package lab2Problem3;

public class King extends Piece {
	private boolean hasMoved = false;
	private boolean isChecked;
	private static String whiteSymbol = "♔";
	private static String blackSymbol = "♚";
	public static final String ANSI_RED = "\u001B[31m";
	
	public King(boolean color, Position pos) {
		super(color, pos);
	}
	public static void setRedSymbol(boolean color) {
		if(color) {
			whiteSymbol = "\u001B[31m" +"♔" + "\u001B[0m";
		} else if(!color) {
			blackSymbol = "\u001B[31m" +"♚" + "\u001B[0m";
		}
	}
	public static void setDefaultSymbol(boolean color) {
		if(color)
			whiteSymbol = "♔";
		else
		blackSymbol = "♚";
	}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) return false;

		int x = this.getPosition().deltaX(endPos);
		int y = this.getPosition().deltaY(endPos);
		
		if ((x > 1 || y > 1)||(x == 0 && y == 0))
			return false;
		if(!hasMoved) {
			hasMoved = true;
		}
		return true;
	}
	
	public void castle(String side) {
		if(side.equals("Q")) {
			Position rookPos = this.getColor() ? new Position("a1") : new Position("a8");
			Rook rook = (Rook) Board.getPiece(rookPos);
			
			if((rook instanceof Rook) && (!this.hasMoved && !rook.hasMoved() && Board.isHorizVertFree(rookPos, this.getPosition()))){
	    		System.out.println("Castling to Queen's side");
	    		
				Position kingEndPos = this.getColor() ? new Position("c1") : new Position("c8");
				Position rookEndPos = this.getColor() ? new Position("d1") : new Position("d8");
				//move rook
				Board.chessBoard[rookEndPos.getX()][rookEndPos.getY()] = Board.getPiece(rookPos);
				Board.removePiece(rookPos);
				rook.setPosition(rookEndPos);
				
				//move king
				Board.chessBoard[kingEndPos.getX()][kingEndPos.getY()] = Board.getPiece(this.getPosition());
				Board.removePiece(getPosition());
				this.setPosition(kingEndPos);		
 			} else System.out.println("Can't castle");
			
		} else if(side.equals("K")) {
			Position rookPos = this.getColor() ? new Position("h1") : new Position("h8");
			Rook rook = (Rook) Board.getPiece(rookPos);
			if((rook instanceof Rook) && (!this.hasMoved && !rook.hasMoved() && Board.isHorizVertFree(rookPos, this.getPosition()))){
	    		System.out.println("Castling to King's side");
				Position kingEndPos = this.getColor() ? new Position("g1") : new Position("g8");
				Position rookEndPos = this.getColor() ? new Position("f1") : new Position("f8");
				
				//move rook
				Board.chessBoard[rookEndPos.getX()][rookEndPos.getY()] = Board.getPiece(rookPos);
				Board.removePiece(rookPos);
				rook.setPosition(rookEndPos);
				
				//move king
				Board.chessBoard[kingEndPos.getX()][kingEndPos.getY()] = Board.getPiece(this.getPosition());
				Board.removePiece(getPosition());
				this.setPosition(kingEndPos);
 			} else {
 				System.out.println("Can't castle");
 			}
		}
	}
	
	public boolean hasMoved() {
		return this.hasMoved;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String toString() {		
		if(this.getColor()) {
			return whiteSymbol;
		} else {
			return blackSymbol;
		}
	}	
}
