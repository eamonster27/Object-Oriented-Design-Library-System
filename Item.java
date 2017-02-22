

import java.lang.String;
public class Item {

	public int doc_id;
	public int type;
	public String title;
	public int num_authors;
	public String[] author = null;
	public String publisher;
	public String publication_date;
	public String isbn;
	public String location;
	public String number;
	public String volume;
	public String date;
	public int waitList[] = null;
	public int num_waiting = 0;
	public int in_stock = 0;
	public int total = 0;
	
	public int docIDRecord[] = null;
	public int userIDRecord[] = null;
	public String dateRecord[] = null;
	public int dueDate[] = null;
}
