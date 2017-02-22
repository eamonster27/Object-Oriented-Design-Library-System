import java.io.IOException;
import java.util.Scanner;


interface DataHandler {
	public void loadDataFromFile(String fileName, LibrarySystem libs) throws IOException;
	public void saveDataToFile(String fileName, LibrarySystem libs);
}
