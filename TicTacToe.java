import java.util.Arrays;

public class TicTacToe {
	
	private static final char EMPTY_SPACE = '_';
	private static final char PLAYER_1_TOKEN = 'X';
	private static final char PLAYER_2_TOKEN = 'O';
	private char[][] board;
	private int turn;
	private static final char[] ROW_MAPPING = {'A', 'B', 'C'};
	private static final char[] ROW_MAPPING_LOWER_CASE = {'a', 'b', 'c'};
	
	
	
	public TicTacToe(){
		board = new char[3][3];
		for(int i = 0; i < 3; i++){
			Arrays.fill(board[i], EMPTY_SPACE);
		}
		turn = 0;
		
	}
	
	public String getBoard(){
		String rtn = "";
		String line;
		line = "~~~~~~~~~~~~~~";
		rtn += (line + "\n");
		line = "   1   2   3";
		rtn += (line + "\n");
		line = "A  " + board[0][0] + " ";
		for (int i = 1; i <= 2; i++){
			line += "| " + board[0][i] + " ";
		}
		rtn += (line + "\n");
		line = "  -----------";
		rtn += (line + "\n");
		
		line = "B  " + board[1][0] + " ";
		for (int i = 1; i <= 2; i++){
			line += "| " + board[1][i] + " ";
		}
		rtn += (line + "\n");
		line = "  -----------";
		rtn += (line + "\n");
		
		line = "C  " + board[2][0] + " ";
		for (int i = 1; i <= 2; i++){
			line += "| " + board[2][i] + " ";
		}
		rtn += (line + "\n");
		line = "~~~~~~~~~~~~~~";
		rtn += (line + "\n");
		return rtn;
		
	}
	
	private boolean isWin(char token){
		return isWinRow(token) || isWinCol(token) || isWinDiag(token);
	}
	
	private boolean isWinRow(char token){
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				char c = board[i][j];
				if (c == token){
					count++;
				}
			}
			if(count == 3){
				return true;
			} else {
				count = 0;
			}
		}
		return false;
	}
	
	private boolean isWinCol(char token){
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				char c = board[j][i];
				if (c == token){
					count++;
				}
			}
			if(count == 3){
				return true;
			} else {
				count = 0;
			}
		}
		return false;
	}
	
	private boolean isWinDiag(char token){
		int count = 0;
		
		for(int i = 0; i < 3; i++){
			char c = board[i][i];
			if (c == token){
				count++;
			}
		}
		
		if(count == 3){
			return true;
		} else {
			count = 0;
		}
		
		for(int i = 2; i >= 0; i--){
			char c = board[i][2 - i];
			if (c == token){
				count++;
			}
		}
		
		if(count == 3){
			return true;
		}
		return false;
	}
	
	private boolean isMoveLegal(int row, int col){
		if(row <= 2 && row >= 0 && col <= 2 && col >= 0){
			return board[row][col] == EMPTY_SPACE;
		}
		return false;
		
	}
	
	public boolean makeMove(int col, char row, int player){
		col--;
		int rowNum = -1;
		for(int i = 0; i < 3; i++){
			if(row == ROW_MAPPING[i] || row == ROW_MAPPING_LOWER_CASE[i]){
				rowNum = i;
			}
		}
		if(rowNum == -1){
			throw new IllegalArgumentException("Bad Row Read");
		}
		
		if(isMoveLegal(rowNum, col)){
			char token = (player == 1) ? PLAYER_1_TOKEN : PLAYER_2_TOKEN;
			board[rowNum][col] = token;
			turn++;
			return isWin(token);
		} else {
			throw new IllegalArgumentException("Bad Move Coords");
		}
		
		
		
	}
}
