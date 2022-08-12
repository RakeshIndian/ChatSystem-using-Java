package one_to_one;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadData implements Runnable {
	private Socket socket;
	private String name;
	public ReadData(Socket socket,String name) {
		this.socket = socket;
		this.name = name;
	}

	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String message;
			do {
				message = (String) dis.readUTF();
				System.out.println(message);
				System.out.print(name +" : ");
			} while (!message.equalsIgnoreCase("EXIT"));
		} catch (IOException e) {
			System.out.println("Chat Closed !");
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
