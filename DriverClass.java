package lab2Problem3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// This java chess programm is written by Artyom Maiorov, KBTU 2nd year student as a part of OOP course to demonstrate usage of basic OOP principles. 
// tg @kisoyee. Feel free to distribute
// Pieces mechanics, capturing logic, pawn promotion, castling and check mechanism are implemented.

public class DriverClass {
	public static void main(String[] args) throws IOException {
		Board b = new Board();
		Board.removePiece(new Position("a2"));
		Board.removePiece(new Position("b2"));
		Board.removePiece(new Position("d2"));
		Board.removePiece(new Position("c2"));
		Board.removePiece(new Position("a1"));
		InputStreamReader r=new InputStreamReader(System.in);    
		BufferedReader br=new BufferedReader(r);   

		while(true) {
			if(Board.isChecked(Board.getBlackKing())) {
				System.out.println("Black king is in check");
				King.setRedSymbol(false);
				
			} else if(Board.isChecked(Board.getWhiteKing())) {
				System.out.println("White king is in check");
				King.setRedSymbol(true);
			}
			if(!Board.isChecked(Board.getBlackKing()))
				King.setDefaultSymbol(false);
			else if(!Board.isChecked(Board.getWhiteKing()))
				King.setDefaultSymbol(true);
			b.displayBoard();

		    System.out.println("Enter 1 to make a move, 2 to capture, 3 to castle, 4 to quit");  
		    String command = br.readLine();
		    
		    if(command.equals("1")) {
		    	System.out.println("Enter position to move from and to move to");		    	
		    	String str = br.readLine();
		    	String[] strings = str.split("\\s+");
		    	Position pos1 = new Position(strings[0]);
		    	Position pos2 = new Position(strings[1]);
		    	
		    	Board.chessBoard[pos1.getX()][pos1.getY()].movePiece(pos2);
		    	b.displayBoard();
		    } else if(command.equals("2")) {
		    	System.out.println("Enter position to move from and to move to");
		    	String str = br.readLine();
		    	String[] strings = str.split("\\s+");
		    	Position pos1 = new Position(strings[0]);
		    	Position pos2 = new Position(strings[1]);
		    	
		    	Board.chessBoard[pos1.getX()][pos1.getY()].capture(pos2);
		    	b.displayBoard();
		    } else if(command.equals("3")) {
		    	System.out.println("White king - W, Black king - B");
		    	String color = br.readLine();
		    	if(color.equals("W")) {
		    		System.out.println("Which side to castle, Q or K?");
			    	String castleType = br.readLine();
			    	if(castleType.equals("Q") ) {
			    		Board.getWhiteKing().castle("Q");
			    	} else if (castleType.equals("K")) {
			    		Board.getWhiteKing().castle("K");
			    	}
		    	} else if(color.equals("B")) {
		    		System.out.println("Which side to castle, Q or K?");
			    	String castleType = br.readLine();
			    	if(castleType. equals("Q") ) {
			    		Board.getBlackKing().castle("Q");
			    	} else if (castleType.equals("K")) {
			    		Board.getBlackKing().castle("K");
			    	}
		    	}
		    }
		    else if(command.equals("4"))  {
		    	break;
		    } else {
		    	System.out.println("Wrong command");
		    	continue;
		    }	    
		}					
	}	
}