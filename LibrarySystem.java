import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;


public class LibrarySystem extends Observable{
	
//DISPALYS**********************************************************************
//DISPALYS**********************************************************************
	public static void displayCheckedOutItems() throws FileNotFoundException {
		for(int i = 0; i < AllItems.total_items; ++i)
			if(AllItems.loadedItems[i].in_stock != AllItems.loadedItems[i].total)
				AllItems.displayItemInfo(AllItems.loadedItems[i]);
	}
	
	public static void displayAllUsers() throws FileNotFoundException {
		for(int i = 0; i < AllUsers.total_IDs; ++i){
			if(AllUsers.loadedUsers[i].removed == false)
				AllUsers.displayUserInfo(AllUsers.loadedUsers[i]);
		}	
	}
//DISPALYS**********************************************************************
//DISPALYS**********************************************************************

//ADD USER**********************************************************************	
//ADD USER**********************************************************************
	public static void addUser() {
		Scanner in = new Scanner(System.in);
		int new_type;
		String name;
		int index = AllUsers.total_IDs;
		boolean replacement = false;
		
		if(AllUsers.total_users >= AllUsers.MAX_USERS) {
			System.out.println("\nMax users reached.");
		}
		else {
			System.out.println("\nEnter first and last name of user: ");
			name = in.next() + " " + in.next();
			
			System.out.println("What type of user?\n0. Student\n1. Staff\n2. " +
			"Faculty\n");
			new_type = in.nextInt();
			//Checking for removed IDs to replace
			for(int i = 0; i < AllUsers.total_IDs; ++i)
				if(AllUsers.loadedUsers[i].removed == true) {
					index = i;
					replacement = true;
					break;
				}
			AllUsers.loadedUsers[index] = new User();
			if(new_type == 0) {
				AllUsers.loadedUsers[index].name = name;
				AllUsers.loadedUsers[index].ID = index;
				AllUsers.loadedUsers[index].type = new_type;
				AllUsers.loadedUsers[index].MAX_DAYS = 180;
				AllUsers.loadedUsers[index].MAX_RENTALS = 6;
				
				AllUsers.loadedUsers[index].removed = false;
				
				if(!replacement) {
					AllUsers.total_IDs++;
				}
				AllUsers.total_users++;
			}
			else if(new_type == 1) {
				AllUsers.loadedUsers[index].name = name;
				AllUsers.loadedUsers[index].ID = index;
				AllUsers.loadedUsers[index].type = new_type;
				AllUsers.loadedUsers[index].MAX_DAYS = 365;
				AllUsers.loadedUsers[index].MAX_RENTALS = 12;

				if(!replacement) {
					AllUsers.total_IDs++;
					AllUsers.loadedUsers[index].removed = false;
				}
				AllUsers.total_users++;
			}
			else if(new_type == 2) {
				AllUsers.loadedUsers[index].name = name;
				AllUsers.loadedUsers[index].ID = index;
				AllUsers.loadedUsers[index].type = new_type;
				AllUsers.loadedUsers[index].MAX_DAYS = 365;
				AllUsers.loadedUsers[index].MAX_RENTALS = 12;

				if(!replacement) {
					AllUsers.total_IDs++;
				}
				AllUsers.loadedUsers[index].removed = false;
				AllUsers.total_users++;
			}
			else {
				System.out.println("Selection not recognized.\n");
			}
		}
	}
//ADD USER*************************************************************************	
//ADD USER*************************************************************************
	
//REMOVE USER**********************************************************************	
//REMOVE USER**********************************************************************	
	public static void removeUser() {
		Scanner in = new Scanner(System.in);
		int user_id;
		User target;
		
		System.out.println("What is the users ID?\n");
		user_id = in.nextInt();
		
		target = AllUsers.searchID(user_id);
		if(target == null) {
			System.out.println("ID not found");
		}
		else {
			AllUsers.total_users--;
			AllUsers.loadedUsers[user_id].name = "xxx";
			AllUsers.loadedUsers[user_id].type = 9;
			AllUsers.loadedUsers[user_id].MAX_DAYS = 0;
			AllUsers.loadedUsers[user_id].MAX_RENTALS = 0;
			AllUsers.loadedUsers[user_id].removed = true;		
		}
		
	}
//REMOVE USER**********************************************************************	
//REMOVE USER**********************************************************************	
	
//ADD ITEM*************************************************************************
//ADD ITEM*************************************************************************
	public static void addItem() {
		Scanner in = new Scanner(System.in);
		int new_type;
		System.out.println("What type of item?\n0. Book\n1. Journal\n2. Audio " +
				"Tape\n3. Video\n4. Conference Proceedings\n5. Quit");
		new_type = in.nextInt();

		AllItems.loadedItems[AllItems.total_items] = new Item();
		//Book
		if(new_type == 0) {
			AllItems.loadedItems[AllItems.total_items].doc_id = AllItems.total_items;
			AllItems.loadedItems[AllItems.total_items].type = new_type;
			
			System.out.println("\nEnter Title(no spaces): ");
			String title = in.next();
			AllItems.loadedItems[AllItems.total_items].title = title;
			
			System.out.println("\nEnter Number of Authors: ");
			int num_authors = in.nextInt();
			
			AllItems.loadedItems[AllItems.total_items].author = new String[num_authors];
			
			for(int i = 0; i < num_authors; ++i) {
				System.out.println("\nEnter Author #" + (i+1) + "'s first and last name");
				String author = in.next() + " " + in.next();
				AllItems.loadedItems[AllItems.total_items].author[i] = author;
			}
			AllItems.loadedItems[AllItems.total_items].num_authors = num_authors;
			System.out.println("\nEnter Publisher(no spaces): ");
			String publisher = in.next();
			AllItems.loadedItems[AllItems.total_items].publisher = publisher;
			
			System.out.println("\nEnter Publication Date: ");
			String p_date = in.next();
			AllItems.loadedItems[AllItems.total_items].publication_date = p_date;
			
			System.out.println("\nEnter ISBN: ");
			String isbn = in.next();
			AllItems.loadedItems[AllItems.total_items].isbn = isbn;
			
			System.out.println("\nEnter Number of Items: ");
			int i_num = in.nextInt();
			AllItems.loadedItems[AllItems.total_items].total += i_num;
			AllItems.loadedItems[AllItems.total_items].in_stock += i_num;
			
			AllItems.total_items++;
			
		}//Journal
		else if(new_type == 1) {
			AllItems.loadedItems[AllItems.total_items].doc_id = AllItems.total_items;
			AllItems.loadedItems[AllItems.total_items].type = new_type;
			
			System.out.println("\nEnter Title with no spaces: ");
			String title = in.next();
			AllItems.loadedItems[AllItems.total_items].title = title;
			
			System.out.println("\nEnter Number of Authors: ");
			int num_authors = in.nextInt();
			
			AllItems.loadedItems[AllItems.total_items].author = new String[num_authors];
			
			for(int i = 0; i < num_authors; ++i) {
				System.out.println("\nEnter Author #" + (i+1) + "'s first and last name");
				String author = in.next() + " " + in.next();
				AllItems.loadedItems[AllItems.total_items].author[i] = author;
			}
			AllItems.loadedItems[AllItems.total_items].num_authors = num_authors;
			System.out.println("\nEnter Publisher: ");
			String publisher = in.next();
			AllItems.loadedItems[AllItems.total_items].publisher = publisher;
			
			System.out.println("\nEnter Publication Date: ");
			String p_date = in.next();
			AllItems.loadedItems[AllItems.total_items].publication_date = p_date;
			
			System.out.println("\nEnter Volume: ");
			String vol = in.next();
			AllItems.loadedItems[AllItems.total_items].volume = vol;
			
			System.out.println("\nEnter Journal Number: ");
			String num = in.next();
			AllItems.loadedItems[AllItems.total_items].number = num;
			
			System.out.println("\nEnter Number of Items: ");
			int i_num = in.nextInt();
			AllItems.loadedItems[AllItems.total_items].total += i_num;
			AllItems.loadedItems[AllItems.total_items].in_stock += i_num;
			
			AllItems.total_items++;
		}//Audio
		else if(new_type == 2) {
			AllItems.loadedItems[AllItems.total_items].doc_id = AllItems.total_items;
			AllItems.loadedItems[AllItems.total_items].type = new_type;
			
			System.out.println("\nEnter Title with no spaces: ");
			String title = in.next();
			AllItems.loadedItems[AllItems.total_items].title = title;
			
			System.out.println("\nEnter Number of Authors: ");
			int num_authors = in.nextInt();
			
			AllItems.loadedItems[AllItems.total_items].author = new String[num_authors];
			
			for(int i = 0; i < num_authors; ++i) {
				System.out.println("\nEnter Author #" + (i+1) + "'s first and last name");
				String author = in.next() + " " + in.next();
				AllItems.loadedItems[AllItems.total_items].author[i] = author;
			}
			AllItems.loadedItems[AllItems.total_items].num_authors = num_authors;
			System.out.println("\nEnter Number of Items: ");
			int i_num = in.nextInt();
			AllItems.loadedItems[AllItems.total_items].total += i_num;
			AllItems.loadedItems[AllItems.total_items].in_stock += i_num;
			
			AllItems.total_items++;
		}//Video
		else if(new_type == 3) {
			AllItems.loadedItems[AllItems.total_items].doc_id = AllItems.total_items;
			AllItems.loadedItems[AllItems.total_items].type = new_type;
			
			System.out.println("\nEnter Title with no spaces: ");
			String title = in.next();
			AllItems.loadedItems[AllItems.total_items].title = title;
			
			System.out.println("\nEnter Number of Authors: ");
			int num_authors = in.nextInt();
			
			AllItems.loadedItems[AllItems.total_items].author = new String[num_authors];
			
			for(int i = 0; i < num_authors; ++i) {
				System.out.println("\nEnter Author #" + (i+1) + "'s first and last name");
				String author = in.next() + " " + in.next();
				AllItems.loadedItems[AllItems.total_items].author[i] = author;
			}
			AllItems.loadedItems[AllItems.total_items].num_authors = num_authors;
			System.out.println("\nEnter Number of Items: ");
			int i_num = in.nextInt();
			AllItems.loadedItems[AllItems.total_items].total += i_num;
			AllItems.loadedItems[AllItems.total_items].in_stock += i_num;
			
			AllItems.total_items++;
		}//Conference Proceeding
		else if(new_type == 4) {
			AllItems.loadedItems[AllItems.total_items].doc_id = AllItems.total_items;
			AllItems.loadedItems[AllItems.total_items].type = new_type;
			
			System.out.println("\nEnter Title with no spaces: ");
			String title = in.next();
			AllItems.loadedItems[AllItems.total_items].title = title;
			
			System.out.println("\nEnter Number of Authors: ");
			int num_authors = in.nextInt();
			
			AllItems.loadedItems[AllItems.total_items].author = new String[num_authors];
			
			for(int i = 0; i < num_authors; ++i) {
				System.out.println("\nEnter Author #" + (i+1) + "'s first and last name");
				String author = in.next() + " " + in.next();
				AllItems.loadedItems[AllItems.total_items].author[i] = author;
			}
			AllItems.loadedItems[AllItems.total_items].num_authors = num_authors;
			System.out.println("\nEnter Location: ");
			String location = in.next();
			AllItems.loadedItems[AllItems.total_items].location = location;
			
			System.out.println("\nEnter Date: ");
			String date = in.next();
			AllItems.loadedItems[AllItems.total_items].date = date;
			
			System.out.println("\nEnter Number of Items: ");
			int i_num = in.nextInt();
			AllItems.loadedItems[AllItems.total_items].total += i_num;
			AllItems.loadedItems[AllItems.total_items].in_stock += i_num;
			
			AllItems.total_items++;
		}//Quit
		else if(new_type == 5)
			System.out.println("No changes made.\n");
		else
			System.out.println("Selection not recognized.\n");	
	}
//ADD ITEM*************************************************************************
//ADD ITEM*************************************************************************
	
//RETURN ITEM**********************************************************************	
//RETURN ITEM**********************************************************************
	public void returnItem(User user) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Document ID of the item you want to return: ");
		int id = in.nextInt();
		boolean possessed = false;
		for(int i = 0; i < user.num_borrowed; ++i)
			if(user.borrowedItems[i].doc_id == id) {
				AllItems.loadedItems[id].in_stock++;
				for(int j = i; j < user.num_borrowed; ++j) {
					if(j != (user.num_borrowed - 1)){
						user.borrowedItems[j] = user.borrowedItems[j+1];
						user.borrowedDates[j] = user.borrowedDates[j+1];
					}
				}
				user.num_borrowed--;
				if(AllItems.loadedItems[id].num_waiting > 0){
					setChanged();
					notifyObservers(AllItems.loadedItems[id].doc_id);
				}
				possessed = true;
				//Remove transaction receipt
			}
		if(!possessed)
			System.out.println("You do not have a book with this Document ID: " + id + "\n");
	}
