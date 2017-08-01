
public class TicTacTester {

	public static void main(String[] args){
		TicTacToe game1 = new TicTacToe();
		System.out.println(game1.getBoard());
		game1.makeMove(3,'A',1);
		System.out.println(game1.getBoard());
		
	}
}
