package lab2Problem3;

public class Board {
	//private Set<Piece> pieces = new HashSet<>();
	public static Piece[][] chessBoard = new Piece[8][8];

	
	public Board() {
		chessBoard[0][4] = new King(true, new Position("e1"));
		chessBoard[7][4] = new King(false, new Position("e8"));
		chessBoard[0][3] = new Queen(true, new Position("d1"));
		chessBoard[7][3] = new Queen(false, new Position("d8"));
		chessBoard[0][2] = new Bishop(true, new Position("c1"));
		chessBoard[0][5] = new Bishop(true, new Position("f1"));
		chessBoard[7][2] = new Bishop(false, new Position("c8"));
		chessBoard[7][5] = new Bishop(false, new Position("f8"));
		chessBoard[0][1] = new Knight(true, new Position("b1"));
		chessBoard[0][6] = new Knight(true, new Position("g1"));
		chessBoard[7][1] = new Knight(false, new Position("b8"));
		chessBoard[7][6] = new Knight(false, new Position("g8"));
		chessBoard[0][0] = new Rook(true, new Position("a1"));
		chessBoard[0][7] = new Rook(true, new Position("h1"));
		chessBoard[7][0] = new Rook(false, new Position("a8"));
		chessBoard[7][7] = new Rook(false, new Position("h8"));
		for(int i = 0; i < 8; i++) {
			chessBoard[1][i] = new Pawn(true, new Position(1, i));
			chessBoard[6][i] = new Pawn(false, new Position(6, i));
		}
	}
	public static void addPiece(Piece p, Position endPos) {
		chessBoard[endPos.getX()][endPos.getY()] = p;
	}
	public static void removePiece(Position pos) {
		chessBoard[pos.getX()][pos.getY()] = null;
	}
	public static Piece getPiece(Position pos) {
		return chessBoard[pos.getX()][pos.getY()];
	}
	public static King getWhiteKing() {
		Piece king = null;
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j++) {
				if (chessBoard[i][j] instanceof King && chessBoard[i][j].getColor())
					king = chessBoard[i][j];
			}
		}
		return (King) king;
	}
	public static King getBlackKing() {
		Piece king = null;
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j++) {
				if (chessBoard[i][j] instanceof King && !chessBoard[i][j].getColor())
					king = chessBoard[i][j];
			}
		}
		return (King) king;
	}
	public static boolean isChecked(King k) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Piece p = Board.chessBoard[i][j];
				if(p != null && p.getColor()!=k.getColor()) {
					if(p.canCapture(k.getPosition()) && !k.isChecked()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isDiagonalFree(Position pos, Position endPos) {
		int startX = 0;
		int startY = 0;
		int endX = 1;
		
		if(pos.getX() > endPos.getX()) {
			startX = endPos.getX();
			endX = pos.getX();
		} else if(pos.getX() < endPos.getX()) {
			startX = pos.getX();
			endX = endPos.getX();
		} else return false;
		if(pos.getY() > endPos.getY()) {
			startY = endPos.getY();
		} else if(pos.getY() < endPos.getY()) {
			startY = pos.getY();
		} else return false;
		startX++;
		startY++;
		
		for(; startX < endX; startX++, startY++) {
			if(Board.chessBoard[startX][startY]!=null) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isHorizVertFree(Position pos, Position endPos) {
		boolean isHorizontal = pos.getX() == endPos.getX();
		boolean isVertical = pos.getY() == endPos.getY();
		
		int smallerPos;
		int biggerPos;
		
		if(!isHorizontal && !isVertical) 
			return false;		
		
		if (isHorizontal) {
			if (pos.getY() > endPos.getY()){
				smallerPos = endPos.getY();
				biggerPos = pos.getY();
			}
			else if(endPos.getY() > pos.getY()) {
				smallerPos = pos.getY();
				biggerPos = endPos.getY();
			} else
				return false;
			smallerPos++;
			for(; smallerPos < biggerPos; smallerPos++) {
				if(chessBoard[pos.getX()][smallerPos] != null)
					return false;
			}
			return true;
			
		} else if(isVertical) {
			if(pos.getX() > endPos.getX()) {
				smallerPos = endPos.getX();
				biggerPos = pos.getX();
			} else if(endPos.getX() > pos.getX()) {
				smallerPos = pos.getX();
				biggerPos = endPos.getX();
			} else
				return false;
			smallerPos++;
			for(; smallerPos < biggerPos; smallerPos++) {
				if(chessBoard[smallerPos][pos.getY()] != null) 
					return false;				
			}
			return true;			
		}
		return true;
	}
	
	public static boolean isSquareFree(Position endPos) {
		if(chessBoard[endPos.getX()][endPos.getY()] == null)
			return true;
		else
			return false;
	}
	//42 green 43 cyan 44 dark blue 45 purple 46 light blue 47 light gray 
	public void displayBoard() {
		System.out.println("   a  b  c  d  e  f  g  h");
		for(int i = 0; i < 8; i++) {
			System.out.print(i+1 + " ");
			for(int j = 0; j < 8; j++) {
				if(Board.chessBoard[i][j] == null) {
					if((i+j)%2==0)
						System.out.print("\u001B[46m" +"□" + "\u001B[0m");
					else 
						System.out.print("\u001B[47m" +"□" + "\u001B[0m");
					continue;
				}
				if((i+j)%2==0)
					System.out.print( "\u001B[46m" + Board.chessBoard[i][j] + "\u001B[0m");
				else
					System.out.print("\u001B[47m" + Board.chessBoard[i][j] + "\u001B[0m");
			}
			System.out.print(" ");
			System.out.println(i + 1);
		}
		System.out.println("   a  b  c  d  e  f  g  h");

	}	
}
