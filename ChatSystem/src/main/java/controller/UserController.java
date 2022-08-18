package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

import domain.User;
import group_chat.GroupChat;
import one_to_one.OneToOneChat;
import service.UserService;

public class UserController {
	private static User user;
	private static Scanner sc = new Scanner(System.in);
	private static UserService userService = new UserService();

	public static User login(String name,String email) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub

		user = userService.getUserByEmail(email);
//		System.out.println(user+"\n"+email);
		if (!user.getEmail().equals(email)) {
			System.out.println("Invalid Email. Email not registered!");
			String choice = new String();
			do {
				System.out.print("Do you want to register ? [y/n]");
				choice = sc.next();
				if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
					break;
				else
					System.out.println("Invalid Choice!");
			} while (true);
			if (choice.equalsIgnoreCase("Y"))
				return signUp(name,email);
			else
				return login(name,email);
		}
		String password = new String();
		for (int i = 0; i < 3; i++) {
			System.out.print("\n [ Password ] : ");
			password = sc.nextLine();
			if (user.getPassword().equals(password))
				return user;
			System.out.println("Incorrect Password");
		}
		return user;
	}

	public static User signUp(String name,String email) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub

		user = userService.getUserByEmail(email);
		if (user.getEmail() == email) {
			System.out.println("Email already registered. Please login !");
			return login(name,email);
		}
		System.out.println("[ Password] : ");	
		String password = sc.nextLine();
		
		System.out.println("[ Phone ] : ");	
		Long phone = sc.nextLong();
		
		String ip = InetAddress.getLocalHost().toString().split("/")[1]; 
		user = new User(name,password,email,phone,ip);		
		userService.registerUser(user);
		return user;
	}

	public static void start(String choice,User use) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		user = use;
		if(choice.equalsIgnoreCase("Y")) GroupChat.start(user);
		else OneToOneChat.start(user);
	}

}
