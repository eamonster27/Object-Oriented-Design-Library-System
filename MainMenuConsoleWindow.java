import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MainMenuConsoleWindow extends ConsoleWindow {
	public MainMenuConsoleWindow(LibrarySystem lib) {
		MainMenuConsoleWindow.libs = lib;
	}
	
	protected void printMenu() {
		System.out.println("1. User view\n2. Librarian view\n3. Admin view\n4. Quit");
	}
	
	public void start() {
		ConsoleWindow c;
		int choice = 0;
		boolean quit = false;
		do {
			printMenu();
			try {
				choice = sc.nextInt();
				
				switch (choice) {
					case 1: c = new UserView(); c.start(); break;
					case 2: c = new LibrarianView(); c.start();; break;
					case 3: c = new AdminView(); c.start(); break;
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
}
	
