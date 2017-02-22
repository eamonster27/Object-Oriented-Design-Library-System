import java.io.IOException;
import java.util.Scanner;


public class SystemFrontend {
	//Using Singleton to access LibrarySystem
	public static LibDataSingleton LDS = LibDataSingleton.getInstance();
	
	public static void main(String[] args) throws IOException {
		String fileName = "Data.txt";
		
		//DataHandlerAbstraction[] dha = new DataHandlerAbstraction[1];
		//dha[0].load(fileName, LDS.libs);
		
		//Importing data
		LDS.libs.importData();
		
		//Using abstract console class
		ConsoleWindow c = new MainMenuConsoleWindow(LDS.libs);
		c.start();
		
		//Exporting data
		LDS.libs.exportData();
		//dha[0].save(fileName, LDS.libs);
	}
}
