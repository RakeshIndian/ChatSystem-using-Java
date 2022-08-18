package group_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.Group;
import domain.User;
import service.GroupService;

public class GroupChat {

	private static final String TERMINATE = "Exit";
	static volatile boolean finished = false;
	static String name, host, port="2222";
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n [ MultiCast-Host IP ] : ");
		host = sc.next();
		System.out.println("\n [ Port ] : ");
		port = sc.next();
		name = args[0];
		connect();
		sc.close();
	}

	public static void start(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		name = user.getName();
		String choice = "";
		do {
			System.out.print("Do you want to join a group? [y/n] : ");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice!");
		} while (true);

//		port = "2222";
		if (choice.equalsIgnoreCase("Y"))
			joinInViaGroupName();
		else
			signup(user);

	}

	private static void signup(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("[ Enter Group name ] : ");
		String gName = sc.next();
		System.out.println(user);
		Group g = GroupService.createGroup(gName, user.getName() ,user.getIp());
		host = g.getgIp();
		connect();
	}

	private static void joinInViaGroupName() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("[ Enter Group name ] : ");
		String gName = sc.next();
		List<Group> groupList = GroupService.getGroupByName(gName);
		
		if(groupList.isEmpty()) {
			String choice = "";
			do {
			System.out.print("No such group present.\nWant to search for groups by User? [y/n]");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice!");
			}while(true);
			if (choice.equalsIgnoreCase("N"))
				joinInViaGroupName();
			else
				joinInViaUser();
			return;
		}
		System.out.println("-- List of Groups --");
		int i=1;
		for(Group x : groupList)
			System.out.println(""+ i++ +"  "+ x);
		int selected ;
		do {
			System.out.print("Please select the group : ");
			selected = sc.nextInt();
			if(selected <= 0 || selected > groupList.size())
				System.out.println("Invalid choice");
			else break;
		}while(true);
		Group g = groupList.get(--selected);
		host = g.getgIp();
		connect();		
	}

	private static void joinInViaUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("[ Enter Admin name ] : ");
		String gName = sc.nextLine();
		List<Group> groupList = GroupService.getGroupByCreator(gName);
		
		if(groupList.isEmpty()) {
			String choice = "";
			do {
			System.out.print("No such group present.\n Want to retry? [y/n]");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice!");
			}while(true);
			if (choice.equalsIgnoreCase("Y")) joinInViaUser();
			return;
		}
		System.out.println("-- List of Groups --");
		int i=1;
		for(Group x : groupList)
			System.out.println(""+ i++ +"  "+ x);
		int selected ;
		do {
			System.out.print("Please select the group : ");
			selected = sc.nextInt();
			if(selected <= 0 || selected > groupList.size())
				System.out.println("Invalid choice");
			else break;
		}while(true);
		Group g = groupList.get(--selected);
		host = g.getgIp();
		connect();
	}

	private static void connect() {
		try {
			InetAddress group = InetAddress.getByName(host);
			MulticastSocket socket = new MulticastSocket(Integer.parseInt(port));

//			socket.setTimeToLive(0); // For localhost only
			 socket.setTimeToLive(1); // For a subnet set

			socket.joinGroup(group);
			Thread t = new Thread(new ReadThread(socket, group,Integer.parseInt(port)));
			t.start();

			System.out.println(
					"Welcome " + name + " !! \n" + "You joined in a GroupChat . Start Typing messages ...  !!\n"
							+ "host ip: "+host+":"+port);
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
					DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group,Integer.parseInt(port));
					socket.send(datagram);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
