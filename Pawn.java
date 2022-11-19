package lab2Problem3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pawn extends Piece{
	public boolean canPromote = false;

	public Pawn(boolean color, Position pos) {
		super(color, pos);
	}
	
	public boolean getPromote() {
		return canPromote;
	}
	@Override
	public boolean isLegalMove(Position endPos) {
		if(!super.basicIsLegal(endPos)) return false;
		boolean isLegalMove;

		boolean isMovingBackwards = 
				this.getColor()==true 
				? endPos.getX() < this.getPosition().getX() 
				: endPos.getX() > this.getPosition().getX();
		if (isMovingBackwards) return false;
		
		int xDiff = this.getPosition().deltaX(endPos);
		
		boolean isOnStartingPos =
				(this.getColor() == true && this.getPosition().getX() == 1)
	            || (this.getColor() == false && this.getPosition().getX() == 6);	
		
		if (isOnStartingPos) {
			if(Board.isSquareFree(new Position(endPos.getX() - 1, endPos.getY())) && this.getColor()==true)
				isLegalMove = endPos.getY() == this.getPosition().getY() && xDiff<= 2 && xDiff > 0;
			else if(Board.isSquareFree(new Position(endPos.getX() + 1, endPos.getY())) && this.getColor()==false)
				isLegalMove = endPos.getY() == this.getPosition().getY() && xDiff<= 2 && xDiff > 0;
			else
				isLegalMove = false;
		} else {
			isLegalMove = endPos.getY() == this.getPosition().getY() && xDiff == 1; 
		}
		boolean isOnLastPos = ((this.getColor()==true && endPos.getX() == 7) || this.getColor() == false && endPos.getX() == 0);
		if (isOnLastPos && isLegalMove) {
			canPromote = true;
		}
		return isLegalMove;
	}
	@Override
	public void movePiece(Position endPos) {
		super.movePiece(endPos);
		System.out.println("Moving pawn");
		if(this.canPromote) {
			System.out.println("Promoting");
			this.promote(this);
		}
	} 
	
	public void promote(Pawn pawn) {
		Position pawnPos = pawn.getPosition();
		InputStreamReader r=new InputStreamReader(System.in);    
		BufferedReader br=new BufferedReader(r); 
		System.out.println("Choose a piece to promote to: Q, B, R, N");
		String choice;
		try {
			choice = br.readLine();
			switch(choice) {
			case "Q":
				Board.addPiece(new Queen(this.getColor(), pawnPos), pawnPos);
				break;
			case "B":
				Board.addPiece(new Bishop(this.getColor(), pawnPos), pawnPos);
				break;
			case "R":
				Board.addPiece(new Rook(this.getColor(), pawnPos), pawnPos);
				break;
			case "N":
				Board.addPiece(new Knight(this.getColor(), pawnPos), pawnPos);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean canCapture(Position endPos) {
		if(!Board.isSquareFree(endPos) && (Math.abs(endPos.getX() - this.getPosition().getX()) == 1) && (Math.abs(endPos.getY() - this.getPosition().getY()) == 1)) 
			if(Board.getPiece(endPos).getColor() != this.getColor())
				return true;
		return false;
	}
	@Override
	public void capture(Position endPos) {
		if(this.canCapture(endPos)) {
			System.out.println("Capturing piece");
			Board.chessBoard[endPos.getX()][endPos.getY()] = Board.getPiece(this.getPosition());
			Board.removePiece(getPosition());
			
			this.setPosition(endPos);
			if((this.getColor()==true && this.getPosition().getX() == 7) || this.getColor() == false && this.getPosition().getX() == 0) {
				this.promote(this);
			}
		}
	}
	@Override
	public String toString() {
		if(this.getColor()) {
			return "♙";
		} else {
			return "♟";
		}
	}

}
