
public class SearchAuthor implements DocumentSearch {
	
	public SearchAuthor() {
	}
	
	public void printMenu() {
		System.out.println("Enter Author first and last name followed by enter: ");
	}
	
	public void start() {
		String author;
		boolean printed = false;
		author = sc.next() + " " + sc.next();
		
		for(int i = 0; i < AllItems.total_items; ++i) {
			for(int j = 0; j < AllItems.loadedItems[i].num_authors; ++j) {
				if(AllItems.loadedItems[i].author[j].equals(author)) {
					AllItems.displayItemInfo(AllItems.loadedItems[i]);
					printed = true;
				}
			}
		}							
	}
}