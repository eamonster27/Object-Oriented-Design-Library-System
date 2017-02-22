import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AllUsers {

	public static int total_users = 0;
	public static int total_IDs = 0;
	public static int MAX_USERS = 100;
	public static User[] loadedUsers = null;


//SEARCH USERS******************************************************************
//SEARCH USERS******************************************************************
	public static User searchID(int ID) {
		while(ID < total_IDs)
		{
			if(loadedUsers[ID].removed == false)
				return loadedUsers[ID];
			else if(loadedUsers[ID].removed == true) {
				System.out.println("User removed.\n");
				return null;
			}
			else {
				System.out.println("Invalid ID.\n");
				return null;
			}
		}
		return null;
	}
//SEARCH USERS******************************************************************
//SEARCH USERS******************************************************************

//GET TOTAL USERS***************************************************************
//GET TOTAL USERS***************************************************************
	public int getTotal_users() {
		return total_users;
	}
//GET TOTAL USERS***************************************************************
//GET TOTAL USERS***************************************************************

//DISPLAY USER INFO*************************************************************
//DISPLAY USER INFO*************************************************************
	public static void displayUserInfo(User user) {
		String t;
		if(user.type == 0)
			t = "Student";
		else if(user.type == 1)
			t = "Staff";
		else if(user.type == 2)
			t = "Faculty";
		else if(user.type == 3)
			t = "Admin";
		else
			t = "Error";
		
		System.out.println("\nName: " + user.name);
		System.out.println("User ID: " + user.ID);
		System.out.println("User Type: " + t);
		System.out.println("Maximum Rental Days: " + user.MAX_DAYS);
		System.out.println("Maximum Book Rentals: " + user.MAX_RENTALS);
		//HOLD
		if(user.hold == true)
			System.out.println("Holds: Account has a hold. Please return and " +
					"overdue books or contact Admin.");
		else if(user.hold == false)
			System.out.println("Holds: No holds on account.");
		else
			System.out.println("\nError");
		//HOLD
		
		//DISPLAY BORROWED BOOKS
		System.out.println("\nBorrowed Books:");
		if(user.num_borrowed == 0)
			System.out.println("No Borrowed Books.");
		else {
			for(int i = 0; i < user.num_borrowed; ++i){
				AllItems.displayItemInfo(user.borrowedItems[i]);
				System.out.println("Due: " + user.MAX_DAYS + " days from " + 
						user.borrowedDates[i]);
			}
		}
	}
}
//DISPLAY USER INFO*************************************************************
//DISPLAY USER INFO*************************************************************
