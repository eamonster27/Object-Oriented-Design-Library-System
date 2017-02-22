import java.util.InputMismatchException;


public class UserView extends ConsoleWindow {
	private static User current_user = null;
	
	protected void printMenu(){
		System.out.println("\n1. Search\n2. Checkout\n3. Return\n4. Quit");
	}
	
	protected void start() {
		int input = 0;
		boolean quit = false;
		DocObserver docObs = new DocObserver();
		libs.addObserver(docObs);
		
		do {
			if(current_user == null) {
				System.out.println("Enter User ID: ");
				int user_id = sc.nextInt();
				current_user = AllUsers.searchID(user_id);
				if(current_user != null) {
					AllUsers.displayUserInfo(current_user);
					if(current_user.num_messages > 0 && current_user.notification){
						for(int i = 0; i < current_user.num_messages; ++i)
							System.out.println(current_user.message[i] + "\n");
						current_user.notification = false;
						current_user.message = new String[0];
					}
				}
			}
			
			if(current_user == null) {
				System.out.println("The ID you entered was not found. ");
			}
			else {
				
				printMenu();
				try {
					input = sc.nextInt();
				
					switch (input) {
						case 1: LibrarySystem.searchItems(); break;
						case 2: LibrarySystem.rentItem(current_user); break;
						case 3: libs.returnItem(current_user); break;
						case 4: quit = true; current_user = null; break;
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
			}
		} while(!quit);
	}

}
