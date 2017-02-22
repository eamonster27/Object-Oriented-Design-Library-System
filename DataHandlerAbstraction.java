import java.io.IOException;


public interface DataHandlerAbstraction {
	
	public void load(String fileName, LibrarySystem libs) throws IOException;
	public void save(String fileName, LibrarySystem libs);
}