//RETURN ITEM**********************************************************************	
//RETURN ITEM**********************************************************************
	
//RENT ITEM**********************************************************************	
//RENT ITEM**********************************************************************
	public static void rentItem(User user) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Document ID of the item you want to rent:\n");
		int id = in.nextInt();
		boolean duplicate = false;
		for(int i = 0; i < user.num_borrowed; ++i)
			if(id == user.borrowedItems[i].doc_id)
				duplicate = true;
		if(id >= AllItems.total_items)
			System.out.println("Document ID not recognized.");
		else if(user.num_borrowed >= user.MAX_RENTALS)
			System.out.println("You have reach the maximum number of " +
					"rentals allowed to you.\n");
		else if(duplicate){
			System.out.println("You have already rented a copy of this item.");
		}
		else if(AllItems.loadedItems[id].in_stock == 0) {
			System.out.println("Out of stock.\n1. Wait List\n2. Main Menu\n");
			int input = in.nextInt();
			if(input == 1) {
				//Initializing temp waiting list
				int tempWL[] = new int[AllItems.loadedItems[id].num_waiting];
				//Copying contents of waitList to tempWL
				for(int i = 0; i < AllItems.loadedItems[id].num_waiting; ++i)
					tempWL[i] = AllItems.loadedItems[id].waitList[i];
				//Reinitializing AllItems.loadedItems[id].waitList to add user's id
				AllItems.loadedItems[id].waitList = new int[AllItems.loadedItems[id].num_waiting+1];
				//Copying old contents from tempWL
				for(int i = 0; i < AllItems.loadedItems[id].num_waiting; ++i)
					AllItems.loadedItems[id].waitList[i] = tempWL[i];
				//Copying new id to waitList
				AllItems.loadedItems[id].waitList[AllItems.loadedItems[id].num_waiting] = user.ID;
				AllItems.loadedItems[id].num_waiting++;
			}
		}
		else {
			if(id < AllItems.total_items && id >= 0){
				if(AllItems.loadedItems[id].in_stock > 0) {
					// Initializing temporary borrowedItems storage array
					Item tempBI[] = new Item[user.num_borrowed];
					String tempBD[] = new String[user.num_borrowed];
					// Copying current contents of borrowedItems into temporary array
					for(int i = 0; i < user.num_borrowed; ++i) {
						tempBI[i] = user.borrowedItems[i];
						tempBD[i] = user.borrowedDates[i];
					}
					// Reinitializing borrowedItems to accommodate new rental
					user.borrowedItems = new Item[user.num_borrowed + 1];
					user.borrowedDates = new String[user.num_borrowed + 1];
					// Copying current contents of borrowedItems into reinitialized array
					for(int i = 0; i < user.num_borrowed; ++i) {
						user.borrowedItems[i] = tempBI[i];
						user.borrowedDates[i] = tempBD[i];
					}
					// user.borrowedItems[user.num_borrowed] ready for new book rental
					
					// Creating "size" index which represents number of checked out copies
					int size = AllItems.loadedItems[id].total - 
					AllItems.loadedItems[id].in_stock;
					
					// Initializing temporary array to store current records
					int tempDIR[] = new int[size];
					int tempUIR[] = new int[size];
					String tempDR[] = new String[size];
					int tempDD[] = new int[size];
					// Copying records into temporary array
					for(int i = 0; i < size; ++i){
						tempDIR[i] = AllItems.loadedItems[id].docIDRecord[i];
						tempUIR[i] = AllItems.loadedItems[id].userIDRecord[i];
						tempDR[i] = AllItems.loadedItems[id].dateRecord[i];
						tempDD[i] = AllItems.loadedItems[id].dueDate[i];
					}
					// Reinitializing records to accommodate new rental records
					AllItems.loadedItems[id].docIDRecord = new int[size + 1];
					AllItems.loadedItems[id].userIDRecord = new int[size + 1];
					AllItems.loadedItems[id].dateRecord = new String[size + 1];
					AllItems.loadedItems[id].dueDate =  new int[size + 1];
					// Copying current records into reinitialized array
					for(int i = 0; i < size; ++i){
						AllItems.loadedItems[id].docIDRecord[i] = tempDIR[i];
						AllItems.loadedItems[id].userIDRecord[i] = tempUIR[i];
						AllItems.loadedItems[id].dateRecord[i] = tempDR[i];
						AllItems.loadedItems[id].dueDate[i] = tempDD[i];
					}
					// Record arrays[size] are ready for new book rental record
					
					Date date = new Date();
					// Adding new book rental to borrowedItems
					user.borrowedItems[user.num_borrowed] = new Item();
					user.borrowedItems[user.num_borrowed] = AllItems.loadedItems[id];
					// Attaching due date to new borrowedItems
					user.borrowedDates[user.num_borrowed] = new String();
					user.borrowedDates[user.num_borrowed] = date.toString();
					
					// Updating number of borrowed items
					user.num_borrowed++;
					// Create transaction record
					AllItems.loadedItems[id].docIDRecord[size] = id;
					AllItems.loadedItems[id].userIDRecord[size] = user.ID;
					AllItems.loadedItems[id].dateRecord[size] = date.toString();
					
					if(AllItems.loadedItems[id].type == 1)
						AllItems.loadedItems[id].dueDate[size] = user.J_MAX_DAYS; 
					else
						AllItems.loadedItems[id].dueDate[size] = user.MAX_DAYS;
					// Updating number of copies in stock
					AllItems.loadedItems[id].in_stock--;
				
					System.out.println("Document ID: " + 
							AllItems.loadedItems[id].docIDRecord[size]);
					System.out.println("User ID: " + 
							AllItems.loadedItems[id].userIDRecord[size]);
					System.out.println("Date: " + 
							AllItems.loadedItems[id].dateRecord[size]);
					System.out.println("Due in: " + 
							AllItems.loadedItems[id].dueDate[size] + " days.");
					
					for(int i = 0; i < AllItems.loadedItems[id].num_waiting; ++i) {
						if(AllItems.loadedItems[id].waitList[i] == user.ID){
							for(int j = i; j < AllItems.loadedItems[id].num_waiting; ++j)
								if(j == (AllItems.loadedItems[id].num_waiting - 1))
									AllItems.loadedItems[id].num_waiting--;
								else{
									AllItems.loadedItems[id].waitList[j] = AllItems.loadedItems[id].waitList[j+1];
									AllItems.loadedItems[id].num_waiting--;
								}
							break;
						}
					}
				}
			}
			else
				System.out.println("No Item with Document ID: " + id);
		}
	}
