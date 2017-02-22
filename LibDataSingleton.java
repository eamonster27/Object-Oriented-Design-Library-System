
public class LibDataSingleton {
	
	private static LibDataSingleton LDS = null;
	
	private LibDataSingleton(){}
	
	protected LibrarySystem libs = new LibrarySystem();
	
	public static LibDataSingleton getInstance(){
		if(LDS == null)
			LDS = new LibDataSingleton();
		return LDS;
	}
	
}
