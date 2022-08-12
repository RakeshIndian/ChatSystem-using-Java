package one_to_one;

import java.util.Scanner;

import one_to_one.client.MyClient;
import one_to_one.server.MyServer;

public class OneToOneChat {

	public static final String TERMINATE = "Exit";
	public static volatile boolean finished = false;
	static String name;

	public static void main(String[] args) {
		name = args[0];
		Scanner sc = new Scanner(System.in);
		String choice = "",port="";
		do {
			System.out.print("\nAre you the host? [y/n] : ");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice !!");
		} while (true);
		
		if(choice.equalsIgnoreCase("Y")) {

			System.out.print("\n[ Port ] : ");
			port = new String(sc.next());
			MyServer.main(new String[] {name,port});
		}
		else {
			System.out.print("\n[ Host Ip ] : ");
			String ip = new String(sc.next());
			System.out.print("\n[ Port ] : ");
			port = new String(sc.next());
			MyClient.main(new String[] {name,ip,port} );
		}
	sc.close();	
	}

}
