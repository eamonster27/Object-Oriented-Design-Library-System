import java.util.InputMismatchException;


public class LibrarianView extends ConsoleWindow {

	protected void printMenu() {
		System.out.println("\n1. Add Item\n2. Checked-out Items\n3. Quit");
	}
	protected void start() {
		int input = 0;
		boolean quit = false;
		do {
			printMenu();
			try {
				input = sc.nextInt();
			
				switch (input) {
					case 1: libs.addItem(); break;
					case 2: LibrarySystem.displayCheckedOutItems(); break;
					case 3: quit = true; break;
					default: System.err.println("Please select a number between 1 and 3.");
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