//RENT ITEM**********************************************************************	
//RENT ITEM**********************************************************************
	
//SEARCH ITEM********************************************************************	
//SEARCH ITEM********************************************************************
	public static void searchItems() {
		Scanner sc = new Scanner(System.in);
		AllItems ai;
		
		int input = 0;
		boolean quit = false;
		do {
			
			System.out.println("\nSearch by: \n1. Title\n2. Author\n3. Publisher\n4. Quit");
			try {
				input = sc.nextInt();
			
				switch (input) {
					case 1: ai = new AllItems(new SearchTitle()); ai.executeDocSearch(); break;
					case 2: ai = new AllItems(new SearchAuthor()); ai.executeDocSearch(); break;
					case 3: ai = new AllItems(new SearchPublisher()); ai.executeDocSearch(); break;
					case 4: quit = true; break;
					default: System.err.println("Please select a number between 1 and 4.");
				}
			} catch (InputMismatchException ex) {
				System.err.println("Input mismatch. Please try again." + ex);
				sc.nextLine();
			} catch (UnsupportedOperationException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				System.err.println("An unknown error has occured." + ex);
			}
		} while(!quit);
	}
	
//SEARCH ITEM********************************************************************	
//SEARCH ITEM********************************************************************
	
	
//IMPORT DATA********************************************************************
//IMPORT DATA********************************************************************
	
	public void importData() throws IOException {

		File file = new File("C:\\Data.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String data[] = null;
		String line;
		AllItems.loadedItems = new Item[AllItems.MAX_ITEMS];
		try {
			while(!(line = in.readLine()).equals("endItems")){
				data = line.split("	");
			
				AllItems.loadedItems[AllItems.total_items] = new Item();
			
				AllItems.loadedItems[AllItems.total_items].doc_id = Integer.valueOf(data[0]);
				AllItems.loadedItems[AllItems.total_items].type = Integer.valueOf(data[1]);
				AllItems.loadedItems[AllItems.total_items].title = data[2];
				AllItems.loadedItems[AllItems.total_items].num_authors = 
					Integer.valueOf(data[3]);
			
				AllItems.loadedItems[AllItems.total_items].author = 
					new String[AllItems.loadedItems[AllItems.total_items].num_authors];
			
				for(int i = 0; i < AllItems.loadedItems[AllItems.total_items].num_authors; ++i)
					AllItems.loadedItems[AllItems.total_items].author[i] = data[4+i];
			
				int index = AllItems.loadedItems[AllItems.total_items].num_authors + 4;
				
				if(AllItems.loadedItems[AllItems.total_items].type == 0){
					AllItems.loadedItems[AllItems.total_items].publisher = data[index++];
					AllItems.loadedItems[AllItems.total_items].publication_date = data[index++];
					AllItems.loadedItems[AllItems.total_items].isbn = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type == 1){
					AllItems.loadedItems[AllItems.total_items].publisher = data[index++];
					AllItems.loadedItems[AllItems.total_items].publication_date = data[index++];
					AllItems.loadedItems[AllItems.total_items].number = data[index++];
					AllItems.loadedItems[AllItems.total_items].volume = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type == 4){
					AllItems.loadedItems[AllItems.total_items].location = data[index++];
					AllItems.loadedItems[AllItems.total_items].date = data[index++];
				}
				else if(AllItems.loadedItems[AllItems.total_items].type != 2 && 
						AllItems.loadedItems[AllItems.total_items].type != 3)
					System.out.println("Error loading item type.");

				AllItems.loadedItems[AllItems.total_items].num_waiting = Integer.valueOf(data[index++]);
				int num_w = AllItems.loadedItems[AllItems.total_items].num_waiting;
				
				AllItems.loadedItems[AllItems.total_items].waitList = new int[num_w];
				
				for(int i = 0; i < num_w; ++i)
					AllItems.loadedItems[AllItems.total_items].waitList[i] = Integer.valueOf(data[index + i]);
				
				index = index + num_w;

				AllItems.loadedItems[AllItems.total_items].in_stock = 
					Integer.valueOf(data[index++]);
				AllItems.loadedItems[AllItems.total_items].total = 
					Integer.valueOf(data[index++]);

				int size = AllItems.loadedItems[AllItems.total_items].total - 
				AllItems.loadedItems[AllItems.total_items].in_stock;

				AllItems.loadedItems[AllItems.total_items].docIDRecord = new int[size];
				AllItems.loadedItems[AllItems.total_items].userIDRecord = new int[size];
				AllItems.loadedItems[AllItems.total_items].dateRecord = new String[size];
				AllItems.loadedItems[AllItems.total_items].dueDate = new int[size];
				for(int i = 0; i < size; ++i) {
					AllItems.loadedItems[AllItems.total_items].docIDRecord[i] = 
						Integer.valueOf(data[index++]);
					AllItems.loadedItems[AllItems.total_items].userIDRecord[i] = 
						Integer.valueOf(data[index++]);
					AllItems.loadedItems[AllItems.total_items].dateRecord[i] = 
						data[index++];
					AllItems.loadedItems[AllItems.total_items].dueDate[i] = 
						Integer.valueOf(data[index++]);
				}
				AllItems.total_items++;
			}
			
			data = null;
			AllUsers.loadedUsers = new User[AllUsers.MAX_USERS];
			
			while(!(line = in.readLine()).equals("endUsers")){
				
				data = line.split("	");
				
				AllUsers.loadedUsers[AllUsers.total_IDs] = new User();

				AllUsers.loadedUsers[AllUsers.total_IDs].ID = Integer.valueOf(data[0]);
				AllUsers.loadedUsers[AllUsers.total_IDs].type = Integer.valueOf(data[1]);
				AllUsers.loadedUsers[AllUsers.total_IDs].name = data[2];
				AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed = 
					Integer.valueOf(data[3]);
				
				int rem = Integer.valueOf(data[4]);
				if(rem == 0)
					AllUsers.loadedUsers[AllUsers.total_IDs].removed = false;
				else if(rem == 1)
					AllUsers.loadedUsers[AllUsers.total_IDs].removed = true;
				else
					System.out.println("Problem with input value.\n");

				AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems = 
					new Item[AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed];
				AllUsers.loadedUsers[AllUsers.total_IDs].borrowedDates = 
					new String[AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed];
				
				for(int i = 0; i < AllUsers.loadedUsers[AllUsers.total_IDs].num_borrowed;++i){
					int x = 2*i; //i=0 x=0, i=1 x=2, etc
					int id = 5 + x;
					int dr = 6 + x;
					int doc_id = Integer.valueOf(data[id]);
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems[i] = new Item();
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedItems[i] = 
						AllItems.loadedItems[doc_id];
					AllUsers.loadedUsers[AllUsers.total_IDs].borrowedDates[i] = data[dr];
				}
				
				AllUsers.loadedUsers[AllUsers.total_IDs].J_MAX_DAYS = 3;
				AllUsers.loadedUsers[AllUsers.total_IDs].hold = false;

				if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 0){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 180;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 6;
				}
				else if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 1){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 365;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 12;
				}
				else if(AllUsers.loadedUsers[AllUsers.total_IDs].type == 2){
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_DAYS = 365;
					AllUsers.loadedUsers[AllUsers.total_IDs].MAX_RENTALS = 12;
				}
				else {
					if(AllUsers.loadedUsers[AllUsers.total_IDs].type != 9)
						System.out.println("Error loading user type.");
				}

				//If user removed do not increment total_users.			
				AllUsers.total_users++;
				AllUsers.total_IDs++;
			}
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}	
	}

