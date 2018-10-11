package ttt;

public class Game {

	BigBoard mainBoard;
	Algorithm x;
	Algorithm o;
	int turn = 0;
	Move xMove;
	Move oMove;
	String winMessage;
	
	public Game(Algorithm playerX, Algorithm playerO) {
		x = playerX;
		o = playerO;
		mainBoard = new BigBoard();
	}
	
	public void playGame() {
		GAMELOOP: while (true) {
			if (turn == 0) {
				xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), -1, mainBoard.getFairBoards());
				mainBoard.makeMove('X', xMove);
				turn++;
			} else if (turn % 2 != 0) {
				if (mainBoard.smallBoards.get(xMove.sIndex).checkForWin() != "NOT COMPLETE") {
					oMove = o.getMove(mainBoard.makeBoardForPlayer('O'), -1, mainBoard.getFairBoards());
				} else {
					oMove = o.getMove(mainBoard.makeBoardForPlayer('O'), xMove.sIndex, mainBoard.getFairBoards());
				}
				
				mainBoard.makeMove('O', oMove);
				
				winMessage = mainBoard.checkForWin();
				
				turn++;
				
			} else {
				if (mainBoard.smallBoards.get(oMove.sIndex).checkForWin() != "NOT COMPLETE") {
					xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), -1, mainBoard.getFairBoards());
				} else {
					xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), oMove.sIndex, mainBoard.getFairBoards());
				}
				
				mainBoard.makeMove('X', xMove);
				
				winMessage = mainBoard.checkForWin();
				
				turn++;
			}
			
			if (winMessage == "X") {
				x.reportResult(1, turn);
				o.reportResult(-1, turn);
				break GAMELOOP;
			}
			
			if (winMessage == "O") {
				x.reportResult(-1, turn);
				o.reportResult(1, turn);
				break GAMELOOP;
			}
			
			if (winMessage == "TIE") {
				x.reportResult(0, turn);
				o.reportResult(0, turn);
				break GAMELOOP;
			}
		}
	}

}
