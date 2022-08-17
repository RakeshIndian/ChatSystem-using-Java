import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

import controller.UserController;
import group_chat.GroupChat;
import one_to_one.OneToOneChat;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("\n [ Name ] : ");
		String name = sc.nextLine();
		String choice = null;
		System.out.print("\n [ Email ] : ");
		String email = sc.nextLine();
		do {
			System.out.print("\n Are you already a user? [y/n] : ");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice!!");
		} while (true);
		if (choice.equalsIgnoreCase("Y")) {
			UserController.login(name,email);
		} else {
			UserController.signUp(name,email);
		};
		do {
			System.out.print("\n Do you want to go for Group Chat? [y/n] : ");
			choice = sc.next();
			if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
				break;
			else
				System.out.println("Invalid Choice!!");
		} while (true);
		UserController.start(choice);
		
//		if (choice.equalsIgnoreCase("Y")) {
//			GroupChat.main(new String[] { name });
//		} else {
//			OneToOneChat.main(new String[] { name });
//		};
		sc.close();
	}

}