//IMPORT DATA********************************************************************
//IMPORT DATA********************************************************************

//EXPORT DATA********************************************************************
//EXPORT DATA********************************************************************
	public void exportData() {
		
		try {
			File file = new File("C:\\Data.txt");
			if(!file.exists())
				file.createNewFile();
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < AllItems.total_items; ++i){
				bw.write(AllItems.loadedItems[i].doc_id+"\t");
				bw.write(AllItems.loadedItems[i].type+"\t");
				bw.write(AllItems.loadedItems[i].title+"\t");
				bw.write(AllItems.loadedItems[i].num_authors+"\t");
				for(int j = 0; j < AllItems.loadedItems[i].num_authors; ++j)
					bw.write(AllItems.loadedItems[i].author[j]+"\t");
				//Book
				if(AllItems.loadedItems[i].type == 0) {
					bw.write(AllItems.loadedItems[i].publisher+"\t");
					bw.write(AllItems.loadedItems[i].publication_date+"\t");
					bw.write(AllItems.loadedItems[i].isbn+"\t");
				}//Journal
				else if(AllItems.loadedItems[i].type == 1) {
					bw.write(AllItems.loadedItems[i].publisher+"\t");
					bw.write(AllItems.loadedItems[i].publication_date+"\t");
					bw.write(AllItems.loadedItems[i].number+"\t");
					bw.write(AllItems.loadedItems[i].volume+"\t");
				}//Conference Proceeding
				else if(AllItems.loadedItems[i].type == 4) {
					bw.write(AllItems.loadedItems[i].location+"\t");
					bw.write(AllItems.loadedItems[i].date+"\t");
				}
				
				bw.write(AllItems.loadedItems[i].num_waiting+"\t");
				for(int l = 0; l < AllItems.loadedItems[i].num_waiting; ++l)
					bw.write(AllItems.loadedItems[i].waitList[l]+"t");
				
				bw.write(AllItems.loadedItems[i].in_stock+"\t");
				bw.write(AllItems.loadedItems[i].total+"\t");
				
				int size = AllItems.loadedItems[i].total - AllItems.loadedItems[i].in_stock;
				for(int k = 0; k < size; ++k) {
					bw.write(AllItems.loadedItems[i].docIDRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].userIDRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].dateRecord[k]+"\t");
					bw.write(AllItems.loadedItems[i].dueDate[k]+"\t");
				}
				bw.newLine();
			}
			bw.write("endItems");
			bw.newLine();
			
			for(int i = 0; i < AllUsers.total_IDs; ++i){
				bw.write(AllUsers.loadedUsers[i].ID+"\t");
				bw.write(AllUsers.loadedUsers[i].type+"\t");
				bw.write(AllUsers.loadedUsers[i].name+"\t");
				bw.write(AllUsers.loadedUsers[i].num_borrowed+"\t");
		
				int rem = 0;
				if(AllUsers.loadedUsers[i].removed == true)
					rem = 1;
				bw.write(rem+"\t");
				
				for(int j = 0; j < AllUsers.loadedUsers[i].num_borrowed; ++j) {
					bw.write(AllUsers.loadedUsers[i].borrowedItems[j].doc_id+"\t");
					bw.write(AllUsers.loadedUsers[i].borrowedDates[j]);
					if(!(j == (AllUsers.loadedUsers[i].num_borrowed - 1)))
						bw.write("\t");
				}
				bw.newLine();
			}
			bw.write("endUsers\n");
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}	
	}
}
//EXPORT DATA********************************************************************
//EXPORT DATA********************************************************************
