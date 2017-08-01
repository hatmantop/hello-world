import java.net.*;
import java.io.*;

public class TTTClient{
	
	public static void main(String[] args){
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		
		try (
            Socket tttSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(tttSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(tttSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String message;
            while ((message = in.readLine()) != null) {
				if(message.equals("It is your turn, what is your move? (column number then row letter, no spaces)")){
					System.out.println(message);
					out.println(stdIn.readLine());
					System.out.println(in.readLine());
				} else{
					System.out.println(message);
				}
                
                
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
	}
}