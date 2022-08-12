package group_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GroupChat {

	private static final String TERMINATE = "Exit";
	static volatile boolean finished = false;
	static String name;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("\n [ MultiCast-Host IP ] : ");
		String host = sc.next();
		System.out.println("\n [ Port ] : ");
		int port = sc.nextInt();
		name = args[0];
		try {
			InetAddress group = InetAddress.getByName(host);
			MulticastSocket socket = new MulticastSocket(port);

			socket.setTimeToLive(0); // For localhost only
			// socket.setTimeToLive(1); // For a subnet set

			socket.joinGroup(group);
			Thread t = new Thread(new ReadThread(socket, group, port));
			t.start();

			System.out.println(
					"Welcome " + name + " !! \n" + "You joined in a GroupChat . Start Typing messages ...  !!\n");
			System.out.println("[To Exit, please type exit]\n");

			while (true) {
				String message = sc.nextLine();
				if (message.equalsIgnoreCase(GroupChat.TERMINATE)) {
					finished = true;
					socket.leaveGroup(group);
					socket.close();
					break;
				}
				if (!message.equals("")) {
					message = name + " : " + message;
					byte[] buffer = message.getBytes();
					DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
					socket.send(datagram);
				}
			}
			sc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
