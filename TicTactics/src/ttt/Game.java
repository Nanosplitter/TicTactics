public class Game {

	private BigBoard mainBoard;
	public Algorithm x;
	public Algorithm o;
	private int turn = 0;
	private Move xMove;
	private Move oMove;
	public String winMessage;
	
	public Game(Algorithm playerX, Algorithm playerO) {
		x = playerX;
		o = playerO;
		mainBoard = new BigBoard();
		winMessage = "";
	}
	
	public void playGame() {
		while (true) {
			if (turn == 0) {
				xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), -1, mainBoard.getFairBoards());
				mainBoard.makeMove('X', xMove);
				turn++;
			} else if (turn % 2 != 0) {
				if (!mainBoard.smallBoards.get(xMove.sIndex).checkForWin().equals("NOT COMPLETE")) {
					oMove = o.getMove(mainBoard.makeBoardForPlayer('O'), -1, mainBoard.getFairBoards());
				} else {
					oMove = o.getMove(mainBoard.makeBoardForPlayer('O'), xMove.sIndex, mainBoard.getFairBoards());
				}
				
				mainBoard.makeMove('O', oMove);
				
				winMessage = mainBoard.checkForWin();
				
				turn++;
				
			} else {
				if (!mainBoard.smallBoards.get(oMove.sIndex).checkForWin().equals("NOT COMPLETE")) {
					xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), -1, mainBoard.getFairBoards());
				} else {
					xMove = x.getMove(mainBoard.makeBoardForPlayer('X'), oMove.sIndex, mainBoard.getFairBoards());
				}
				
				mainBoard.makeMove('X', xMove);
				
				winMessage = mainBoard.checkForWin();
				
				turn++;
			}
			
			if (winMessage.equals("X")) {
				x.reportResult(1, turn);
				o.reportResult(-1, turn);
				break;
			}
			
			if (winMessage.equals("O")) {
				x.reportResult(-1, turn);
				o.reportResult(1, turn);
				break;
			}
			
			if (winMessage.equals("TIE")) {
				x.reportResult(0, turn);
				o.reportResult(0, turn);
				break;
			}
		}
	}

}
