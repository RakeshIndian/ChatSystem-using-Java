package one_to_one.server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import one_to_one.OneToOneChat;
import one_to_one.ReadData;

public class MyServer {
	private static ServerSocket serverSocket;
	private static Socket socket;
	static Boolean isConnected;
	static String name;

	public static void main(String[] args) {
		name = args[0];
		try {
			Scanner sc = new Scanner(System.in);
			isConnected = connectToClient(args[1]);
			if (isConnected) {
				System.out.println("Connected with Client");
				Thread t = new Thread(new ReadData(socket, name));
				t.start();

				System.out.println("Welcome " + name + " !! \n"
						+ "You Started in a Private Chat . Start Typing messages ...  !!\n");
				System.out.println("[To Exit, please type exit]\n");

				while (true) {
					String message = sc.nextLine();
					if (message.equalsIgnoreCase(OneToOneChat.TERMINATE)) {
						OneToOneChat.finished = true;
						socket.close();
						break;
					}
					if (!message.equals("")) {
						message = name + " : " + message;
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						dos.writeUTF(message);
						dos.flush();
					}
				}
			} else {
				System.out.println("Unable to connected with Client");
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	@Override
	public String toString() {
		return "Server [serverSocket=" + serverSocket + "]";
	}

	public MyServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Boolean connectToClient(String port) throws Exception {
		serverSocket = new ServerSocket(Integer.parseInt(port));
		System.out.println("Scanning Nearby Clients ...");
		socket = serverSocket.accept();
		if (socket != null) {
			return true;
		} else {
			System.out.println("Unable to connected with Client");
			return false;
		}
	}
}
