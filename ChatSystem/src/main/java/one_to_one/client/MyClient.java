package one_to_one.client;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import one_to_one.OneToOneChat;
import one_to_one.ReadData;

public class MyClient {
	private static String name;
	private static Socket socket;

	public static void main(String[] args) {
		name = args[0];
		try {
			Scanner sc = new Scanner(System.in);
			boolean isConnected = connectToServer(args[1],args[2]);
			if (isConnected) {
				System.out.println("Connected with Server");
				Thread t = new Thread(new ReadData(socket, name));
				t.start();

				System.out.println("Welcome " + name + " !! \n"
						+ "You joined in a Private Chat . Start Typing messages ...  !!\n");
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
				System.out.println("Unable to connected with Server");
			}
			sc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static Boolean connectToServer(String host, String port) throws Exception {
		socket = new Socket(host, Integer.parseInt(port));
		if (socket != null)
			return true;
		return false;
	}
}