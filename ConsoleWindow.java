import java.util.Scanner;

public abstract class ConsoleWindow{
	protected static LibrarySystem libs;
	protected static Scanner sc = new Scanner(System.in);
	
	protected abstract void printMenu();
	protected abstract void start();
	
}
