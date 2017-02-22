import java.io.IOException;


public class DataHandlerRefined implements DataHandlerAbstraction{

	
	private DataHandler dh;
	
	
	public void load(String fileName, LibrarySystem libs) throws IOException {
		dh.loadDataFromFile(fileName, libs);
	}

	public void save(String fileName, LibrarySystem libs) {
		dh.saveDataToFile(fileName, libs);
	}
	
}
