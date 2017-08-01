import java.net.*;
import java.io.*;
import java.util.*;

public class TTTServer{
	public static void main(String[] args){
		int turn = 1;
		int port = Integer.parseInt(args[0]);
		Random rand = new Random();
		
		TicTacToe tttGame = new TicTacToe();
		System.out.println("Waiting for client to connect...");
		try (
			ServerSocket servSocket = new ServerSocket(port);
			Socket clientSocket = servSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			PrintWriter stdOut = new PrintWriter(System.out, true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			)
		{
			out.println("You are connected to the server!");
			out.println("Get ready to play Tic Tac Toe!");
			out.println(tttGame.getBoard());
			
			stdOut.println("Client has connected!");
			stdOut.println("Get ready to play Tic Tac Toe!");
			stdOut.println(tttGame.getBoard());
			
			
			String move;
			int player = -1;
			boolean win = false;
			boolean serverPlayerOne = rand.nextBoolean();
			int serverPlayerNumber = (serverPlayerOne ? 1 : 2);
			int clientPlayerNumber = (serverPlayerOne ? 2 : 1);
			int turnModTest = (serverPlayerOne ? 0 : 1);
			
			while(turn <= 9 && !win){
				
				move = "";
				if(turn % 2 == turnModTest){
					out.println("It is your turn, what is your move? (column number then row letter, no spaces)");
					stdOut.println("Waiting for client move...");
					player = clientPlayerNumber;
					move = in.readLine();
					
					stdOut.println("Client move was: " + move);
					out.println("Your move was: " + move);
				} else {
					stdOut.println("It is your turn, what is your move? (column number then row letter, no spaces)");
					out.println("Waiting for server move...");
					player = serverPlayerNumber;
					move = stdIn.readLine();
					stdOut.println("Your move was: " + move);
					out.println("Server move was: " + move);
				}
				move = move.trim();
				int col = Character.getNumericValue(move.charAt(0));
				char row = move.charAt(1);
				win = tttGame.makeMove(col, row, player);
				out.println(tttGame.getBoard());
				stdOut.println(tttGame.getBoard());
				
				turn++;
				
			}
			String endMsg = (win) ? ("Player " + player + ", the " + ((player == 1) ? "server" : "client") + ", has won!") : "The game was a draw!";
			out.println(endMsg);
			stdOut.println(endMsg);
			
			
		} catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
		
		
	}
}